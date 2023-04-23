package com.lcwaikiki;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class Test {
    static WebDriver webDriver;
    static WebDriverWait webWait;


    public static void main(String[] args) {
        By cat = By.xpath("//*[text()='ERKEK']");
        By ofisStilleri = By.xpath("//*[text()='Ofis Stilleri']");
        By fourthProduct = By.xpath("(//*[@class='product-image__image'])[4]");
        By firstSize = By.xpath("//*[@id='option-size']//a");
        By sepeteEkle = By.xpath("//*[@class='add-to-cart button-link add-to-cart-button']");
        By sepetim = By.xpath("(//*[@class='header-dropdown-toggle'])[3]");
        By headerLogo = By.xpath("//*[@class='main-header-logo']");
        By sepetimSayfasi = By.xpath("//*[@class='price-info-area']");
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver(getChromeOptions());
        webWait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        webWait.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        webDriver.get("https://www.lcwaikiki.com/");
        webDriver.manage().window().maximize();
        Actions action = new Actions(webDriver);
        action.moveToElement(getElement(cat)).perform();
        waitSeconds(3);
        getElement(ofisStilleri).click();
        getElement(fourthProduct).click();
        waitSeconds(3);
        getElement(firstSize).click();
        getElement(sepeteEkle).click();
        getElement(sepetim).click();
        controlElementVisible(sepetimSayfasi);
        getElement(headerLogo).click();
        waitSeconds(3);
        Assert.assertEquals("https://www.lcwaikiki.com/tr-TR/TR",webDriver.getCurrentUrl());
        webDriver.close();
    }

    public static WebElement getElement(By by) {
        return webWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }


    public static void controlElementVisible(By by) {
        Assert.assertTrue(getElement(by).isDisplayed());
        System.out.println("Element kontrol edildi.Sayfa doğrulandı.");
    }

    public static void waitSeconds(int second) {
        try {
            Thread.sleep(1000L * second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-ssl-errors=yes");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-translate");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
        return options;
    }
}


