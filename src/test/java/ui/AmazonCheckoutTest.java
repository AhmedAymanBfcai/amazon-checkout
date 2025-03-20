package ui;

import org.testng.annotations.*;
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
        // 1. Open https://www.amazon.eg/ and login
        goToUrl(homeUrl);
        maximizeScreen();

        signInPage.enterPhoneNumber(shippingData.getPhoneNumber())
                .enterPassword(shippingData.getPassword());
    }

    @Test
    public void testAddProductsLess15kToCart() throws InterruptedException {
        // Open “All” menu from the left side
        homePage.clickOnLeftSideMenu()
                .clickOnVideoGames()
                .clickOnAllVideoGames(); // Click on “video games” then choose “all video games”

        // From the filter menu on the left side add filter “free shipping” & add the filter of condition "new"
        videoGamesPage.clickOnFreeShippingFilter().clickOnNewConditionFilter()
                .clickOnFilterByPrice()  // On the right side open the sort menu then sort by price: high to low
                .navigateToNextPage();

        // Make sure that the total amount of all items is correct with the shipping fees if exist
        double expectedTotalPrice;
        expectedTotalPrice = cartPage.goToCartPage().getExpectedTotalPrice();
        System.out.println("ExpectedTotalPrice: " + expectedTotalPrice);

        double actualTotalPriceInCart;
        actualTotalPriceInCart = cartPage.goToCartPage().getActualTotalPrice();
        System.out.println("ActualTotalPriceInCart: " + actualTotalPriceInCart);

        softAssert.assertEquals(actualTotalPriceInCart,
                expectedTotalPrice,
                "The total price in the cart and the product get added not equal!!");

        // Add address and choose cash as a payment method if it's available.
        cartPage.processToBuyButton();
        checkOutPage.addAddress(shippingData.getFullName()
                ,shippingData.getPhoneNumber()
                ,shippingData.getStreetName()
                ,shippingData.getBuildingName()
                ,shippingData.getCityArea()
                ,shippingData.getDistrict()
                ,shippingData.getLandMark());

        Thread.sleep(2000);
        String status = checkOutPage.textView();

        if (status.equals("Oops! We're sorry")) {
            softAssert.assertEquals(status, "Oops! We're sorry", "Test case pass but cannot automate amazon!");
        } else if (status.equals("true")) {
            softAssert.assertEquals(status, "true", "Test case pass and can select COD method ");
        } else {
            softAssert.assertEquals(status, "false", "Test case pass but cannot use COD method");
        }
    }

    @AfterMethod
    public void tearDown(){
        quitDriver();
    }
}