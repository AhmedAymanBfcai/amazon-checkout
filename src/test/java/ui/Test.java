package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        // Set up WebDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // Open Amazon homepage
            driver.get("https://www.amazon.eg/");
            Thread.sleep(3000);

            // Click on the Sign-In button
            WebElement signInButton = driver.findElement(By.id("nav-link-accountList"));
            signInButton.click();
            Thread.sleep(3000);

            // Enter email/phone number
            WebElement emailField = driver.findElement(By.id("ap_email"));
            emailField.sendKeys("01112649704"); // 🔹 Change this to your email

            driver.findElement(By.id("continue")).click(); // Click Continue

            // Enter password
            WebElement passwordField = driver.findElement(By.id("ap_password"));
            passwordField.sendKeys("9bvcm!-Sprj+zEt"); // 🔹 Change this to your password

            driver.findElement(By.id("signInSubmit")).click(); // Click Sign In

            // Wait for login to complete (ensuring we're signed in)
            Thread.sleep(3000);

            // Open the Video Games page with filters applied
            driver.get("https://www.amazon.eg/-/en/s?i=videogames&rh=n%3A18022560031%2Cp_n_free_shipping_eligible%3A21909080031%2Cp_n_condition-type%3A28071525031&s=price-desc-rank&dc&page=2&language=en&xpid=J556zmMVA7yZM&qid=1741914563&rnid=28071523031&ref=sr_pg_2");

            // Find all product elements
            List<WebElement> products = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']"));

            System.out.println("products");
            System.out.println(products);

            double total = 0.0; // To store total price before adding to cart

            int notFoundCount = 0; // Counter for missing Add to Cart buttons

            for (WebElement product : products) {
                try {
                    // Get the price element
                    WebElement priceElement = product.findElement(By.xpath(".//span[@class='a-price-whole']"));

                    String priceText = priceElement.getText().replace(",", "").trim(); // Remove commas
                    System.out.println("Price Text: " + priceText);

                    // Convert to double
                    double price = Double.parseDouble(priceText);

                    // Check if price is < 40,000 EGP
                    if (price < 15000) {

                        // Click "Add to Cart"
                        Thread.sleep(10000);

                        try {
                            WebElement addToCartButton = product.findElement(By.name("submit.addToCart"));
                            addToCartButton.click();
                            System.out.println("Added product with price: " + price + " EGP");
                            total += price; // Add to total
                        } catch (NoSuchElementException e) {
                            notFoundCount++; // Increment count if button is missing
                            System.out.println("Add to Cart button NOT found for a product.");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Could not get price or add to cart for a product.");
                }
            }

            // Print total price before adding to cart
            System.out.println("Total price before adding to cart: " + total + " EGP");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close browser
            //driver.quit();
        }
    }
}
