package ui.page_objects;

import org.openqa.selenium.By;

import static ui.ui_utils.Shared.clickOnElement;
import static ui.ui_utils.Shared.sendTextToElement;

public class CheckOutPage {

    private final By changeAddressButtonLocator = By.linkText("Change");
    private final By addNewAddressButtonLocator = By.partialLinkText("Add a new address");
    private final By fullNameFieldLocator = By.id("address-ui-widgets-enterAddressFullName");
    private final By mobileNumberFieldLocator = By.id("address-ui-widgets-enterAddressPhoneNumber");
    private final By streetNameFieldLocator = By.id("address-ui-widgets-enterAddressLine1");
    private final By buildingNameFieldLocator = By.id("address-ui-widgets-enter-building-name-or-number");
    private final By cityFieldLocator = By.id("address-ui-widgets-enterAddressCity");
    private final By districtFieldLocator = By.id("address-ui-widgets-enterAddressDistrictOrCounty");
    private final By landMarkLocator = By.id("address-ui-widgets-landmark");
    private final By homeRadioButtonLocator = By.id("address-ui-widgets-addr-details-res-radio-input");
    private final By defaultAddressCheckBoxLocator = By.id("address-ui-widgets-use-as-my-default");
    private final By addAddressLocator = By.id("address-ui-widgets-form-submit-button-announce");

    private CheckOutPage(){
    }

    public static CheckOutPage getCheckOutPage(){
        return new CheckOutPage();
    }

    public CheckOutPage addAddress(String fullName,String mobileNumber,
                                   String streetName,String buildingName,
                                   String cityName,String district,
                                   String landMark){
        clickOnElement(changeAddressButtonLocator);
        clickOnElement(addNewAddressButtonLocator);
        sendTextToElement(fullNameFieldLocator,fullName);
        sendTextToElement(mobileNumberFieldLocator,mobileNumber);
        sendTextToElement(streetNameFieldLocator,streetName);
        sendTextToElement(buildingNameFieldLocator,buildingName);
        sendTextToElement(cityFieldLocator,cityName);
        clickOnElement(districtFieldLocator);
        sendTextToElement(districtFieldLocator,district);
        sendTextToElement(landMarkLocator,landMark);
        clickOnElement(homeRadioButtonLocator);
        clickOnElement(defaultAddressCheckBoxLocator);
        clickOnElement(addAddressLocator);
        return this;
    }

      // This is a legacy code for handling if the product support COD or not.
//    public CheckOutPage goToCheckout() {
//        // This assumes we’re coming from the Cart page
//        WebElement proceedButton = wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton));
//        proceedButton.click();
//        return this; // Return this for chaining
//    }
//
//    public boolean isCODAvailable() {
//        try {
//            WebElement codElement = wait.until(ExpectedConditions.visibilityOfElementLocated(codOption));
//            return codElement.isDisplayed() && codElement.isEnabled();
//        } catch (Exception e) {
//            return false; // COD option not found or not clickable
//        }
//    }
//
//    public void selectCashOnDelivery() {
//        WebElement codElement = wait.until(ExpectedConditions.elementToBeClickable(codOption));
//        codElement.click();
//        // Sometimes Amazon requires a "Continue" button after selecting payment
//        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//input[@name='ppw-widgetEvent:SelectPaymentMethodEvent']")));
//        continueButton.click();
//    }
//
//    public double getFinalTotal() {
//        WebElement totalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(finalTotalLocator));
//        String totalText = totalElement.getText().replace("EGP", "").replace(",", "").trim();
//        return Double.parseDouble(totalText);
//    }
//
}
