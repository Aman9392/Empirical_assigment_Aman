package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseTest;

import java.time.Duration;

public class Empiricial extends BaseTest {

    @Test(priority = 1)
    public void testSignUpButtonVisible() {
        driver.get("https://v0-button-to-open-v0-home-page-h5dizpkwp.vercel.app/");

        WebElement openButton = driver.findElement(By.tagName("button"));
        openButton.click();

        // Switch to New Tab
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        // Wait for page to fully load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        // Now wait for the Sign Up button to be visible
        WebElement signUpButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='Sign Up']")));

        Assert.assertTrue(signUpButton.isDisplayed(), "Sign Up button should be visible");
    }

    @Test(priority = 2)
    public void testSearchInputVisible() {
        driver.get("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_links_target");

        driver.switchTo().frame("iframeResult");
        WebElement visitLink = driver.findElement(By.linkText("Visit W3Schools!"));
        visitLink.click();

        // Switch to new tab
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        WebElement searchInput = driver.findElement(By.id("search2"));
        Assert.assertTrue(searchInput.isDisplayed(), "Search input should be visible on W3Schools page");
    }
}
