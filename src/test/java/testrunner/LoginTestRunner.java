package testrunner;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import setup.Setup;

public class LoginTestRunner extends Setup {
    LoginPage loginPage;

    @Test (priority = 1, description = "Admin can not login with invalid username")
    public void doLoginInvalidUsername(){
        loginPage = new LoginPage(driver);
        loginPage.doLogin("admin123", "admin123");
        String validationError_actual = driver.findElement(By.className("oxd-alert-content-text")).getText();
        String validationError_expected = "Invalid credentials";
        Assert.assertTrue(validationError_actual.contains(validationError_expected));
    }
    @Test (priority = 2, description = "Admin can not login with invalid password")
    public void doLoginInvalidPassword(){
        loginPage = new LoginPage(driver);
        loginPage.doLogin("admin", "admin");
        String validationError_actual = driver.findElement(By.className("oxd-alert-content-text")).getText();
        String validationError_expected = "Invalid credentials";
        Assert.assertTrue(validationError_actual.contains(validationError_expected));
    }
    @Test (priority = 3, description = "Admin shall be able to login with valid username and password")
    public void doLogin(){
        loginPage = new LoginPage(driver);
        loginPage.doLogin("admin", "admin123");
        String headerActual = driver.findElement(By.tagName("h6")).getText();
        String headerExpected = "Dashboard";

        boolean isUserNameExist = driver.findElement(By.className("oxd-userdropdown-name")).isDisplayed();
        boolean isProfileImageExist = driver.findElement(By.className("oxd-userdropdown-img")).isDisplayed();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(headerActual.contains(headerExpected));
        softAssert.assertTrue((isUserNameExist));
        softAssert.assertTrue(isProfileImageExist);
        softAssert.assertAll();
    }
    @Test (priority = 4, description = "Admin can logout by clicking on logout button")
    public void doLogout(){
        loginPage=new LoginPage(driver);
        loginPage.btnUserName.click();
        driver.findElement(By.partialLinkText("Logout")).click();
        String url= driver.getCurrentUrl();
        Assert.assertTrue(url.contains("login"));
        driver.close();
    }
}
