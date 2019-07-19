package com.amazonaws.lambda.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogResponseCode {
   static final Logger log = LogManager.getLogger(LogResponseCode.class);

   public static void log(int code) {
      switch(code) {
      case 200:
         log.debug("200: The request is successful");
         break;
      case 201:
         log.debug("201: new resource is created");
         break;
      case 204:
         log.debug("204: request has been successfully processed");
         break;
      case 400:
         log.debug("400: request doesn't have all the parameters which is required to fulfill a request. Or it has invalid data.");
         break;
      case 401:
         log.debug("401: Incorrect credentials information");
         break;
      case 403:
         log.debug("403: feature is not available to the organization");
         break;
      case 404:
         log.debug("404: Invalid URI. The resource you're looking for is not available or doesn't exist.");
         break;
      case 415:
         log.debug("415: iLMS API only supports \"Application/JSON\" media type");
         break;
      case 500:
         log.debug("500: Error occurred at Server Side");
         break;
      case 503:
         log.debug("503: System might be temporary down due to up-gradation. Or your request can't be accepted at that point of time.");
      }

   }
}
