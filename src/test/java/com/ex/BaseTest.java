package com.ex;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected static WebDriver webDriver;

    @BeforeClass
    public void initWebDriver() {
        String browser = System.getProperty("browser");
        switch (browser) {
            case "chrome":
                webDriver = DriverInit.getWebDriver(DriverInit.Browser.CHROME);
                break;
            case "ff":
                webDriver = DriverInit.getWebDriver(DriverInit.Browser.FF);
                break;
            default:
                System.err.println("You have to chose from 'chrome' and 'ff'");
        }
    }

    @AfterClass
    public void closeWebDriver() {
        webDriver.quit();
    }
}
