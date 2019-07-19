package com.amazonaws.lambda.domain;

import com.univocity.parsers.annotations.Parsed;
import java.util.HashMap;
import java.util.Map;

public class AdUser {
   @Parsed(
      field = "First Name",
      defaultNullRead = ""
   )
   private String firstName;
   @Parsed(
      field = "Last Name",
      defaultNullRead = ""
   )
   private String lastName;
   @Parsed(
      field = "Full Name",
      defaultNullRead = ""
   )
   private String FullName;
   @Parsed(
      field = "Email - Work",
      defaultNullRead = ""
   )
   private String emailId;
   @Parsed(
      field = "Hire Date"
   )
   private String hireDate;
   @Parsed(
      field = "Employee Sub-Type",
      defaultNullRead = ""
   )
   private String employeeType;
   @Parsed(
      field = "Cost Center",
      defaultNullRead = "AppDirect Default Department"
   )
   private String department;
   @Parsed(
      field = "Business Title",
      defaultNullRead = ""
   )
   private String jobTitle;
   @Parsed(
      field = "Division",
      defaultNullRead = "AppDirect Default Division"
   )
   private String division;
   @Parsed(
      field = "Employee's Manager",
      defaultNullRead = ""
   )
   private String supervisorName;
   @Parsed(
      field = "Manager's Email",
      defaultNullRead = ""
   )
   private String supervisorEmail;
   @Parsed(
      field = "Location",
      defaultNullRead = ""
   )
   private String city;
   @Parsed(
      field = "Office Country",
      defaultNullRead = ""
   )
   private String country;
   @Parsed(
      field = "Addresses",
      defaultNullRead = ""
   )
   private String address1;

   public DiffUser equals(LmsUser lmsUser) {
      Map list = new HashMap();
      Map listOld = new HashMap();
      DiffUser diffUser = new DiffUser();
      Profile lmsProfile = lmsUser.getProfile();
      boolean result = true;
      if (!this.getSupervisorName().equals(lmsProfile.getSupervisorName())) {
         list.put("supervisorName", this.getSupervisorName());
         listOld.put("supervisorName", lmsProfile.getSupervisorName());
         result = false;
      }

      if (!this.getSupervisorEmail().equals(lmsProfile.getSupervisorEmail())) {
         list.put("supervisorEmail", this.getSupervisorEmail());
         listOld.put("supervisorEmail", lmsProfile.getSupervisorEmail());
         result = false;
      }

      diffUser.setUpdated(result);
      diffUser.setDiffIndex(list);
      diffUser.setDiffIndexOld(listOld);
      return diffUser;
   }

   public String getFirstName() {
      return this.firstName != null ? this.firstName.trim().replaceAll(" +", " ") : this.firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return this.lastName != null ? this.lastName.trim().replaceAll(" +", " ") : this.lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmailId() {
      return this.emailId != null ? this.emailId.trim().replaceAll(" +", " ") : this.emailId;
   }

   public void setEmailId(String emailId) {
      this.emailId = emailId;
   }

   public String getHireDate() {
      return this.hireDate != null ? this.hireDate.trim().replaceAll(" +", " ") : this.hireDate;
   }

   public void setHireDate(String hireDate) {
      this.hireDate = hireDate;
   }

   public String getEmployeeType() {
      return this.employeeType != null ? this.employeeType.trim().replaceAll(" +", " ") : this.employeeType;
   }

   public void setEmployeeType(String employeeType) {
      this.employeeType = employeeType;
   }

   public String getDepartment() {
      return this.department != null ? this.department.trim().replaceAll(" +", " ") : this.department;
   }

   public void setDepartment(String department) {
      this.department = department;
   }

   public String getJobTitle() {
      return this.jobTitle != null ? this.jobTitle.trim().replaceAll(" +", " ") : this.jobTitle;
   }

   public void setJobTitle(String jobTitle) {
      this.jobTitle = jobTitle;
   }

   public String getDivision() {
      return this.division != null ? this.division.trim().replaceAll(" +", " ") : this.division;
   }

   public void setDivision(String division) {
      this.division = division;
   }

   public String getSupervisorName() {
      return this.supervisorName != null ? this.supervisorName.trim().replaceAll(" +", " ") : this.supervisorName;
   }

   public void setSupervisorName(String supervisorName) {
      this.supervisorName = supervisorName;
   }

   public String getSupervisorEmail() {
      return this.supervisorEmail != null ? this.supervisorEmail.trim().replaceAll(" +", " ") : this.supervisorEmail;
   }

   public void setSupervisorEmail(String supervisorEmail) {
      this.supervisorEmail = supervisorEmail;
   }

   public String getCity() {
      return this.city != null ? this.city.trim().replaceAll(" +", " ") : this.city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public String getCountry() {
      return this.country != null ? this.country.trim().replaceAll(" +", " ") : this.country;
   }

   public void setCountry(String country) {
      this.country = country;
   }

   public String getAddress1() {
      return this.address1 != null ? this.address1.trim().replaceAll(" +", " ") : this.address1;
   }

   public void setAddress1(String address1) {
      this.address1 = address1;
   }
}
