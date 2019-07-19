package com.amazonaws.lambda.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DefaultDueDateSettings {
   private String DefaultDueDate;
   private String DaysAfterEnrollment;

   @JsonProperty("DefaultDueDate")
   public String getDefaultDueDate() {
      return this.DefaultDueDate;
   }

   public void setDefaultDueDate(String defaultDueDate) {
      this.DefaultDueDate = defaultDueDate;
   }

   @JsonProperty("DaysAfterEnrollment")
   public String getDaysAfterEnrollment() {
      return this.DaysAfterEnrollment;
   }

   public void setDaysAfterEnrollment(String daysAfterEnrollment) {
      this.DaysAfterEnrollment = daysAfterEnrollment;
   }
}
