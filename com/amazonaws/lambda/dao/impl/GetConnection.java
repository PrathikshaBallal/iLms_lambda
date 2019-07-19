package com.amazonaws.lambda.dao.impl;

import com.amazonaws.lambda.common.RequestMethod;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetConnection {
   static final Logger log = LogManager.getLogger(GetConnection.class);

   public static HttpURLConnection getConnection(String ilmsUrl, RequestMethod method) {
      HttpURLConnection conn = null;
      FileReader fr = null;
      Object var4 = null;

      try {
         String test_env = System.getenv("test");
         log.debug("ENVORINMENT=" + test_env);
         String apiId = "1T0vVqIwg0LO/R9Rnfzd6A==";
         String key = "aTxgwb8LW2SLVxbOWAFfeQ==";
         String orgId = "2424";
         URL url = new URL(ilmsUrl);
         conn = (HttpURLConnection)url.openConnection();
         conn.setRequestMethod(method.toString());
         conn.setRequestProperty("Content-Type", "application/json");
         conn.setRequestProperty("Accept", "application/json");
         conn.setRequestProperty("APILoginID", apiId);
         conn.setRequestProperty("TransactionKey", key);
         conn.setRequestProperty("OrgID", orgId);
      } catch (MalformedURLException var10) {
         log.error("Exception " + var10);
      } catch (IOException var11) {
         log.error("Exception " + var11);
      }

      return conn;
   }

   public static long getOrgId() {
      long orgId = 2424L;
      return orgId;
   }
}
