package com.amazonaws.lambda.dao;

import com.amazonaws.lambda.beans.Course;
import com.amazonaws.lambda.service.impl.LmsApiCallerCourseServiceImpl;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class GetNotStartedUsers {
   public static void main(String[] args) {
      LmsApiCallerCourseServiceImpl lmsApiCallerCourseServiceImpl = new LmsApiCallerCourseServiceImpl();
      List allCourse = lmsApiCallerCourseServiceImpl.getAllCourses();
      List notstartedUsers = null;
      List expiresUsers = null;
      Iterator var6 = allCourse.iterator();

      while(var6.hasNext()) {
         Course course = (Course)var6.next();
         notstartedUsers = lmsApiCallerCourseServiceImpl.getNotStartedUsers(course.getID());
         expiresUsers = lmsApiCallerCourseServiceImpl.getNotStartedUsersWithDueDateExpires(course.getID());
         System.out.println(course.getName());
         System.out.println(course.getID());
         System.out.println("number of Not Started Users = " + notstartedUsers.size());
         System.out.println(Arrays.toString(notstartedUsers.toArray()));
         System.out.println("number of Not Started Users Whose Due Date Expires = " + expiresUsers.size());
         System.out.println(Arrays.toString(expiresUsers.toArray()));
      }

   }
}
