package com.amazonaws.lambda.service;

import com.amazonaws.lambda.domain.LmsUser;
import com.amazonaws.lambda.domain.NewUser;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface LmsApiCallerService {
   long getUserIdByEmailId(String var1);

   Map getAllUsers();

   LmsUser getUserById(long var1);

   LmsUser getUserByEmailId(String var1);

   int updateUserProfileById(long var1, String var3);

   int updateUserProfileByEmailId(String var1, String var2);

   List addUsers(ArrayList var1);

   int addUser(NewUser var1);

   List inActivateUsers(Map var1);
}
