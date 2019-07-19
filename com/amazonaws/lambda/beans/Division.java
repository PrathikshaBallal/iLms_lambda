package com.amazonaws.lambda.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Division {
   private long ID;
   private String Name;
   private String NumberOfUsers;

   @JsonProperty("ID")
   public long getID() {
      return this.ID;
   }

   public void setID(long iD) {
      this.ID = iD;
   }

   @JsonProperty("Name")
   public String getName() {
      return this.Name;
   }

   public void setName(String name) {
      this.Name = name;
   }

   @JsonProperty("NumberOfUsers")
   public String getNumberOfUsers() {
      return this.NumberOfUsers;
   }

   public void setNumberOfUsers(String numberOfUsers) {
      this.NumberOfUsers = numberOfUsers;
   }
}
