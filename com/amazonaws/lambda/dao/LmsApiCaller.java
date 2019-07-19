package com.amazonaws.lambda.dao;

import com.amazonaws.lambda.domain.LmsUser;
import java.util.Map;

public interface LmsApiCaller {
   Map getAllUsers();

   LmsUser getUserById(long var1);

   LmsUser getUserByEmailId(String var1);

   long getUserIdByEmailId(String var1);

   int updateUserProfileById(long var1, String var3);

   int addUser(String var1, String var2);

   int inActivateUsers(Long var1);

   Map getAllRegions();

   int addRegion(String var1, String var2);

   Map getAllDivisions(Long var1);

   int addDivision(Long var1, String var2, String var3);

   Map getAllDepartments(Long var1, Long var2);

   int addDepartment(Long var1, long var2, String var4, String var5);
}
