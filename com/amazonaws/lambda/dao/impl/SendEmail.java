package com.amazonaws.lambda.dao.impl;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import java.io.IOException;

public class SendEmail {
   static final String FROM = "prodummy2017@gmail.com";
   static final String TO = "sanjeet.roy@appdirect.com";
   static final String SUBJECT = "Amazon SES test (AWS SDK for Java)";
   static final String HTMLBODY = "<h1>Amazon SES test (AWS SDK for Java)</h1><p>This email was sent with <a href='https://aws.amazon.com/ses/'>Amazon SES</a> using the <a href='https://aws.amazon.com/sdk-for-java/'>AWS SDK for Java</a>";
   static final String TEXTBODY = "This email was sent through Amazon SES using the AWS SDK for Java.";

   public static void main(String[] args) throws IOException {
      try {
         AmazonSimpleEmailService client = (AmazonSimpleEmailService)((AmazonSimpleEmailServiceClientBuilder)AmazonSimpleEmailServiceClientBuilder.standard().withRegion(Regions.US_EAST_1)).build();
         SendEmailRequest request = (new SendEmailRequest()).withDestination((new Destination()).withToAddresses("sanjeet.roy@appdirect.com")).withMessage((new Message()).withBody((new Body()).withHtml((new Content()).withCharset("UTF-8").withData("<h1>Amazon SES test (AWS SDK for Java)</h1><p>This email was sent with <a href='https://aws.amazon.com/ses/'>Amazon SES</a> using the <a href='https://aws.amazon.com/sdk-for-java/'>AWS SDK for Java</a>")).withText((new Content()).withCharset("UTF-8").withData("This email was sent through Amazon SES using the AWS SDK for Java."))).withSubject((new Content()).withCharset("UTF-8").withData("Amazon SES test (AWS SDK for Java)"))).withSource("prodummy2017@gmail.com");
         client.sendEmail(request);
         System.out.println("Email sent!");
      } catch (Exception var3) {
         System.out.println("The email was not sent. Error message: " + var3.getMessage());
      }

   }
}
