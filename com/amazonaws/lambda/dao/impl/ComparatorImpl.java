package com.amazonaws.lambda.dao.impl;

import com.amazonaws.lambda.beans.Division;
import com.amazonaws.lambda.beans.Region;
import com.amazonaws.lambda.common.UpdatedJson;
import com.amazonaws.lambda.dao.Comparator;
import com.amazonaws.lambda.domain.AdUser;
import com.amazonaws.lambda.domain.DiffUser;
import com.amazonaws.lambda.domain.LmsUser;
import com.amazonaws.lambda.domain.NewUser;
import com.amazonaws.lambda.domain.UpdatedUsers;
import com.amazonaws.lambda.service.impl.LmsApiCallerServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ComparatorImpl implements Comparator {
   private ArrayList diffUsers = new ArrayList();
   private ArrayList newUsers = new ArrayList();
   private UpdatedUsers updatedUsers = new UpdatedUsers();
   private List activeUsers = new ArrayList();
   private Map lmsToBeInActivedUsers;
   private Map lmsUsers;
   private Map lmsRegions;
   private Map lmsDivisions;
   private Map lmsDepartments;
   private int onlyCompare;
   static final Logger log = LogManager.getLogger(ComparatorImpl.class);
   private int diffUsersCount;
   private int notExistsCount;
   private int updatedCount;
   private int total;
   private int totalAppdirectUser;

   public ComparatorImpl() {
      this.lmsToBeInActivedUsers = new TreeMap(String.CASE_INSENSITIVE_ORDER);
      this.onlyCompare = 1;
      this.diffUsersCount = 0;
      this.notExistsCount = 0;
      this.updatedCount = 0;
      this.total = 0;
      this.totalAppdirectUser = 0;
   }

   public void logSummary() {
      log.info("Summary of Things BEfore Update Are as Below......");
      log.info("Total number of USERS in CSV = " + this.total);
      log.info("Total number of USERS in CSV with Appdirect Account = " + this.totalAppdirectUser);
      log.info("Total number of USERS in ILMS = " + this.lmsUsers.size());
      log.info("Number of Users that Does NOT EXIST in ILMS (New USers) = " + this.notExistsCount);
      log.info("Number of Users whose Information are OUT-OF-DATE = " + this.diffUsersCount);
      log.info("Number of Users that are UP-To-DATE = " + this.updatedCount);
      this.logUpdatedUsersEmail();
      log.info("Number of Users that will be UPDATED if noOps = flase => " + this.diffUsersCount);
      this.logDiffUsersEmail();
      log.info("Number of Users that will be ADDED to ILMS if noOps = flase => " + this.notExistsCount);
      this.logNewUsersEmail();
      int extraUsers = this.lmsUsers.size() - this.activeUsers.size();
      log.info("Number of Users that are in ILMS but NOT in Csv (Extra Users) = " + extraUsers);
      log.info("Number of Users that will be InActivated in ILMS if noOps = flase => " + extraUsers);
      this.logExtraUsersEmail();
   }

   public void logSummaryOps(List failUpdate, List failAdd, List failInactivate) {
      int extraUsers = this.lmsUsers.size() - this.activeUsers.size();
      int countOfInactivatedUser = extraUsers - failInactivate.size();
      int countOfAddedUsers = this.notExistsCount - failAdd.size();
      int ilmsSizeAfterUpdate = this.lmsUsers.size() - countOfInactivatedUser + countOfAddedUsers;
      log.info("Congrats You Just Updated iLms !!");
      log.info("Summary of Things After Update Are as Below......");
      log.info("Total number of USERS in CSV with Appdirect Account = " + this.totalAppdirectUser);
      log.info("Total number of USERS in ILMS = " + ilmsSizeAfterUpdate);
      if (failUpdate.size() > 0) {
         log.info("Following are the Users Whose Update has been FAILED: ");
         log.info(Arrays.toString(failUpdate.toArray()));
      }

      if (failAdd.size() > 0) {
         log.info("Following are the Users Whose Addition has been FAILED: ");
         log.info(Arrays.toString(failAdd.toArray()));
      }

      if (failInactivate.size() > 0) {
         log.info("Following are the Users Whose In-Activation has been FAILED: ");
         log.info(Arrays.toString(failInactivate.toArray()));
      }

   }

   public void logExtraUsersEmail() {
      Set lmsUsersEmail = this.lmsUsers.keySet();
      Set lmsActiveUsersEmail_caseInSensitive = new TreeSet(String.CASE_INSENSITIVE_ORDER);
      lmsActiveUsersEmail_caseInSensitive.addAll(lmsUsersEmail);
      Set activeUsers_caseInSensitive = new TreeSet(String.CASE_INSENSITIVE_ORDER);
      activeUsers_caseInSensitive.addAll(this.activeUsers);
      lmsActiveUsersEmail_caseInSensitive.removeAll(activeUsers_caseInSensitive);
      log.info("Emails of the Users that will be InActivated = " + Arrays.toString(lmsActiveUsersEmail_caseInSensitive.toArray()));
      Iterator var5 = lmsActiveUsersEmail_caseInSensitive.iterator();

      while(var5.hasNext()) {
         String email = (String)var5.next();
         this.lmsToBeInActivedUsers.put(email, ((LmsUser)this.lmsUsers.get(email)).getId());
      }

   }

   public void logUpdatedUsersEmail() {
      if (this.updatedCount > 0) {
         log.info("Emails of the Users that are UP-TO-DATE = " + Arrays.toString(this.updatedUsers.getUsers().toArray()));
      }

   }

   public void logDiffUsersEmail() {
      String diffStr = "[";
      int diffSize = this.diffUsers.size();
      if (diffSize > 0) {
         diffStr = diffStr + ((DiffUser)this.diffUsers.get(0)).getEmailId();
      }

      for(int i = 1; i < diffSize; ++i) {
         diffStr = diffStr + "," + ((DiffUser)this.diffUsers.get(i)).getEmailId();
      }

      diffStr = diffStr + "]";
      log.info("Emails of the Users that will be UPDATED = " + diffStr);
   }

   public void logNewUsersEmail() {
      String newStr = "[";
      int newSize = this.newUsers.size();
      if (newSize > 0) {
         newStr = newStr + ((NewUser)this.newUsers.get(0)).getEmailId();
      }

      for(int i = 1; i < newSize; ++i) {
         newStr = newStr + "," + ((NewUser)this.newUsers.get(i)).getEmailId();
      }

      newStr = newStr + "]";
      log.info("Emails of the Users that will be ADDED = " + newStr);
   }

   public void compare(ArrayList adUsers) {
      this.onlyCompare = 1;
      this.setLmsUsers();
      this.setLmsRegions();
      this.compareAndSet(adUsers);
      this.logDiffUsers();
      this.logSummary();
   }

   public void compareAndUpdate(ArrayList adUsers) {
      this.onlyCompare = 0;
      this.setLmsUsers();
      this.setLmsRegions();
      this.compareAndSet(adUsers);
      List failUpdateUsers = this.updateLms();
      List failAddUsers = this.addToLms();
      List failInActivateUsers = this.inActivateInLms(this.lmsToBeInActivedUsers);
      this.logSummary();
      this.logSummaryOps(failUpdateUsers, failAddUsers, failInActivateUsers);
   }

   public List inActivateInLms(Map lmsToBeInActivedUsers) {
      LmsApiCallerServiceImpl lmsApiCallerServiceImpl = new LmsApiCallerServiceImpl();
      return lmsApiCallerServiceImpl.inActivateUsers(lmsToBeInActivedUsers);
   }

   public void setLmsUsers() {
      LmsApiCallerServiceImpl lmsApiCallerServiceImpl = new LmsApiCallerServiceImpl();
      log.info("Calling LmsApiCaller to get All Users");
      this.lmsUsers = lmsApiCallerServiceImpl.getAllUsers();
   }

   public void setLmsRegions() {
      LmsApiCallerServiceImpl lmsApiCallerServiceImpl = new LmsApiCallerServiceImpl();
      log.info("Calling LmsApiCaller to get All Regions");
      this.lmsRegions = lmsApiCallerServiceImpl.getAllRegions();
   }

   public void setLmsDivisions(long regionId) {
      LmsApiCallerServiceImpl lmsApiCallerServiceImpl = new LmsApiCallerServiceImpl();
      log.info("Calling LmsApiCaller to get All Divisions");
      this.lmsDivisions = lmsApiCallerServiceImpl.getAllDivisions(regionId);
   }

   public void setLmsDepartments(long regionId, long divisionId) {
      LmsApiCallerServiceImpl lmsApiCallerServiceImpl = new LmsApiCallerServiceImpl();
      log.info("Calling LmsApiCaller to get All Departments");
      this.lmsDepartments = lmsApiCallerServiceImpl.getAllDepartments(regionId, divisionId);
   }

   public boolean regionExist(String region) {
      return this.lmsRegions.get(region) != null;
   }

   public int addRegion(String region) {
      String payload = "{\"name\":\"" + region + "\"}";
      int successCode = true;
      LmsApiCallerServiceImpl lmsApiCallerServiceImpl = new LmsApiCallerServiceImpl();
      log.info("Calling LmsApiCaller ");
      log.info("Trying to add region = " + region);
      int successCode = lmsApiCallerServiceImpl.addRegion(region, payload);
      return successCode;
   }

   public int addDivision(String region, String division) {
      long regionId = ((Region)this.lmsRegions.get(region)).getID();
      String payload = "{\"name\":\"" + division + "\"}";
      int successCode = true;
      LmsApiCallerServiceImpl lmsApiCallerServiceImpl = new LmsApiCallerServiceImpl();
      log.info("Calling LmsApiCaller..");
      log.info("Trying to add division = " + division + " in region = " + region);
      int successCode = lmsApiCallerServiceImpl.addDivision(regionId, division, payload);
      return successCode;
   }

   public int addDepartment(String region, String division, String department) {
      long regionId = ((Region)this.lmsRegions.get(region)).getID();
      long divisionId = ((Division)this.lmsDivisions.get(division)).getID();
      String payload = "{\"name\":\"" + department + "\"}";
      int successCode = true;
      LmsApiCallerServiceImpl lmsApiCallerServiceImpl = new LmsApiCallerServiceImpl();
      log.info("Calling LmsApiCaller..");
      log.info("Trying to add department " + department + " in division = " + division + " in region = " + region);
      int successCode = lmsApiCallerServiceImpl.addDepartment(regionId, divisionId, department, payload);
      return successCode;
   }

   public boolean divisionExist(String region, String division) {
      if (this.lmsRegions.get(region) == null) {
         this.setLmsRegions();
      }

      this.setLmsDivisions(((Region)this.lmsRegions.get(region)).getID());
      log.info("Updated lmsDivision = " + Arrays.toString(this.lmsDivisions.keySet().toArray()));
      return this.lmsDivisions.get(division) != null;
   }

   public boolean departmentExist(String region, String division, String department) {
      this.setLmsDivisions(((Region)this.lmsRegions.get(region)).getID());
      this.setLmsDepartments(((Region)this.lmsRegions.get(region)).getID(), ((Division)this.lmsDivisions.get(division)).getID());
      return this.lmsDepartments.get(department) != null;
   }

   public void addToNewUsers(AdUser adUser) {
      Map profileFields = new HashMap();
      NewUser newUser = new NewUser();
      newUser.setEmailId(adUser.getEmailId());
      profileFields.put("firstName", adUser.getFirstName());
      profileFields.put("lastName", adUser.getLastName());
      profileFields.put("emailId", adUser.getEmailId());
      profileFields.put("jobTitle", adUser.getJobTitle());
      profileFields.put("supervisorName", adUser.getSupervisorName());
      profileFields.put("supervisorEmail", adUser.getSupervisorEmail());
      profileFields.put("city", adUser.getCity());
      profileFields.put("address1", adUser.getAddress1());
      if (this.onlyCompare == 0) {
         int responseCode;
         if (this.regionExist(adUser.getCity())) {
            profileFields.put("region", adUser.getCity());
         } else {
            log.debug("Region \"" + adUser.getCity() + "\" of user " + adUser.getEmailId() + " Does NOT EXIST in ILMS");
            log.info("Adding region = " + adUser.getCity());
            responseCode = this.addRegion(adUser.getCity());
            if (responseCode == 201) {
               profileFields.put("region", adUser.getCity());
            }
         }

         if (this.divisionExist(adUser.getCity(), adUser.getDivision())) {
            profileFields.put("division", adUser.getDivision());
         } else {
            log.info("Division \"" + adUser.getDivision() + "\" Does NOT EXIST in ILMS In region = \"" + adUser.getCity() + "\" of user " + adUser.getEmailId());
            log.info("Adding Division = " + adUser.getDivision());
            responseCode = this.addDivision(adUser.getCity(), adUser.getDivision());
            if (responseCode == 201) {
               profileFields.put("division", adUser.getDivision());
            }
         }

         if (this.departmentExist(adUser.getCity(), adUser.getDivision(), adUser.getDepartment())) {
            profileFields.put("department", adUser.getDepartment());
         } else {
            log.debug("Department \"" + adUser.getDepartment() + "\" Does NOT EXIST in ILMS In Division = \"" + adUser.getDivision() + "\" Of region = \"" + adUser.getCity() + "\" of user " + adUser.getEmailId());
            log.info("Adding Department = " + adUser.getDepartment());
            responseCode = this.addDepartment(adUser.getCity(), adUser.getDivision(), adUser.getDepartment());
            if (responseCode == 201) {
               profileFields.put("department", adUser.getDepartment());
            }
         }
      }

      newUser.setProfileFields(profileFields);
      this.newUsers.add(newUser);
   }

   public void compareAndSetNewUser(AdUser adUser) {
      String email = adUser.getEmailId();
      LmsUser lmsUser = (LmsUser)this.lmsUsers.get(email);
      if (lmsUser == null) {
         log.info("User '" + email + "' does NOT EXISTS");
         ++this.notExistsCount;
         this.addToNewUsers(adUser);
      }

   }

   public void compareAndSet(ArrayList adUsers) {
      int size = adUsers.size();
      this.total = size;

      for(int i = 0; i < size; ++i) {
         AdUser adUser = (AdUser)adUsers.get(i);
         if (adUser.getEmailId().endsWith("@appdirect.com")) {
            ++this.totalAppdirectUser;
            this.compareAndSetDiffUser(adUser);
            this.compareAndSetNewUser(adUser);
         }
      }

   }

   public void compareAndSetDiffUser(AdUser adUser) {
      String email = adUser.getEmailId();
      LmsUser lmsUser = (LmsUser)this.lmsUsers.get(email);
      long userID = -1L;
      String emailId = null;
      if (lmsUser != null) {
         userID = lmsUser.getId();
         emailId = lmsUser.getProfile().getEmailId();
         DiffUser d = adUser.equals(lmsUser);
         if (d.isUpdated()) {
            log.info("User '" + email + "' is Up-To-Date. NO DIFFERENCE FOUND");
            this.activeUsers.add(email);
            this.updatedUsers.add(email);
            ++this.updatedCount;
         } else {
            this.activeUsers.add(emailId);
            d.setId(userID);
            d.setEmailId(emailId);
            this.diffUsers.add(d);
            ++this.diffUsersCount;
         }
      }

   }

   public void logDiffUsers() {
      int size = this.diffUsers.size();
      if (size > 0) {
         log.info("Now Showing the Details of Users who will be UPDATEd if \"noOps = false\" .");

         for(int i = 0; i < size; ++i) {
            DiffUser diffUser = (DiffUser)this.diffUsers.get(i);
            log.info("EmailId:" + diffUser.getEmailId());
            log.debug("Id:" + diffUser.getId());
            log.info("Difference Index = " + diffUser.getDiffIndex().keySet().toString());
            log.info("If \"noOps = false\" Following Update Will take place : ");
            this.logValues(diffUser);
         }
      }

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

   public List updateLms() {
      LmsApiCallerServiceImpl lmsApiCallerServiceImpl = new LmsApiCallerServiceImpl();
      return lmsApiCallerServiceImpl.updateUsers(this.diffUsers);
   }

   public List addToLms() {
      LmsApiCallerServiceImpl lmsApiCallerServiceImpl = new LmsApiCallerServiceImpl();
      return lmsApiCallerServiceImpl.addUsers(this.newUsers);
   }

   public String getCurrentLocalDateTimeStamp() {
      return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
   }
}
