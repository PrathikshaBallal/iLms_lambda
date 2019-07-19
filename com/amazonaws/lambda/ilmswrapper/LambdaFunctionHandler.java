package com.amazonaws.lambda.ilmswrapper;

import com.amazonaws.lambda.dao.impl.ComparatorImpl;
import com.amazonaws.lambda.util.CsvHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.event.S3EventNotification;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LambdaFunctionHandler implements RequestHandler {
   private AmazonS3 s3 = (AmazonS3)AmazonS3ClientBuilder.standard().build();
   private ArrayList adUsers = new ArrayList();
   static final Logger log = LogManager.getLogger(LambdaFunctionHandler.class);
   private int noOps = 1;

   public LambdaFunctionHandler() {
   }

   LambdaFunctionHandler(AmazonS3 s3) {
      this.s3 = s3;
   }

   public String handleRequest(S3Event event, Context context) {
      log.info("Received event: " + event);
      String bucket = ((S3EventNotification.S3EventNotificationRecord)event.getRecords().get(0)).getS3().getBucket().getName();
      String key = ((S3EventNotification.S3EventNotificationRecord)event.getRecords().get(0)).getS3().getObject().getKey();

      try {
         S3Object response = this.s3.getObject(new GetObjectRequest(bucket, key));
         String contentType = response.getObjectMetadata().getContentType();
         log.info("Processing File Name: " + key + " ....");
         BufferedReader br = new BufferedReader(new InputStreamReader(response.getObjectContent()));
         String opStatus = br.readLine();
         boolean var9 = true;

         int noOpsStatus;
         try {
            noOpsStatus = Integer.parseInt(opStatus.split("=")[1]);
         } catch (ArrayIndexOutOfBoundsException var12) {
            noOpsStatus = 1;
         }

         if (noOpsStatus == 0) {
            this.noOps = 0;
         } else {
            this.noOps = 1;
         }

         log.debug("noOpsStatus => " + opStatus);
         log.info("noOpsStatus => " + noOpsStatus);
         this.setAdUsers(br);
         this.callComparator();
         return contentType;
      } catch (Exception var13) {
         Exception e = var13;
         var13.printStackTrace();
         context.getLogger().log(String.format("Error getting object %s from bucket %s. Make sure they exist and your bucket is in the same region as this function.", key, bucket));

         try {
            throw e;
         } catch (IOException var11) {
            log.error("Exception = " + var11);
            return "";
         }
      }
   }

   public void setAdUsers(BufferedReader response) {
      CsvHandler csvHandler = new CsvHandler();
      log.debug("Csv has been converted to Java Objects");
      this.adUsers = csvHandler.parse(response);
   }

   public void callComparator() {
      ComparatorImpl comparatorImpl = new ComparatorImpl();
      if (this.noOps == 1) {
         log.info("Calling Comparator..");
         comparatorImpl.compare(this.adUsers);
         log.info("Comparator has been Run Successfully");
      } else {
         log.info("Calling Comparator and Updater..");
         comparatorImpl.compareAndUpdate(this.adUsers);
         log.info("Comparator & Updater has been Run Successfully");
      }

   }
}
