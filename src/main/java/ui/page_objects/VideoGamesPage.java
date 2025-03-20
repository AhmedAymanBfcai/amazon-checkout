package ui.page_objects;

import org.openqa.selenium.*;
import java.util.List;
import static ui.ui_utils.Shared.*;

public class VideoGamesPage {

    private final By freeShippingFilterLocator = By.xpath("//ul[@aria-labelledby='p_n_free_shipping_eligible-title']");
    private final By newFilterConditionLocator = By.xpath("//span[text()='New']");
    private final By sortByPriceLocator = By.id("s-result-sort-select");
    private final By priceLocator = By.xpath("//span[@class='a-price-whole']");
    private final By nextPageButtonLocator = By.linkText("Next");

    //prevent instance
    private VideoGamesPage() {
    }

    public static VideoGamesPage getVideoGamesPage() {
        return new VideoGamesPage();
    }

    public VideoGamesPage clickOnFreeShippingFilter() {
        clickOnElement(freeShippingFilterLocator);
        return this;
    }

    public VideoGamesPage clickOnNewConditionFilter() {
        clickOnElement(newFilterConditionLocator);
        return this;
    }

    public VideoGamesPage clickOnFilterByPrice() {
        selectFromDropdownByIndex(sortByPriceLocator, 2); // To be refactored to select by text: "High to low" not index.
        return this;
    }

    private boolean isPageContainsItemsLess15k(List<WebElement> items) {
        return items
                .stream()
                .map(e -> Double.parseDouble(e.getText()
                        .replace(",", "")
                        .trim()))
                .anyMatch(price -> price < 15000);
    }

    public void navigateToNextPage() {
        boolean itemsLess15k = false;
        while (!itemsLess15k) {
            List<WebElement> productsPrices = getAllElements(priceLocator);
            itemsLess15k = isPageContainsItemsLess15k(productsPrices);

            if (!itemsLess15k) {
                clickOnElement(nextPageButtonLocator);
            }
        }
    }
}
