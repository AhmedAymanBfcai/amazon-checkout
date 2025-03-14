package ui.ui_utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;
import static ui.ui_utils.DriverFactory.getDriver;
import static ui.ui_utils.Settings.browserType;

public final class Shared {

    private static final WebDriver driver = getDriver(browserType);

    public static void goToUrl(String url) {
        driver.get(url);
    }

    private static Wait<WebDriver> getFluentWait() {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotInteractableException.class);
    }

    public static void clickOnElement(By elementPath1) {
        WebElement element = getFluentWait().until(ExpectedConditions.elementToBeClickable(elementPath1));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    public static double TestClickOnElement() {
        List<WebElement> products = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']"));

        System.out.println("products");
        System.out.println(products);

        double total = 0.0; // To store total price before adding to cart
        int notFoundCount = 0; // Counter for missing Add to Cart buttons

        for (WebElement product : products)
            {
                try {
                    // Get the price element
                    WebElement priceElement = product.findElement(By.xpath(".//span[@class='a-price-whole']"));

                    String priceText = priceElement.getText().replace(",", "").trim(); // Remove commas
                    System.out.println("Price Text: " + priceText);

                    // Convert to double
                    double price = Double.parseDouble(priceText);

                    // Check if price is < 15,000 EGP
                    if (price < 15000) {
                        // Click "Add to Cart"
                        try {
                            WebElement addToCartButton = product.findElement(By.name("submit.addToCart"));
                            addToCartButton.click();
                            total += price; // Add to total
                            System.out.println("Added product with price: " + price + " EGP");
                        } catch (NoSuchElementException e) {
                            notFoundCount++; // Increment count if button is missing
                            System.out.println("Add to Cart button NOT found for a product.");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Could not get price or add to cart for a product.");
                }
                System.out.println("Total products where 'Add to Cart' button was NOT found: " + notFoundCount);

            }

        return total ;
    }

    public static void sendTextToElement(By elementPath, String text) {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementPath)).sendKeys(text);
    }

    public static void maximizeScreen(){
        driver.manage().window().maximize();
    }

    public static String getElementText(By elementPath){
        return getFluentWait().until(ExpectedConditions.visibilityOfElementLocated(elementPath)).getText();
    }

    public static List<WebElement> getAllElements(By elementPath){
        return getFluentWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(elementPath));
    }

    public static void selectFromDropdownByIndex(By dropdownLocator, int index) {
        WebElement dropdown = getFluentWait().until(ExpectedConditions.elementToBeClickable(dropdownLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", dropdown);

        try {
            Select select = new Select(dropdown);
            select.selectByIndex(index);
        } catch (ElementNotInteractableException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].selectedIndex=arguments[1];", dropdown, index);
        }
    }
}
