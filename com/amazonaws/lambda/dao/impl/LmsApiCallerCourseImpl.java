package com.amazonaws.lambda.dao.impl;

import com.amazonaws.lambda.beans.Course;
import com.amazonaws.lambda.beans.CourseEnrollment;
import com.amazonaws.lambda.common.RequestMethod;
import com.amazonaws.lambda.util.JsonToBean;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LmsApiCallerCourseImpl {
   static final Logger log = LogManager.getLogger(LmsApiCallerImpl.class);
   private long organisationId;
   private List courses = new ArrayList();
   private List courseEnrollements = new ArrayList();

   public LmsApiCallerCourseImpl() {
      log.debug("Organisation Id has been settted.");
      this.organisationId = GetConnection.getOrgId();
   }

   public List getAllCourses() {
      String ilmsUrl = "https://api.inspiredlms.com/organizations/" + this.organisationId + "/courses?per_page=500";
      String next = this.getAllCourses(ilmsUrl);

      while(next != null) {
         try {
            next = this.getAllCourses(next);
         } catch (RuntimeException var4) {
            log.error((Object)var4);
         }
      }

      return this.courses;
   }

   public String getAllCourses(String ilmsUrl) {
      List bean = null;
      String next = null;

      try {
         log.debug("Trying to make a GET Request to ILMS, For All Courses");
         HttpURLConnection conn = GetConnection.getConnection(ilmsUrl, RequestMethod.GET);
         if (conn.getResponseCode() == 200) {
            log.debug("GET Request has been Successfully ececuted.");
            log.debug("Trying to read the Json Response From ILMS.");
            next = conn.getHeaderField("next");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String json = br.readLine();
            System.out.println(" course json = " + json);
            bean = JsonToBean.jsonToAllCourse(json);
            Iterator var8 = bean.iterator();

            while(var8.hasNext()) {
               Course course = (Course)var8.next();
               this.courses.add(course);
            }
         } else {
            log.error("Failed : HTTP error code : " + conn.getResponseCode() + " " + conn.getResponseMessage());
            LogResponseCode.log(conn.getResponseCode());
         }

         log.debug("Trying to Disconnect from ILMS.");
         conn.disconnect();
         log.debug("Successfully Disconnected from ILMS.");
      } catch (IOException var9) {
         log.error("Exception " + var9);
      }

      return next;
   }

   public List getCourseAllEnrollment(String courseId) {
      String ilmsUrl = "https://api.inspiredlms.com/organizations/" + this.organisationId + "/courses/" + courseId + "/enrollments?per_page=500";
      String next = this.getAllEnrollment(ilmsUrl);

      while(next != null) {
         try {
            next = this.getAllEnrollment(next);
         } catch (RuntimeException var5) {
            log.error((Object)var5);
         }
      }

      return this.courseEnrollements;
   }

   public String getAllEnrollment(String ilmsUrl) {
      List bean = null;
      String next = null;

      try {
         log.debug("Trying to make a GET Request to ILMS, For All Courses");
         HttpURLConnection conn = GetConnection.getConnection(ilmsUrl, RequestMethod.GET);
         if (conn.getResponseCode() == 200) {
            log.debug("GET Request has been Successfully ececuted.");
            log.debug("Trying to read the Json Response From ILMS.");
            next = conn.getHeaderField("next");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String json = br.readLine();
            bean = JsonToBean.jsonToAllCourseEnrollment(json);
            Iterator var8 = bean.iterator();

            while(var8.hasNext()) {
               CourseEnrollment courseEnrollment = (CourseEnrollment)var8.next();
               this.courseEnrollements.add(courseEnrollment);
            }
         } else {
            log.error("Failed : HTTP error code : " + conn.getResponseCode() + " " + conn.getResponseMessage());
            LogResponseCode.log(conn.getResponseCode());
         }

         log.debug("Trying to Disconnect from ILMS.");
         conn.disconnect();
         log.debug("Successfully Disconnected from ILMS.");
      } catch (IOException var9) {
         log.error("Exception " + var9);
      }

      return next;
   }
}
