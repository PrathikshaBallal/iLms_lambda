package com.amazonaws.lambda.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseEnrollment {
   private CourseUserBean User;
   private String CourseName;
   private String CourseID;
   private String SessionName;
   private String SessionID;
   private String RequirementType;
   private String DueDate;
   private String StartDate;
   private String CompletionDate;
   private String Score;
   private String CourseStatus;
   private String EnrollmentDate;
   private String TimeSpent;
   private String LicenseExpirationDate;

   @JsonProperty("LicenseExpirationDate")
   public String getLicenseExpirationDate() {
      return this.LicenseExpirationDate;
   }

   public void setLicenseExpirationDate(String licenseExpirationDate) {
      this.LicenseExpirationDate = licenseExpirationDate;
   }

   @JsonProperty("User")
   public CourseUserBean getUser() {
      return this.User;
   }

   public void setUser(CourseUserBean user) {
      this.User = user;
   }

   @JsonProperty("CourseName")
   public String getCourseName() {
      return this.CourseName;
   }

   public void setCourseName(String courseName) {
      this.CourseName = courseName;
   }

   @JsonProperty("CourseID")
   public String getCourseID() {
      return this.CourseID;
   }

   public void setCourseID(String courseID) {
      this.CourseID = courseID;
   }

   @JsonProperty("SessionName")
   public String getSessionName() {
      return this.SessionName;
   }

   public void setSessionName(String sessionName) {
      this.SessionName = sessionName;
   }

   @JsonProperty("SessionID")
   public String getSessionID() {
      return this.SessionID;
   }

   public void setSessionID(String sessionID) {
      this.SessionID = sessionID;
   }

   @JsonProperty("RequirementType")
   public String getRequirementType() {
      return this.RequirementType;
   }

   public void setRequirementType(String requirementType) {
      this.RequirementType = requirementType;
   }

   @JsonProperty("DueDate")
   public String getDueDate() {
      return this.DueDate;
   }

   public void setDueDate(String dueDate) {
      this.DueDate = dueDate;
   }

   @JsonProperty("StartDate")
   public String getStartDate() {
      return this.StartDate;
   }

   public void setStartDate(String startDate) {
      this.StartDate = startDate;
   }

   @JsonProperty("CompletionDate")
   public String getCompletionDate() {
      return this.CompletionDate;
   }

   public void setCompletionDate(String completionDate) {
      this.CompletionDate = completionDate;
   }

   @JsonProperty("Score")
   public String getScore() {
      return this.Score;
   }

   public void setScore(String score) {
      this.Score = score;
   }

   @JsonProperty("CourseStatus")
   public String getCourseStatus() {
      return this.CourseStatus;
   }

   public void setCourseStatus(String courseStatus) {
      this.CourseStatus = courseStatus;
   }

   @JsonProperty("EnrollmentDate")
   public String getEnrollmentDate() {
      return this.EnrollmentDate;
   }

   public void setEnrollmentDate(String enrollmentDate) {
      this.EnrollmentDate = enrollmentDate;
   }

   @JsonProperty("TimeSpent")
   public String getTimeSpent() {
      return this.TimeSpent;
   }

   public void setTimeSpent(String timeSpent) {
      this.TimeSpent = timeSpent;
   }
}
