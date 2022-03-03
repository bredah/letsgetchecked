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
    var version = capabilities.getBrowserVersion();
    return String.format("browser: %s v.%s", browserName, version);
  }

  public static String getPlatform() {
    var capabilities = ((RemoteWebDriver) DriverManager.getDriver()).getCapabilities();
    return capabilities.getPlatformName().toString().toLowerCase();
  }


}
