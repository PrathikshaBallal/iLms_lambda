package com.amazonaws.lambda.service.impl;

import com.amazonaws.lambda.common.UpdatedJson;
import com.amazonaws.lambda.dao.impl.LmsApiCallerImpl;
import com.amazonaws.lambda.domain.DiffUser;
import com.amazonaws.lambda.domain.LmsUser;
import com.amazonaws.lambda.domain.NewUser;
import com.amazonaws.lambda.service.LmsApiCallerService;
import com.amazonaws.lambda.util.IlmsFieldToNumber;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LmsApiCallerServiceImpl implements LmsApiCallerService {
   static final Logger log = LogManager.getLogger(LmsApiCallerServiceImpl.class);

   public Map getAllUsers() {
      LmsApiCallerImpl lmsApiCallerImpl = new LmsApiCallerImpl();
      return lmsApiCallerImpl.getAllUsers();
   }

   public Map getAllRegions() {
      LmsApiCallerImpl lmsApiCallerImpl = new LmsApiCallerImpl();
      return lmsApiCallerImpl.getAllRegions();
   }

   public Map getAllDivisions(Long regionId) {
      LmsApiCallerImpl lmsApiCallerImpl = new LmsApiCallerImpl();
      return lmsApiCallerImpl.getAllDivisions(regionId);
   }

   public Map getAllDepartments(long regionId, Long divisionId) {
      LmsApiCallerImpl lmsApiCallerImpl = new LmsApiCallerImpl();
      return lmsApiCallerImpl.getAllDepartments(regionId, divisionId);
   }

   public List inActivateUsers(Map lmsToBeInActivedUsers) {
      LmsApiCallerImpl lmsApiCallerImpl = new LmsApiCallerImpl();
      ArrayList failEmail = new ArrayList();
      if (lmsToBeInActivedUsers.size() == 0) {
         return failEmail;
      } else {
         Set emails = lmsToBeInActivedUsers.keySet();
         int total = emails.size();
         int count = 0;
         int fail = 0;
         int response = false;
         Iterator var10 = emails.iterator();

         while(var10.hasNext()) {
            String email = (String)var10.next();
            log.info("InActivating " + email);
            int response = lmsApiCallerImpl.inActivateUsers((Long)lmsToBeInActivedUsers.get(email));
            if (response != 204) {
               ++fail;
               failEmail.add(email);
               log.info("Failed to InActivate : " + email);
            } else {
               ++count;
               log.info("Successfully InActivated : " + email);
            }
         }

         return failEmail;
      }
   }

   public LmsUser getUserById(long userId) {
      LmsApiCallerImpl lmsApiCallerImpl = new LmsApiCallerImpl();
      return lmsApiCallerImpl.getUserById(userId);
   }

   public LmsUser getUserByEmailId(String email) {
      LmsApiCallerImpl lmsApiCallerImpl = new LmsApiCallerImpl();
      return lmsApiCallerImpl.getUserByEmailId(email);
   }

   public long getUserIdByEmailId(String email) {
      LmsApiCallerImpl lmsApiCallerImpl = new LmsApiCallerImpl();
      return lmsApiCallerImpl.getUserIdByEmailId(email);
   }

   public int updateUserProfileById(long userId, String payload) {
      LmsApiCallerImpl lmsApiCallerImpl = new LmsApiCallerImpl();
      int successCode = lmsApiCallerImpl.updateUserProfileById(userId, payload);
      return successCode;
   }

   public int addUser(String emailId, String payload) {
      LmsApiCallerImpl lmsApiCallerImpl = new LmsApiCallerImpl();
      int successCode = -1;

      try {
         successCode = lmsApiCallerImpl.addUser(emailId, payload);
      } catch (RuntimeException var6) {
         log.error((Object)var6);
      }

      return successCode;
   }

   public int addRegion(String region, String payload) {
      log.info("Adding Region : " + region);
      LmsApiCallerImpl lmsApiCallerImpl = new LmsApiCallerImpl();
      int successCode = lmsApiCallerImpl.addRegion(region, payload);
      return successCode;
   }

   public int addDivision(long regionId, String division, String payload) {
      LmsApiCallerImpl lmsApiCallerImpl = new LmsApiCallerImpl();
      int successCode = lmsApiCallerImpl.addDivision(regionId, division, payload);
      return successCode;
   }

   public int addDepartment(long regionId, long divisionId, String department, String payload) {
      LmsApiCallerImpl lmsApiCallerImpl = new LmsApiCallerImpl();
      int successCode = lmsApiCallerImpl.addDepartment(regionId, divisionId, department, payload);
      return successCode;
   }

   public int updateUserProfileByEmailId(String email, String payload) {
      long userId = this.getUserIdByEmailId(email);
      return this.updateUserProfileById(userId, payload);
   }

   public List updateUsers(ArrayList diffUsers) {
      int response = true;
      int total = diffUsers.size();
      int count = 0;
      int fail = 0;
      ArrayList failEmail = new ArrayList();
      if (total == 0) {
         return failEmail;
      } else {
         Iterator var8 = diffUsers.iterator();

         while(var8.hasNext()) {
            DiffUser diffUser = (DiffUser)var8.next();
            int response = this.updateUser(diffUser);
            if (response == 204) {
               ++count;
            } else {
               ++fail;
               failEmail.add(diffUser.getEmailId());
            }
         }

         return failEmail;
      }
   }

   public int updateUser(DiffUser diffUser) {
      ObjectMapper mapper = new ObjectMapper();
      String lmsField = "";
      long id = diffUser.getId();
      String email = diffUser.getEmailId();
      Map diffIndex = diffUser.getDiffIndex();
      Set keys = diffIndex.keySet();
      List updateObj = new ArrayList();
      int responseCode = -1;
      Iterator var13 = keys.iterator();

      while(var13.hasNext()) {
         String index = (String)var13.next();
         UpdatedJson updatedJson = new UpdatedJson();
         int num = IlmsFieldToNumber.get(index);
         if (num < 10) {
            lmsField = "F00";
         } else {
            lmsField = "F0";
         }

         lmsField = lmsField + num;
         updatedJson.setKey(lmsField);
         updatedJson.setValue((String)diffIndex.get(index));
         updateObj.add(updatedJson);
      }

      try {
         lmsField = mapper.writeValueAsString(updateObj);
      } catch (JsonProcessingException var16) {
         log.error("Exception : " + var16);
      }

      log.info("Updating Profile of " + email);
      log.info("Difference Index = " + keys.toString());
      this.logValues(diffUser);

      try {
         responseCode = this.updateUserProfileById(id, lmsField);
      } catch (RuntimeException var15) {
         log.error((Object)var15);
      }

      if (responseCode == 204) {
         log.info("Update has been Successful");
      } else {
         log.error("Oh No Something Wrong!!");
         log.error("Update Failed");
      }

      return responseCode;
   }

   public void logValues(DiffUser diffUser) {
      Map diffIndex = diffUser.getDiffIndex();
      Map diffIndexOld = diffUser.getDiffIndexOld();
      Set keys = diffIndex.keySet();
      List updateObj = new ArrayList();
      ObjectMapper mapper = new ObjectMapper();
      Iterator var8 = keys.iterator();

      String diffFieldsValue;
      while(var8.hasNext()) {
         diffFieldsValue = (String)var8.next();
         UpdatedJson updatedJson = new UpdatedJson();
         updatedJson.setKey(diffFieldsValue);
         updatedJson.setValue("From:-> " + (String)diffIndexOld.get(diffFieldsValue) + " To:-> " + (String)diffIndex.get(diffFieldsValue));
         updateObj.add(updatedJson);
      }

      diffFieldsValue = "";

      try {
         diffFieldsValue = mapper.writeValueAsString(updateObj);
      } catch (JsonProcessingException var10) {
         log.error("Exception : " + var10);
      }

      String formattedDiffFieldsValue = "{ \"updates\" : " + diffFieldsValue + " }";
      log.info(formattedDiffFieldsValue);
   }

   public List addUsers(ArrayList newUsers) {
      int total = newUsers.size();
      int count = 0;
      int fail = 0;
      int response = true;
      ArrayList failEmail = new ArrayList();
      if (total == 0) {
         return failEmail;
      } else {
         Iterator var8 = newUsers.iterator();

         while(var8.hasNext()) {
            NewUser newUser = (NewUser)var8.next();
            int response = this.addUser(newUser);
            if (response == 201) {
               ++count;
            } else {
               ++fail;
               failEmail.add(newUser.getEmailId());
            }
         }

         return failEmail;
      }
   }

   public int addUser(NewUser newUser) {
      ObjectMapper mapper = new ObjectMapper();
      Map profileFields = newUser.getProfileFields();
      Set keys = profileFields.keySet();
      String lmsField = "";
      String addPayload = "";
      String email = newUser.getEmailId();
      List updateObj = new ArrayList();
      int responseCode = true;
      Iterator var14 = keys.iterator();

      while(var14.hasNext()) {
         String index = (String)var14.next();
         UpdatedJson updatedJson = new UpdatedJson();
         int num = IlmsFieldToNumber.get(index);
         if (num < 10) {
            lmsField = "F00";
         } else {
            lmsField = "F0";
         }

         lmsField = lmsField + num;
         updatedJson.setKey(lmsField);
         updatedJson.setValue((String)profileFields.get(index));
         updateObj.add(updatedJson);
      }

      try {
         lmsField = mapper.writeValueAsString(updateObj);
         addPayload = "{\"userProfileData\":" + lmsField + ",\"sendRegistrationMail\":false,\"changePasswordAtNextLogin\":true}";
      } catch (JsonProcessingException var16) {
         log.error("Exception : " + var16);
      }

      log.info("Adding " + email);
      log.info("Following information will be Added: " + addPayload);
      int responseCode = this.addUser(email, addPayload);
      if (responseCode == 201) {
         log.info("Add Successful");
      } else {
         log.error("Oh No Something Wrong!!");
         log.error("Adding User Failed");
      }

      return responseCode;
   }
}
