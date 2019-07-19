package com.amazonaws.lambda.domain;

import java.util.ArrayList;
import java.util.List;

public class UpdatedUsers {
   private List users = new ArrayList();

   public void add(String email) {
      this.users.add(email);
   }

   public List getUsers() {
      return this.users;
   }

   public void setUsers(List users) {
      this.users = users;
   }
}
