package com.amazonaws.lambda.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Course {
   private String ID;
   private String Name;
   private String Type;
   private String DeliveryType;
   private String DefaultRequirementType;
   private String Credits;
   private String Hours;
   private String Status;
   private DefaultDueDateSettings DefaultDueDateSettings;

   @JsonProperty("ID")
   public String getID() {
      return this.ID;
   }

   public void setID(String iD) {
      this.ID = iD;
   }

   @JsonProperty("Name")
   public String getName() {
      return this.Name;
   }

   public void setName(String name) {
      this.Name = name;
   }

   @JsonProperty("Type")
   public String getType() {
      return this.Type;
   }

   public void setType(String type) {
      this.Type = type;
   }

   @JsonProperty("DeliveryType")
   public String getDeliveryType() {
      return this.DeliveryType;
   }

   public void setDeliveryType(String deliveryType) {
      this.DeliveryType = deliveryType;
   }

   @JsonProperty("DefaultRequirementType")
   public String getDefaultRequirementType() {
      return this.DefaultRequirementType;
   }

   public void setDefaultRequirementType(String defaultRequirementType) {
      this.DefaultRequirementType = defaultRequirementType;
   }

   @JsonProperty("Credits")
   public String getCredits() {
      return this.Credits;
   }

   public void setCredits(String credits) {
      this.Credits = credits;
   }

   @JsonProperty("Hours")
   public String getHours() {
      return this.Hours;
   }

   public void setHours(String hours) {
      this.Hours = hours;
   }

   @JsonProperty("Status")
   public String getStatus() {
      return this.Status;
   }

   public void setStatus(String status) {
      this.Status = status;
   }

   @JsonProperty("DefaultDueDateSettings")
   public DefaultDueDateSettings getDefaultDueDateSettings() {
      return this.DefaultDueDateSettings;
   }

   public void setDefaultDueDateSettings(DefaultDueDateSettings defaultDueDateSettings) {
      this.DefaultDueDateSettings = defaultDueDateSettings;
   }
}
