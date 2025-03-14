package ui.page_objects;

import org.openqa.selenium.By;

import static ui.ui_utils.Shared.*;

public class CartPage {

    private final By cartButtonLocator = By.id("nav-cart");
    private final By processToBuyButtonLocator = By.name("proceedToRetailCheckout");
    private final By totalPriceLocator = By.id("sc-subtotal-amount-buybox");

    private CartPage(){

    }

    public static CartPage getCartPage(){
        return new CartPage();
    }

    public CartPage goToCartPage(){
        return this;
    }

    public CartPage goToCheckout() {
        return this;
    }
    
//    public void goToSignInPage(){
//        //clickOnElement(processToBuyButtonLocator);
//    }

    public double ExcpectedTotalPrice()  {
        double total =  TestClickOnElement();
        clickOnElement(cartButtonLocator) ;
        return total;
    }

    public double getTotalPriceLocator(){
        double totalPrice = Double.parseDouble(getElementText(totalPriceLocator).trim().replace(",","")
                .replace("EGP ",""));
        System.out.println(totalPrice);
        return totalPrice;
    }


}
