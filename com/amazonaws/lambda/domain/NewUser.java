package com.amazonaws.lambda.domain;

import java.util.Map;

public class NewUser {
   private String emailId;
   private Map profileFields;

   public String getEmailId() {
      return this.emailId != null ? this.emailId.trim().replaceAll(" +", " ") : this.emailId;
   }

   public void setEmailId(String emailId) {
      this.emailId = emailId;
   }

   public Map getProfileFields() {
      return this.profileFields;
   }

   public void setProfileFields(Map profileFields) {
      this.profileFields = profileFields;
   }
}
