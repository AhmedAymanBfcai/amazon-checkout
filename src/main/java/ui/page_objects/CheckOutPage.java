package ui.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static ui.ui_utils.Shared.*;
import static ui.ui_utils.Shared.clickOnElement;

public class CheckOutPage {
    private final By fullNameFieldLocator = By.id("address-ui-widgets-enterAddressFullName");
    private final By mobileNumberFieldLocator = By.id("address-ui-widgets-enterAddressPhoneNumber");
    private final By streetNameFieldLocator = By.id("address-ui-widgets-enterAddressLine1");
    private final By buildingNameFieldLocator = By.id("address-ui-widgets-enter-building-name-or-number");
    private final By cityFieldLocator = By.id("address-ui-widgets-enterAddressCity");
    private final By districtFieldLocator = By.id("address-ui-widgets-enterAddressDistrictOrCounty");
    private final By landMarkLocator = By.id("address-ui-widgets-landmark");
    private final By homeRadioButtonLocator = By.id("address-ui-widgets-addr-details-res-radio-input");
    private final By defaultAddressCheckBoxLocator = By.id("address-ui-widgets-use-as-my-default");
    private final By addAddressLocator = By.cssSelector("input.a-button-input");
    private static final By codRadioButton = By.xpath("//*[@id=\"pp-InLbga-83\"]");
    private static final By useThisPaymentButton = By.cssSelector("input.data-csa-c-type");
    private final By textLocator = By.xpath("/html/body/div[2]/table/tbody/tr/td/b");

    private CheckOutPage() {
    }

    public static CheckOutPage getCheckOutPage() {
        return new CheckOutPage();
    }

    public CheckOutPage addAddress(String fullName, String mobileNumber,
                                   String streetName, String buildingName,
                                   String cityName, String district,
                                   String landMark) throws InterruptedException {
        sendTextToElement(fullNameFieldLocator, fullName);
        sendTextToElement(mobileNumberFieldLocator, mobileNumber);
        sendTextToElement(streetNameFieldLocator, streetName);
        sendTextToElement(buildingNameFieldLocator, buildingName);

        sendTextToElement(cityFieldLocator, cityName);
        Thread.sleep(5000);
        clickOnElement(streetNameFieldLocator);

        Thread.sleep(5000);
        sendTextToElement(districtFieldLocator, district);

        sendTextToElement(landMarkLocator, landMark);
        clickOnElement(homeRadioButtonLocator);
        clickOnElement(defaultAddressCheckBoxLocator);
        clickOnElement(addAddressLocator);


        return this;
    }

    public String textView() {
        try {
            System.out.println("Test1");

             WebElement textLocatorElement = (WebElement) textLocator; // Wrong Casting
            //  WebElement textLocatorElement = driver.findElement(textLocator);
            System.out.println("Test2");

            System.out.println("textLocatorElement: " + textLocatorElement.getText());
            return textLocatorElement.getText();

        } catch (ClassCastException e) {
            boolean Test = CheckOutPage.isCODAvailable();
            if(Test){
                return "true" ;
            }else
            {
                return "false" ;
            }
        }

    }

        public  static boolean isCODAvailable() {
            WebElement codRadio = (WebElement) codRadioButton;
            if (codRadio.isDisplayed() && codRadio.isEnabled()) {
                codRadio.click();
                clickOnElement(useThisPaymentButton);
                return true;
            } else {
                return false;
            }
        }
    }


