package com.amazonaws.lambda.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdatedJson {
   private String Key;
   private String Value;

   @JsonProperty("Key")
   public String getKey() {
      return this.Key;
   }

   public void setKey(String key) {
      this.Key = key;
   }

   @JsonProperty("Value")
   public String getValue() {
      return this.Value;
   }

   public void setValue(String value) {
      this.Value = value;
   }
}
