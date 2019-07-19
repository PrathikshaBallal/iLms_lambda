package com.amazonaws.lambda.domain;

import com.univocity.parsers.annotations.Parsed;

public class Profile {
   @Parsed(
      field = "First Name",
      defaultNullRead = ""
   )
   private String firstName;
   private String middleName;
   private String lastName;
   private String employeeId;
   private String hireDate;
   private String userLanguage;
   private String jobTitle;
   private String address1;
   private String address2;
   private String city;
   private String country;
   private String state;
   private String province;
   private String zipCode;
   private String emailId;
   private String password;
   private String phoneNo;
   private String faxNo;
   private String region;
   private String regionId;
   private String division;
   private String divisionId;
   private String department;
   private String departmentId;
   private String company;
   private String timeZone;
   private String customField1;
   private String customField2;
   private String customField3;
   private String customField4;
   private String registrationType;
   private String registrationDate;
   private String supervisorName;
   private String supervisorEmail;

   public String getFirstName() {
      return this.firstName != null ? this.firstName.trim().replaceAll(" +", " ") : this.firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getMiddleName() {
      return this.middleName != null ? this.middleName.trim().replaceAll(" +", " ") : this.middleName;
   }

   public void setMiddleName(String middleName) {
      this.middleName = middleName;
   }

   public String getLastName() {
      return this.lastName != null ? this.lastName.trim().replaceAll(" +", " ") : this.lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmployeeId() {
      return this.employeeId != null ? this.employeeId.trim().replaceAll(" +", " ") : this.employeeId;
   }

   public void setEmployeeId(String employeeId) {
      this.employeeId = employeeId;
   }

   public String getHireDate() {
      return this.hireDate != null ? this.hireDate.trim().replaceAll(" +", " ") : this.hireDate;
   }

   public void setHireDate(String hireDate) {
      this.hireDate = hireDate;
   }

   public String getUserLanguage() {
      return this.userLanguage != null ? this.userLanguage.trim().replaceAll(" +", " ") : this.userLanguage;
   }

   public void setUserLanguage(String userLanguage) {
      this.userLanguage = userLanguage;
   }

   public String getJobTitle() {
      return this.jobTitle != null ? this.jobTitle.trim().replaceAll(" +", " ") : this.jobTitle;
   }

   public void setJobTitle(String jobTitle) {
      this.jobTitle = jobTitle;
   }

   public String getAddress1() {
      return this.address1 != null ? this.address1.trim().replaceAll(" +", " ") : this.address1;
   }

   public void setAddress1(String address1) {
      this.address1 = address1;
   }

   public String getAddress2() {
      return this.address2 != null ? this.address2.trim().replaceAll(" +", " ") : this.address2;
   }

   public void setAddress2(String address2) {
      this.address2 = address2;
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

   public String getState() {
      return this.state != null ? this.state.trim().replaceAll(" +", " ") : this.state;
   }

   public void setState(String state) {
      this.state = state;
   }

   public String getProvince() {
      return this.province != null ? this.province.trim().replaceAll(" +", " ") : this.province;
   }

   public void setProvince(String province) {
      this.province = province;
   }

   public String getZipCode() {
      return this.zipCode != null ? this.zipCode.trim().replaceAll(" +", " ") : this.zipCode;
   }

   public void setZipCode(String zipCode) {
      this.zipCode = zipCode;
   }

   public String getEmailId() {
      return this.emailId != null ? this.emailId.trim().replaceAll(" +", " ") : this.emailId;
   }

   public void setEmailId(String emailId) {
      this.emailId = emailId;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getPhoneNo() {
      return this.phoneNo != null ? this.phoneNo.trim().replaceAll(" +", " ") : this.phoneNo;
   }

   public void setPhoneNo(String phoneNo) {
      this.phoneNo = phoneNo;
   }

   public String getFaxNo() {
      return this.faxNo != null ? this.faxNo.trim().replaceAll(" +", " ") : this.faxNo;
   }

   public void setFaxNo(String faxNo) {
      this.faxNo = faxNo;
   }

   public String getRegion() {
      return this.region != null ? this.region.trim().replaceAll(" +", " ") : this.region;
   }

   public void setRegion(String region) {
      this.region = region;
   }

   public String getRegionId() {
      return this.regionId != null ? this.regionId.trim().replaceAll(" +", " ") : this.regionId;
   }

   public void setRegionId(String regionId) {
      this.regionId = regionId;
   }

   public String getDivision() {
      return this.division != null ? this.division.trim().replaceAll(" +", " ") : this.division;
   }

   public void setDivision(String division) {
      this.division = division;
   }

   public String getDivisionId() {
      return this.divisionId != null ? this.divisionId.trim().replaceAll(" +", " ") : this.divisionId;
   }

   public void setDivisionId(String divisionId) {
      this.divisionId = divisionId;
   }

   public String getDepartment() {
      return this.department != null ? this.department.trim().replaceAll(" +", " ") : this.department;
   }

   public void setDepartment(String department) {
      this.department = department;
   }

   public String getDepartmentId() {
      return this.departmentId != null ? this.departmentId.trim().replaceAll(" +", " ") : this.departmentId;
   }

   public void setDepartmentId(String departmentId) {
      this.departmentId = departmentId;
   }

   public String getCompany() {
      return this.company != null ? this.company.trim().replaceAll(" +", " ") : this.company;
   }

   public void setCompany(String company) {
      this.company = company;
   }

   public String getTimeZone() {
      return this.timeZone != null ? this.timeZone.trim().replaceAll(" +", " ") : this.timeZone;
   }

   public void setTimeZone(String timeZone) {
      this.timeZone = timeZone;
   }

   public String getCustomField1() {
      return this.customField1 != null ? this.customField1.trim().replaceAll(" +", " ") : this.customField1;
   }

   public void setCustomField1(String customField1) {
      this.customField1 = customField1;
   }

   public String getCustomField2() {
      return this.customField2 != null ? this.customField2.trim().replaceAll(" +", " ") : this.customField2;
   }

   public void setCustomField2(String customField2) {
      this.customField2 = customField2;
   }

   public String getCustomField3() {
      return this.customField3 != null ? this.customField3.trim().replaceAll(" +", " ") : this.customField3;
   }

   public void setCustomField3(String customField3) {
      this.customField3 = customField3;
   }

   public String getCustomField4() {
      return this.customField4 != null ? this.customField4.trim().replaceAll(" +", " ") : this.customField4;
   }

   public void setCustomField4(String customField4) {
      this.customField4 = customField4;
   }

   public String getRegistrationType() {
      return this.registrationType != null ? this.registrationType.trim().replaceAll(" +", " ") : this.registrationType;
   }

   public void setRegistrationType(String registrationType) {
      this.registrationType = registrationType;
   }

   public String getRegistrationDate() {
      return this.registrationDate != null ? this.registrationDate.trim().replaceAll(" +", " ") : this.registrationDate;
   }

   public void setRegistrationDate(String registrationDate) {
      this.registrationDate = registrationDate;
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
}
