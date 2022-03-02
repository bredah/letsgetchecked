package dev.bredah.page;

import static dev.bredah.config.ConfigurationFactory.environment;
import static org.openqa.selenium.support.PageFactory.initElements;
import java.time.Duration;
import java.util.regex.Pattern;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import dev.bredah.driver.DriverManager;

public abstract class PageObject {

  protected PageObject() {
    initElements(new AjaxElementLocatorFactory(DriverManager.getDriver(), environment().timeout()),
        this);
  }

  protected void mouseHover(WebElement element) {
    var action = new Actions(DriverManager.getDriver());
    action.moveToElement(element).perform();
  }

  protected void selectByVisibleText(WebElement element, String text) {
    var selecteElement = new Select(element);
    selecteElement.selectByVisibleText(text);
  }

  protected void selectByValue(WebElement element, String text) {
    var selecteElement = new Select(element);
    selecteElement.selectByValue(text);
  }

  protected void waitUntil(ExpectedCondition<WebElement> expectedConditions) {
    new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10)).until(expectedConditions);
  }

  protected String regexExtractValue(String regex, String text) {
    var pattern = Pattern.compile(regex);
    var matcher = pattern.matcher(text);
    if (matcher.find()) {
      return matcher.group(0);
    }
    return "";
  }
}

