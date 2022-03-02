package dev.bredah;

import static dev.bredah.config.ConfigurationFactory.environment;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import dev.bredah.driver.DriverManager;
import dev.bredah.driver.TargetFactory;
import dev.bredah.report.AllureReport;

@Listeners({TestListener.class})
public abstract class BaseWeb {

  @BeforeSuite
  public void beforeSuite() {
    AllureReport.setAllureEnvironmentInformation();
  }

  @BeforeMethod(alwaysRun = true)
  @Parameters("browser")
  public void preCondition(@Optional("chrome") String browser) {
    WebDriver driver = new TargetFactory().createInstance(browser);
    DriverManager.setDriver(driver);

    DriverManager.getDriver().get(environment().url());
  }

  @AfterMethod(alwaysRun = true)
  public void postCondition() {
    DriverManager.quit();
  }

}
