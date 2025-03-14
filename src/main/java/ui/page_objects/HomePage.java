package ui.page_objects;

import org.openqa.selenium.By;
import static ui.ui_utils.Shared.clickOnElement;

public class HomePage {

    private final By leftSideMenuLocator = By.id("nav-hamburger-menu");
    private final By seeAllMenuButtonLocator = By.xpath("//div[contains(text(),'See all')]");
    private final By videoGamesLocator = By.xpath("//a[@data-menu-id='16']");
    private final By allVideoGamesLocator = By.xpath("//a[contains(text(),'All Video Games')]");

    private HomePage(){
    }

    public static HomePage getHomePage(){
        return new HomePage();
    }

    public HomePage clickOnLeftSideMenu(){
        clickOnElement(leftSideMenuLocator);
        return this;
    }

    public HomePage clickOnVideoGames(){
        clickOnElement(seeAllMenuButtonLocator);
        clickOnElement(videoGamesLocator);
        return this;
    }

    public void clickOnAllVideoGames(){
        clickOnElement(allVideoGamesLocator);
    }
}
