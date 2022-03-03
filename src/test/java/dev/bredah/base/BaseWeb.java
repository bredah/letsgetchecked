package dev.bredah.base;

import static dev.bredah.config.ConfigurationFactory.environment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import dev.bredah.driver.DriverManager;

@ExtendWith(TestWatchExtension.class)
public class BaseWeb {

  @BeforeAll
  static void init() {
    // Allure

    // AllureEnvironmentWriter.allureEnvironmentWriter(ImmutableMap.<String, String>builder()
    // .put("Test URL", environment().url()).put("Target execution", environment().target())
    // .put("Global timeout", String.valueOf(environment().timeout()))
    // .put("Browser", environment().browser()).put("Grid URL", environment().gridUrl())
    // .put("Grid port", environment().gridPort()).build());
  }

  @BeforeEach
  void preCondition() {
    DriverManager.getDriver().get(environment().url());
  }

  @AfterEach
  void postCondition() {}

}
