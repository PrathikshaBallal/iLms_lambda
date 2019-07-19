package com.amazonaws.lambda.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseUserBean {
   private String ID;
   private String UserStatus;
   private CourseProfileBean Profile_Basic;

   @JsonProperty("ID")
   public String getID() {
      return this.ID;
   }

   public void setID(String iD) {
      this.ID = iD;
   }

   @JsonProperty("UserStatus")
   public String getUserStatus() {
      return this.UserStatus;
   }

   public void setUserStatus(String userStatus) {
      this.UserStatus = userStatus;
   }

   @JsonProperty("Profile_Basic")
   public CourseProfileBean getProfile_Basic() {
      return this.Profile_Basic;
   }

   public void setProfile_Basic(CourseProfileBean profile_Basic) {
      this.Profile_Basic = profile_Basic;
   }
}
