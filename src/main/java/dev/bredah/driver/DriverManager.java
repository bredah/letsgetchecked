package dev.bredah.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager {

  private static WebDriver driver;

  private DriverManager() {}

  public static WebDriver getDriver() {
    return DriverManager.driver;
  }

  public static void setDriver(WebDriver driver) {
    DriverManager.driver = driver;
  }

  public static void quit() {
    DriverManager.driver.quit();
  }

  public static String getInfo() {
    var capabilities = ((RemoteWebDriver) DriverManager.getDriver()).getCapabilities();
    String browserName = capabilities.getBrowserName();
    var platform = capabilities.getPlatformName().toString();
    return String.format("browser: %s platform: %s", browserName, platform);
  }

}
