package org.testerfabrik.DriverFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AmazonTest {
    WebDriver driver;
    WebDriverWait waitElement;



    @BeforeTest
    public void setUpTest(){
        DriverFactory.getInstance().setDriver(BrowserType.CHROME);
        driver = DriverFactory.getInstance().getDriver();
        driver.get("https://www.amazon.com.mx");
        waitElement = new WebDriverWait(driver, 15);
    }

    @AfterTest
    public void tearDown() throws InterruptedException {
        DriverFactory.getInstance().removeDriver();
    }

    @Test(priority = 0)
    public void searchAmazon(){
        WebElement txtSearch = driver.findElement(By.id("twotabsearchtextbox"));
        waitElement.until(ExpectedConditions.visibilityOf(txtSearch));
        txtSearch.sendKeys("rtx 2060");

        driver.findElement(By.xpath("//input[@value='Ir']")).click();

        WebElement chkAsus = driver.findElement(By.xpath("//*[text()='Asus']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)",chkAsus);
        chkAsus.click();
    }
    @Test(priority = 1)
    public void getTextItem() {
         String name = driver.findElement(By.xpath("//span[contains(text(),'Asus TURBO-RTX2060-6G Graphic Card GeForce, 6GB GD')]")).getText();
        String score = driver.findElement(By.xpath("//span[contains(text(),'Asus TURBO-RTX2060-6G Graphic Card GeForce, 6GB GD')]/following::span[3]")).getAttribute("innerText");
        System.out.println("Name: " + name + ", \nScore: " + score);
    }

    @Test(priority = 2)
    public void Clickitem() {
        driver.findElement(By.xpath("//span[contains(text(), 'Asus TURBO-RTX2060-6G')]")).click();
    }

    @Test(priority = 3)
    public void addtocart(){
            WebElement addcart = driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)",addcart);
            addcart.click();
            driver.findElement(By.xpath("//a[@id='hlb-view-cart-announce']")).click();

    }

    @Test(priority = 4)
    public void PriceCheck(){
        String actualResult = "";
        String expectedResult = "$11,059.98";

        actualResult = driver.findElement(By.xpath("//span[@id='sc-subtotal-amount-buybox']//span[@class='a-size-medium a-color-price sc-price sc-white-space-nowrap sc-price-sign'][contains(text(),'$10,059.99')]")).getText();

        Assert.assertEquals(actualResult, expectedResult, "Tittle is not equals");

    }
}


