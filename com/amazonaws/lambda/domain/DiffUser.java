package com.amazonaws.lambda.domain;

import java.util.Map;

public class DiffUser {
   boolean updated;
   long id;
   String emailId;
   Map diffIndex;
   Map diffIndexOld;

   public long getId() {
      return this.id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getEmailId() {
      return this.emailId != null ? this.emailId.trim().replaceAll(" +", " ") : this.emailId;
   }

   public void setEmailId(String emailId) {
      this.emailId = emailId;
   }

   public Map getDiffIndex() {
      return this.diffIndex;
   }

   public void setDiffIndex(Map diffIndex) {
      this.diffIndex = diffIndex;
   }

   public Map getDiffIndexOld() {
      return this.diffIndexOld;
   }

   public void setDiffIndexOld(Map diffIndexOld) {
      this.diffIndexOld = diffIndexOld;
   }

   public void setUpdated(boolean updated) {
      this.updated = updated;
   }

   public boolean isUpdated() {
      return this.updated;
   }
}
