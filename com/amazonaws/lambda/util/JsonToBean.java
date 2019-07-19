package com.amazonaws.lambda.util;

import com.amazonaws.lambda.beans.Course;
import com.amazonaws.lambda.beans.CourseEnrollment;
import com.amazonaws.lambda.beans.Department;
import com.amazonaws.lambda.beans.Division;
import com.amazonaws.lambda.beans.Region;
import com.amazonaws.lambda.beans.UserBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JsonToBean {
   static final Logger log = LogManager.getLogger(JsonToBean.class);

   public static UserBean jsonToUserBean(String json) {
      ObjectMapper mapper = new ObjectMapper();
      UserBean bean = null;

      try {
         bean = (UserBean)mapper.readValue(json, UserBean.class);
      } catch (Exception var4) {
         log.error("Exception " + var4);
      }

      log.debug("Json response has been Converted to java Object");
      return bean;
   }

   public static List jsonToAllUserBean(String json) {
      ObjectMapper mapper = new ObjectMapper();
      List bean = null;

      try {
         bean = Arrays.asList((UserBean[])mapper.readValue(json, UserBean[].class));
      } catch (Exception var4) {
         log.error("Exception " + var4);
      }

      log.debug("Json response has been Converted to java Object");
      return bean;
   }

   public static Course jsonToCourse(String json) {
      ObjectMapper mapper = new ObjectMapper();
      Course bean = null;

      try {
         bean = (Course)mapper.readValue(json, Course.class);
      } catch (Exception var4) {
         log.error("Exception " + var4);
      }

      log.debug("Json response has been Converted to java Object");
      return bean;
   }

   public static List jsonToAllCourse(String json) {
      ObjectMapper mapper = new ObjectMapper();
      List bean = null;

      try {
         bean = Arrays.asList((Course[])mapper.readValue(json, Course[].class));
      } catch (Exception var4) {
         log.error("Exception " + var4);
      }

      log.debug("Json response has been Converted to java Object");
      return bean;
   }

   public static CourseEnrollment jsonToCourseEnrollment(String json) {
      ObjectMapper mapper = new ObjectMapper();
      CourseEnrollment bean = null;

      try {
         bean = (CourseEnrollment)mapper.readValue(json, CourseEnrollment.class);
      } catch (Exception var4) {
         log.error("Exception " + var4);
      }

      log.debug("Json response has been Converted to java Object");
      return bean;
   }

   public static List jsonToAllCourseEnrollment(String json) {
      ObjectMapper mapper = new ObjectMapper();
      List bean = null;

      try {
         bean = Arrays.asList((CourseEnrollment[])mapper.readValue(json, CourseEnrollment[].class));
      } catch (Exception var4) {
         log.error("Exception " + var4);
      }

      log.debug("Json response has been Converted to java Object");
      return bean;
   }

   public static Region jsonToRegion(String json) {
      ObjectMapper mapper = new ObjectMapper();
      Region bean = null;

      try {
         bean = (Region)mapper.readValue(json, Region.class);
      } catch (Exception var4) {
         log.error("Exception " + var4);
      }

      log.debug("Json response has been Converted to java Object");
      return bean;
   }

   public static List jsonToAllRegion(String json) {
      ObjectMapper mapper = new ObjectMapper();
      List bean = null;

      try {
         bean = Arrays.asList((Region[])mapper.readValue(json, Region[].class));
      } catch (Exception var4) {
         log.error("Exception " + var4);
      }

      log.debug("Json response has been Converted to java Object");
      return bean;
   }

   public static Division jsonToDivision(String json) {
      ObjectMapper mapper = new ObjectMapper();
      Division bean = null;

      try {
         bean = (Division)mapper.readValue(json, Division.class);
      } catch (Exception var4) {
         log.error("Exception " + var4);
      }

      log.debug("Json response has been Converted to java Object");
      return bean;
   }

   public static List jsonToAllDivision(String json) {
      ObjectMapper mapper = new ObjectMapper();
      List bean = null;

      try {
         bean = Arrays.asList((Division[])mapper.readValue(json, Division[].class));
      } catch (Exception var4) {
         log.error("Exception " + var4);
      }

      log.debug("Json response has been Converted to java Object");
      return bean;
   }

   public static Department jsonToDepartment(String json) {
      ObjectMapper mapper = new ObjectMapper();
      Department bean = null;

      try {
         bean = (Department)mapper.readValue(json, Department.class);
      } catch (Exception var4) {
         log.error("Exception " + var4);
      }

      log.debug("Json response has been Converted to java Object");
      return bean;
   }

   public static List jsonToAllDepartment(String json) {
      ObjectMapper mapper = new ObjectMapper();
      List bean = null;

      try {
         bean = Arrays.asList((Department[])mapper.readValue(json, Department[].class));
      } catch (Exception var4) {
         log.error("Exception " + var4);
      }

      log.debug("Json response has been Converted to java Object");
      return bean;
   }
}
