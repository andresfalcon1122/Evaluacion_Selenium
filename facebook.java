package org.testerfabrik.DriverFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class facebook{
    WebDriver driver;
    WebDriverWait waitElement;

    @BeforeTest
    public void setUpTest() {
        DriverFactory.getInstance().setDriver(BrowserType.CHROME);
        driver = DriverFactory.getInstance().getDriver();
        driver.get("https://es-la.facebook.com/");
        waitElement = new WebDriverWait(driver, 15);
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        DriverFactory.getInstance().removeDriver();
    }

    @Test(priority = 0)
    public void searchfacebook() {
        WebElement txtemail = driver.findElement(By.id("email"));
        waitElement.until(ExpectedConditions.visibilityOf(txtemail));
        txtemail.sendKeys("TitaniumSolutionsTest@hotmail.com");

        WebElement txtpass = driver.findElement(By.id("pass"));
        waitElement.until(ExpectedConditions.visibilityOf(txtpass));
        txtpass.sendKeys("Titaniumsolutions1");

        driver.findElement(By.xpath("//input[@value='Iniciar sesión']")).click();
    }
}
