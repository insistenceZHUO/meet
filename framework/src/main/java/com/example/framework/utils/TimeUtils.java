package com.example.framework.utils;

public class TimeUtils {
  public static String formatDuring(long ms) {
    long hours = (ms % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
    long minutes = (ms % (1000 * 60 * 60)) / (1000 * 60);
    long seconds = (ms % (1000 * 60)) / (1000);
    return hours + ":" + minutes + ":" + seconds;
  }
}
