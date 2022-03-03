package dev.bredah.utils;

import static org.openqa.selenium.OutputType.BYTES;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.TakesScreenshot;
import dev.bredah.driver.DriverManager;
import io.qameta.allure.Attachment;
public final class Helper {

  private Helper() {}

  public static boolean stringContainsItemFromList(String inputStr, String[] items) {
    return Arrays.stream(items).anyMatch(inputStr::contains);
  }

  public static boolean stringContainsItemFromList(String inputStr, List<String> items) {
    return items.stream().anyMatch(inputStr::equals);
  }

  @Attachment(value = "Failed test screenshot", type = "image/png")
  public static byte[] takeScreenshot() {
    return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(BYTES);
  }

  @Attachment(value = "Browser information", type = "text/plain")
  public static String browserInformation() {
    return DriverManager.getInfo();
  }
}
