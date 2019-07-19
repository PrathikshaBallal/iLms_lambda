package com.amazonaws.lambda.service.impl;

import com.amazonaws.lambda.beans.CourseEnrollment;
import com.amazonaws.lambda.beans.CourseProfileBean;
import com.amazonaws.lambda.beans.CourseUserBean;
import com.amazonaws.lambda.dao.impl.LmsApiCallerCourseImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class LmsApiCallerCourseServiceImpl {
   public List getAllCourses() {
      LmsApiCallerCourseImpl lmsApiCallerCourseImpl = new LmsApiCallerCourseImpl();
      return lmsApiCallerCourseImpl.getAllCourses();
   }

   public List getCourseAllEnrollment(String courseId) {
      LmsApiCallerCourseImpl lmsApiCallerCourseImpl = new LmsApiCallerCourseImpl();
      return lmsApiCallerCourseImpl.getCourseAllEnrollment(courseId);
   }

   public List getNotStartedUsers(String courseId) {
      List notStartedUsers = new ArrayList();
      List allEnrollment = this.getCourseAllEnrollment(courseId);
      Iterator var5 = allEnrollment.iterator();

      while(var5.hasNext()) {
         CourseEnrollment courseEnrollment = (CourseEnrollment)var5.next();
         CourseUserBean courseUser = courseEnrollment.getUser();
         CourseProfileBean courseUserProfile = null;
         String courseUserStatus = "";
         if (courseUser != null) {
            courseUserStatus = courseUser.getUserStatus();
         }

         if (courseUser != null) {
            courseUserProfile = courseUser.getProfile_Basic();
         }

         String courseStatus = courseEnrollment.getCourseStatus();
         if (courseStatus.equals("Not Started") && courseUserStatus.equals("Active")) {
            notStartedUsers.add(courseUserProfile.getEmailId());
         }
      }

      return notStartedUsers;
   }

   public List getNotStartedUsersWithDueDateExpires(String courseId) {
      List expireDuesDateUsers = new ArrayList();
      List allEnrollment = this.getCourseAllEnrollment(courseId);
      Iterator var5 = allEnrollment.iterator();

      while(var5.hasNext()) {
         CourseEnrollment courseEnrollment = (CourseEnrollment)var5.next();
         CourseUserBean courseUser = courseEnrollment.getUser();
         CourseProfileBean courseUserProfile = null;
         String courseUserStatus = "";
         if (courseUser != null) {
            courseUserStatus = courseUser.getUserStatus();
         }

         if (courseUser != null) {
            courseUserProfile = courseUser.getProfile_Basic();
         }

         String courseStatus = courseEnrollment.getCourseStatus();
         if (courseStatus.equals("Not Started") && courseUserStatus.equals("Active") && this.checkForExpiry(courseEnrollment.getDueDate())) {
            expireDuesDateUsers.add(courseUserProfile.getEmailId());
         }
      }

      return expireDuesDateUsers;
   }

   public boolean checkForExpiry(String dueDate) {
      long res = this.compareDate(dueDate);
      return res > 0L;
   }

   public long compareDate(String dueDate) {
      String currentDate = null;
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
      Date date = new Date();
      currentDate = sdf.format(date);
      Date due = null;
      Date current = null;
      long elapsed = 0L;

      try {
         due = sdf.parse(dueDate);
         current = sdf.parse(currentDate);
      } catch (ParseException var13) {
         System.out.println(var13);

         try {
            currentDate = dateFormat.format(date);
            due = dateFormat.parse(dueDate);
            current = dateFormat.parse(currentDate);
         } catch (ParseException var12) {
            System.out.println(var12);
         }
      }

      elapsed = current.getTime() - due.getTime();
      return elapsed;
   }
}
