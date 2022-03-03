package dev.bredah.driver;

import static dev.bredah.config.ConfigurationFactory.environment;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.AbstractDriverOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;

public enum BrowserFactory {
  CHROME {
    @Override
    public WebDriver createDriver() {
      WebDriverManager.getInstance(DriverManagerType.CHROME)
          .setup();
      return new ChromeDriver(getOptions());
    }

    @Override
    public ChromeOptions getOptions() {
      var chromeOptions = new ChromeOptions();
      chromeOptions.addArguments(START_MAXIMIZED);
      chromeOptions.addArguments("--disable-infobars");
      chromeOptions.addArguments("--disable-notifications");
      chromeOptions.setHeadless(environment().headless());
      return chromeOptions;
    }
  },
  FIREFOX {
    @Override
    public WebDriver createDriver() {
      WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
      return new FirefoxDriver(getOptions());
    }

    @Override
    public FirefoxOptions getOptions() {
      var profile = new FirefoxProfile();
      profile.setPreference("intl.accept_languages", "en");
      var firefoxOptions = new FirefoxOptions();
      firefoxOptions.addArguments(START_MAXIMIZED);
      firefoxOptions.setHeadless(environment().headless());
      firefoxOptions.setProfile(profile);
      return firefoxOptions;
    }
  };


  private static final String START_MAXIMIZED = "--start-maximized";

  public abstract WebDriver createDriver();

  public abstract AbstractDriverOptions<?> getOptions();

}
