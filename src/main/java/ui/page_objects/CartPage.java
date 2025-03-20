package ui.page_objects;

import org.openqa.selenium.By;
import static ui.ui_utils.Shared.*;

public class CartPage {

    private final By cartButtonLocator = By.id("nav-cart");
    public static By processToBuyButtonLocator = By.name("proceedToRetailCheckout");
    private final By totalPriceLocator = By.id("sc-subtotal-amount-buybox");
    private final By addNewAddress = By.id("add-new-address-desktop-sasp-tango-link");

    private CartPage(){

    }

    public static CartPage getCartPage(){
        return new CartPage();
    }

    public CartPage goToCartPage(){
        return this;
    }

    public double getExpectedTotalPrice() throws InterruptedException {
        double total =  addAffordableProductsToCart();
        clickOnElement(cartButtonLocator);

        return total;
    }

    public double getActualTotalPrice(){
        return Double.parseDouble(getElementText(totalPriceLocator).trim().replace(",","")
                .replace("EGP ",""));
    }

    public void processToBuyButton() {
        clickOnElement(processToBuyButtonLocator);
        clickOnElement(addNewAddress);
    }
}
