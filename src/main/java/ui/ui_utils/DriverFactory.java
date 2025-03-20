package ui.ui_utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

// Singleton-Like Behavior: Not a true singleton (no private constructor), but ensures one WebDriver at a time via the static driver check.
// Factory Pattern: Acts as a factory by creating WebDriver instances based on input (browserName).
// Static Methods: Utility-style, no need for instantiation since it manages a single, shared resource.
public final class DriverFactory {
    private static WebDriver driver;

    public static WebDriver getDriver(String browserName) {
        if (driver == null) {
            if (browserName.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            } else if (browserName.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            } else if (browserName.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
