package dev.bredah.page.google;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import dev.bredah.page.PageObject;

public class GoogleMapsPage extends PageObject {

  @FindBy(xpath = "//span[text()='I agree']")
  private WebElement acceptAgreementButton;

  @FindBy(id = "searchboxinput")
  private WebElement searchInput;

  @FindBy(id = "searchbox-searchbutton")
  private WebElement searchButton;

  @FindBy(xpath = "//h1")
  private WebElement mainLabel;

  @FindBy(css = "div[class*='title'] > h2")
  private List<WebElement> moreInfoLabels;

  public void acceptAgreement() {
    waitUntil(ExpectedConditions.elementToBeClickable(this.acceptAgreementButton));
    this.acceptAgreementButton.click();
  }

  public void find(String context) {
    this.searchInput.sendKeys(context);
    this.searchButton.click();
  }

  public String getMainInfo() {
    waitUntil(ExpectedConditions.visibilityOf(this.mainLabel));
    return this.mainLabel.getText();
  }

  public List<String> getMoreInfo() {
    return this.moreInfoLabels.stream().map(WebElement::getText).collect(Collectors.toList());
  }

}
