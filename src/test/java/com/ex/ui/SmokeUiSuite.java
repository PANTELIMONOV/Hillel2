package com.ex.ui;

import com.ex.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class SmokeUiSuite extends BaseTest {
    @Test
    public void firstTest() {
        webDriver.get("http://automationpractice.com/");
        By signIn = By.xpath("//a[@class='login']");
        webDriver.findElement(signIn).click();

        //TODO generate random email, and check register form is displayed
        webDriver.findElement(By.xpath("//input[@id='email_create']")).sendKeys("example@email.com");
        webDriver.findElement(By.xpath("//button[@id='SubmitCreate']")).click();


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void firstTest1() {
        webDriver.get("http://automationpractice.com/");
        By signIn = By.xpath("//a[@class='login']");
        webDriver.findElement(signIn).click();

        //TODO verify that error 'email already exist' is displayed
        webDriver.findElement(By.xpath("//input[@id='email_create']")).sendKeys("example@email.com");
        webDriver.findElement(By.xpath("//button[@id='SubmitCreate']")).click();


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
