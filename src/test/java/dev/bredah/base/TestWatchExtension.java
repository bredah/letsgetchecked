package dev.bredah.base;

import static dev.bredah.config.ConfigurationFactory.environment;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;
import dev.bredah.driver.DriverManager;
import dev.bredah.driver.TargetFactory;
import dev.bredah.report.AllurePropertiesFile;
import dev.bredah.utils.Helper;

public class TestWatchExtension
    implements TestWatcher, AfterAllCallback, BeforeAllCallback, AfterEachCallback, BeforeEachCallback {

  private static final Logger logger = LogManager.getLogger(TestWatchExtension.class);

  @Override
  public void beforeAll(ExtensionContext context) throws Exception {
    WebDriver driver = new TargetFactory().createInstance(environment().browser());
    DriverManager.setDriver(driver);
  }

  @Override
  public void afterAll(ExtensionContext context) throws Exception {
    DriverManager.quit();
    AllurePropertiesFile.build();
  }

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    Helper.browserInformation();
  }
  
  @Override
  public void afterEach(ExtensionContext context) throws Exception {
    DriverManager.getDriver().manage().deleteAllCookies();
  }

  @Override
  public void testSuccessful(ExtensionContext context) {
    logger.info("Test {} - status: successful", context.getDisplayName());
  }

  @Override
  public void testDisabled(ExtensionContext context, Optional<String> reason) {
    logger.info("Test {} - status: disabled - reason: {}", context.getDisplayName(),
        reason.toString());
  }

  @Override
  public void testAborted(ExtensionContext context, Throwable cause) {
    logger.info("Test {} - status: aborted - reason: {}", context.getDisplayName(),
        cause.getMessage());
  }

  @Override
  public void testFailed(ExtensionContext context, Throwable cause) {
    logger.info("Test {} - status: failed - reason: {}", context.getDisplayName(),
    cause.getMessage());
    Helper.takeScreenshot();
  }

  

}
