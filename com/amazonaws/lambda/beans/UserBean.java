package com.amazonaws.lambda.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserBean {
   private long ID;
   private ProfileBean Profile;
   private String UserStatus;

   @JsonProperty("ID")
   public long getID() {
      return this.ID;
   }

   public void setID(long iD) {
      this.ID = iD;
   }

   @JsonProperty("Profile")
   public ProfileBean getProfile() {
      return this.Profile;
   }

   public void setProfile(ProfileBean profile) {
      this.Profile = profile;
   }

   @JsonProperty("UserStatus")
   public String getUserStatus() {
      return this.UserStatus;
   }

   public void setUserStatus(String userStatus) {
      this.UserStatus = userStatus;
   }
}
