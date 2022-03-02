package dev.bredah.driver;

import static dev.bredah.config.ConfigurationFactory.environment;
import java.net.URL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import dev.bredah.exceptions.TargetNotValidException;

public class TargetFactory {
  
  private static final Logger logger = LogManager.getLogger(TargetFactory.class);


  public WebDriver createInstance(String browser) {
    var target = Target.valueOf(environment().target().toUpperCase());
    WebDriver webdriver;

    switch (target) {
      case LOCAL:
        webdriver = BrowserFactory.valueOf(browser.toUpperCase()).createDriver();
        break;
      case REMOTE:
        webdriver = createRemoteInstance(BrowserFactory.valueOf(browser.toUpperCase()).getOptions());
        break;
      default:
        throw new TargetNotValidException(target.toString());
    }
    return webdriver;
  }

  private RemoteWebDriver createRemoteInstance(MutableCapabilities capability) {
    RemoteWebDriver remoteWebDriver = null;
    try {
      String gridURL = String.format("http://%s:%s", 
          environment().gridUrl(), environment().gridPort());
      remoteWebDriver = new RemoteWebDriver(new URL(gridURL), capability);
    } catch (java.net.MalformedURLException e) {
      logger.error("Grid URL is not available");
      logger.error(String.format("Browser: %s", capability.getBrowserName()), e);
    } catch (IllegalArgumentException e) {
      logger.error(String.format("Browser %s is not recognized", capability.getBrowserName()), e);
    }
    return remoteWebDriver;
  }

  enum Target {
    LOCAL, REMOTE
  }
}
