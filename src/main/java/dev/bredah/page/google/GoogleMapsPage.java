package dev.bredah.page.google;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import dev.bredah.page.PageObject;
import io.qameta.allure.Step;

public class GoogleMapsPage extends PageObject {

  @FindBy(xpath = "//form//button/span")
  private WebElement acceptAgreementButton;

  @FindBy(id = "searchboxinput")
  private WebElement searchInput;

  @FindBy(id = "searchbox-searchbutton")
  private WebElement searchButton;

  @FindBy(xpath = "//div[@id='pane']//img[contains(@src, 'directions')]")
  private WebElement routesButton;

  @FindBy(xpath = "//h1")
  private WebElement mainLabel;

  @FindBy(xpath = "//h2/span")
  private List<WebElement> moreInfoLabels;

  @FindBy(xpath = "//div[contains(@id, 'directions-searchbox-1')]//input")
  private WebElement directionToInput;

  @Step("Accept agreement")
  public void acceptAgreement() {
    waitUntil(ExpectedConditions.elementToBeClickable(this.acceptAgreementButton));
    this.acceptAgreementButton.click();
  }

  @Step("Find a place: {context}")
  public void find(String context) {
    this.searchInput.sendKeys(context);
    this.searchButton.click();
  }

  @Step("Get route")
  public void getRoute() {
    waitUntil(ExpectedConditions.elementToBeClickable(this.routesButton));
    this.routesButton.click();
  }

  public String getDirectionToContent() {
    waitUntil(ExpectedConditions.visibilityOf(this.directionToInput));
    return this.directionToInput.getAttribute("aria-label").replace("Destination", "")
        .replaceAll("\\s+", "");
  }


  public String getMainInfo() {
    waitUntil(ExpectedConditions.visibilityOf(this.mainLabel));
    return this.mainLabel.getText();
  }

  public List<String> getMoreInfo() {
    return this.moreInfoLabels.stream().map(WebElement::getText).collect(Collectors.toList());
  }

}
