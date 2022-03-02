package dev.bredah.utils;

import java.util.Arrays;
import java.util.List;

public final class Helper {

  private Helper() {}

  public static boolean stringContainsItemFromList(String inputStr, String[] items) {
    return Arrays.stream(items).anyMatch(inputStr::contains);
  }

  public static boolean stringContainsItemFromList(String inputStr, List<String> items) {
    return items.stream().anyMatch(inputStr::equals);
  }
}
