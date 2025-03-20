package ui.page_objects;

import org.openqa.selenium.By;
import static ui.ui_utils.Shared.clickOnElement;
import static ui.ui_utils.Shared.sendTextToElement;

public class SignInPage {
    private final By phoneNumberFieldLocator = By.xpath("//input[@name='email']");
    private final By continueButtonLocator = By.id("continue");
    private final By passwordFieldLocator = By.id("ap_password");
    private final By signInFieldLocator = By.id("signInSubmit");
    private final By signInButton = By.id("nav-link-accountList");

    private SignInPage() {
    }

    public static SignInPage getSignInPage() {
        return new SignInPage();
    }

    public SignInPage enterPhoneNumber(String phoneNumber) {
        clickOnElement(signInButton);
        sendTextToElement(phoneNumberFieldLocator, phoneNumber);
        clickOnElement(continueButtonLocator);
        return this;
    }

    public void enterPassword(String password) {
        sendTextToElement(passwordFieldLocator, password);
        clickOnElement(signInFieldLocator);
    }

}
