package com.amazonaws.lambda.dao;

import java.util.ArrayList;
import java.util.List;

public interface Comparator {
   void compare(ArrayList var1);

   void setLmsUsers();

   void compareAndSet(ArrayList var1);

   void logDiffUsers();

   List updateLms();
}
