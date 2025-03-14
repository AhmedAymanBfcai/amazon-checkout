package ui;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ui.test_data.ShippingData;
import ui.page_objects.*;
import static ui.test_data.ShippingData.getShippingData;
import static ui.page_objects.CartPage.getCartPage;
import static ui.page_objects.CheckOutPage.getCheckOutPage;
import static ui.page_objects.HomePage.getHomePage;
import static ui.page_objects.SignInPage.getSignInPage;
import static ui.page_objects.VideoGamesPage.getVideoGamesPage;
import static ui.ui_utils.DriverFactory.quitDriver;
import static ui.ui_utils.Settings.homeUrl;
import static ui.ui_utils.Shared.goToUrl;
import static ui.ui_utils.Shared.maximizeScreen;

public class AmazonCheckoutTest {
    HomePage homePage = getHomePage();
    SignInPage signInPage = getSignInPage();
    VideoGamesPage videoGamesPage = getVideoGamesPage();
    CartPage cartPage = getCartPage();
    ShippingData shippingData = getShippingData();
    CheckOutPage checkOutPage = getCheckOutPage();
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void setUp() {
        //STEP 1 ----> Open https://www.amazon.eg/ and login
        goToUrl(homeUrl);
        maximizeScreen();
        signInPage.enterPhoneNumber(shippingData.getPhoneNumber())
                .enterPassword(shippingData.getPassword());
    }

    @Test
    public void testAddProductsLess15kToCart()  {
        //STEP 2 ----> open “All” menu from the left side
        homePage.clickOnLeftSideMenu()
                .clickOnVideoGames()
                .clickOnAllVideoGames(); //STEP 3 ----> click on “video games” then choose “all video games”

        //STEP 4 ----> from the filter menu on the left side add filter “free shipping” & add the filter of condition "new"
        videoGamesPage.clickOnFreeShippingFilter().clickOnNewConditionFilter()
                .clickOnFilterByPrice() //STEP 5 ----> On the right side open the sort menu then sort by price: high to low
                .addProductToCart(); //STEP 6 ---->  add all products below that its cost below 15k EGP , if no product below 15k EGP move to next page

        //STEP 7 ----> make sure that all products is already added to carts
        int actualNumOfProductsInTheCart = videoGamesPage.getCartCount();
        softAssert.assertEquals(actualNumOfProductsInTheCart,
                7, // To be refactored
                "The count of products is wrong in the cart!!");

        // STEP 8 - a ----> add address and choose cash as a payment method
        checkOutPage.addAddress(shippingData.getFullName()
                ,shippingData.getPhoneNumber()
                ,shippingData.getStreetName()
                ,shippingData.getBuildingName()
                ,shippingData.getCityArea()
                ,shippingData.getDistrict()
                ,shippingData.getLandMark());
        softAssert.assertAll();


//        // STEP 8 - b: Check if COD is available
         //private By codRadioButton = By.id("pp-67daR1-83");
//        public boolean isCodAvailable() {
//            WebElement codOption = wait.until(ExpectedConditions.visibilityOfElementLocated(codRadioButton));
//            return !codOption.getAttribute("disabled").equals("true"); // Check if disabled attribute is present
//        }
//
//        // Select COD payment if available
//        public CheckOutPage selectCodPayment() {
//            WebElement codOption = driver.findElement(codRadioButton);
//            if (codOption.isEnabled()) {
//                codOption.click();
//            }
//            return this;
//        }

        //STEP 9 ----> make sure that the total amount of all items is correct with the shipping fees if exist
        double ExpectedPrice;
        ExpectedPrice = cartPage.goToCartPage().ExcpectedTotalPrice();
        System.out.println("ExpectedTotalPrice : " + ExpectedPrice );
        double actualTotalPrice ;
        actualTotalPrice = cartPage.goToCartPage().getTotalPriceLocator();
        System.out.println("actualTotalPriceInCart : " + actualTotalPrice );

        softAssert.assertEquals(actualTotalPrice,ExpectedPrice,"The total price is wrong!!");
    }

    @AfterMethod
    public void tearDown(){
        quitDriver();
    }
}



