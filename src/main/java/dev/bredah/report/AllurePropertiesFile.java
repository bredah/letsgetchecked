package dev.bredah.report;

import static dev.bredah.config.ConfigurationFactory.environment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;
import dev.bredah.driver.DriverManager;

public class AllurePropertiesFile {

  private final Properties properties = new Properties();

  private AllurePropertiesFile addProperty(String key, String value) {
    if (!StringUtils.isEmpty(value)) {
      this.properties.setProperty(key, value);
    }
    return this;
  }

  private void createPropertyFile() throws IOException {
    String fileName = "environment.properties";
    File filePath = new File(System.getProperty("user.dir") + "/target/allure-results");
    if (!filePath.exists()) {
      filePath.mkdirs();
    }
    try (OutputStream outputStream =
        new FileOutputStream(String.format("%s%s%s", filePath, File.separator, fileName))) {
      properties.store(outputStream, null);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void build() throws IOException {
    new AllurePropertiesFile().addProperty("Test URL", environment().url())
        .addProperty("Platform", DriverManager.getPlatform())
        .addProperty("Target execution", environment().target())
        .addProperty("Global timeout", String.valueOf(environment().timeout()))
        .addProperty("Grid URL", environment().gridUrl())
        .addProperty("Grid port", environment().gridPort()).createPropertyFile();
  }
}

