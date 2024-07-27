package com.workdance.chatbot.utils;

public class StringUtil {
  public static boolean equals(String str1, String str2) {
    return str1 == null ? str2 == null : str1.equals(str2);
  }

  public static String substring(String str, int start) {
    if (str == null) {
      return null;
    } else {
      if (start < 0) {
        start += str.length();
      }

      if (start < 0) {
        start = 0;
      }

      return start > str.length() ? "" : str.substring(start);
    }
  }

  public static Boolean isEmpty(String str) {
    return str == null || str.isEmpty();
  }
}
