package com.amazonaws.lambda.util;

import com.amazonaws.lambda.domain.AdUser;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CsvHandler {
   static final Logger log = LogManager.getLogger(CsvHandler.class);

   public ArrayList parse(BufferedReader br) {
      ArrayList adUsers = new ArrayList();

      try {
         BeanListProcessor rowProcessor = new BeanListProcessor(AdUser.class);
         CsvParserSettings parserSettings = new CsvParserSettings();
         parserSettings.setRowProcessor(rowProcessor);
         parserSettings.setHeaderExtractionEnabled(true);
         CsvParser parser = new CsvParser(parserSettings);
         parser.parse(br);
         adUsers = new ArrayList(rowProcessor.getBeans());
      } catch (Exception var14) {
         log.error("Exception: " + var14);
      } finally {
         try {
            if (br != null) {
               br.close();
            }
         } catch (IOException var13) {
            log.error("Exception: " + var13);
         }

      }

      return adUsers;
   }
}
