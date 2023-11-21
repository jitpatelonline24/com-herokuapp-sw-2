package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {

    static String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() throws InterruptedException {
        //Enter username in username field
        WebElement userField = driver.findElement(By.id("username"));
        userField.sendKeys("tomsmith");
        //Enter password in password field
        WebElement passField = driver.findElement(By.name("password"));
        passField.sendKeys("SuperSecretPassword!");
        //click on login button
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(5000);
        //Verify the text "Secure Area"
        String expectedText = "Secure Area";
        String actualText = driver.findElement(By.xpath("//h2")).getText();
        Assert.assertEquals("Text displayed not Matched!", expectedText, actualText);
    }

    @Test
    public void verifyTheUsernameErrorMessage() throws InterruptedException {
        //Enter username 'tomsmith1' in username field
        driver.findElement(By.id("username")).sendKeys("tomsmith1");
        //Enter 'SuperSecret Password' on password field
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        //click on login button
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(5000);
        //verify the error message 'Your username is invalid!' text on screen
        String expectedText = "Your username is invalid!\n" + "×";
        String actualText = driver.findElement(By.xpath("//div[@id='flash']")).getText();
        Assert.assertEquals(expectedText, actualText);
    }

    @Test
    public void verifyThePasswordErrorMessage() throws InterruptedException {
        //Enter 'tomsmith' username field
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        //Enter 'supersecretpassword' password
        driver.findElement(By.name("password")).sendKeys("supersecret");
        //click on login button
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(5000);
        //verify the error message ' your password is invalid'
        String expectedText = "Your password is invalid!\n"+"×";
        String actualText = driver.findElement(By.xpath("//div[@id='flash']")).getText();
        Assert.assertEquals(expectedText,actualText);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }

}
