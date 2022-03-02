package dev.bredah.report;

import static dev.bredah.config.ConfigurationFactory.environment;
import static org.openqa.selenium.OutputType.BYTES;
import com.github.automatedowl.tools.AllureEnvironmentWriter;
import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.TakesScreenshot;
import dev.bredah.driver.DriverManager;
import io.qameta.allure.Attachment;

public class AllureReport {

  private AllureReport() {}

  public static void setAllureEnvironmentInformation() {
    AllureEnvironmentWriter.allureEnvironmentWriter(ImmutableMap.<String, String>builder()
        .put("Test URL", environment().url()).put("Target execution", environment().target())
        .put("Global timeout", String.valueOf(environment().timeout()))
        .put("Browser", environment().browser()).put("Grid URL", environment().gridUrl())
        .put("Grid port", environment().gridPort()).build());
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
