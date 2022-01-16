package com.ex.ui;

import com.ex.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class SmokeUiSuite extends BaseTest {

    private String url = "http://automationpractice.com/";

    @BeforeMethod
    protected String getSaltString() {
        String saltChars = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) {
            int index = (int) (rnd.nextFloat() * saltChars.length());
            salt.append(saltChars.charAt(index));
        }

        return salt + "@mailinator.com";
    }

    private String email = getSaltString();

    @Test
    public void transitionToAccountCreationForm() {
        webDriver.get(url);
        By signIn = By.xpath("//a[@class='login']");
        webDriver.findElement(signIn).click();

        webDriver.findElement(By.xpath("//input[@id='email_create']")).sendKeys(email);
        webDriver.findElement(By.xpath("//button[@id='SubmitCreate']")).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String currentUrl = webDriver.getCurrentUrl();
        String formHeader = webDriver.findElement(By.xpath("//h1[@class='page-heading']")).getText();

        Assert.assertEquals(currentUrl, url + "index.php?controller=authentication&back=my-account#account-creation");
        Assert.assertEquals(formHeader, "CREATE AN ACCOUNT");
    }

    @Test
    public void createAccountWithAlreadyRegisteredEmail() {
        String registeredEmail = "aa@aa.com";
        webDriver.get(url + "index.php?controller=authentication&back=my-account#account-creation");
        webDriver.findElement(By.xpath("//input[@id='email_create']")).sendKeys(registeredEmail);
        webDriver.findElement(By.xpath("//button[@id='SubmitCreate']")).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String expectedErrorMessage = "An account using this email address has already been registered. Please enter a " +
                "valid password or request a new one.";
        String actualErrorMessage = webDriver.findElement(By.xpath("//div[@class='alert alert-danger']//li")).getText();
        String currentUrl = webDriver.getCurrentUrl();

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
        Assert.assertEquals(currentUrl, url + "index.php?controller=authentication&back=my-account#account-creation");
    }
}
