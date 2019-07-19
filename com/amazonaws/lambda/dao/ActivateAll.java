package com.amazonaws.lambda.dao;

import com.amazonaws.lambda.common.RequestMethod;
import com.amazonaws.lambda.dao.impl.GetConnection;
import com.amazonaws.lambda.dao.impl.LmsApiCallerImpl;
import com.amazonaws.lambda.domain.LmsUser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

public class ActivateAll {
   public static void activate_user(long id) {
      String ilmsUrl = "https://api.inspiredlms.com/organizations/2424/users/" + id + "/Activate";

      try {
         HttpURLConnection conn = GetConnection.getConnection(ilmsUrl, RequestMethod.PUT);
         if (conn.getResponseCode() != 204) {
            System.out.println("Failed : HTTP error code : " + conn.getResponseCode());
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
         }

         System.out.println("Activated " + id);
         conn.disconnect();
      } catch (IOException var4) {
         System.out.println(var4);
      }

   }

   public static void main(String[] args) {
      LmsApiCallerImpl lmsApiCallerImpl = new LmsApiCallerImpl();
      Map usersAll = lmsApiCallerImpl.getAllUsers();
      String csvFile = "/Users/sanjeet.roy/projects/ilms/csvs/inac.csv";
      String line = "";
      String cvsSplitBy = ",";
      System.out.println("gl = " + usersAll.size());
      System.out.println(((LmsUser)usersAll.get("produte.roy@appdirect.com")).getId());

      try {
         Throwable var6 = null;
         Object var7 = null;

         try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));

            try {
               line = br.readLine();
               String[] users = line.split(cvsSplitBy);
               System.out.println(line);
               System.out.println(users.length);
               String[] var13 = users;
               int var12 = users.length;

               for(int var11 = 0; var11 < var12; ++var11) {
                  String user = var13[var11];
                  LmsUser lmsUser = (LmsUser)usersAll.get(user.trim());
                  System.out.println(user + " = " + lmsUser.getId());

                  try {
                     activate_user(lmsUser.getId());
                  } catch (RuntimeException var24) {
                     System.out.println("User " + user + " is already activated");
                  }
               }
            } finally {
               if (br != null) {
                  br.close();
               }

            }
         } catch (Throwable var26) {
            if (var6 == null) {
               var6 = var26;
            } else if (var6 != var26) {
               var6.addSuppressed(var26);
            }

            throw var6;
         }
      } catch (IOException var27) {
         var27.printStackTrace();
      }

   }
}
