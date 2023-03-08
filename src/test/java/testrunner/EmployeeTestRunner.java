package testrunner;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MyInfo;
import pages.PIMPage;
import setup.Setup;
import utils.Utils;

import java.io.IOException;

public class EmployeeTestRunner extends Setup {
    LoginPage loginPage;
    PIMPage pimPage;
    MyInfo myInfo;

    @Test(priority = 1, description = "Employee shall be able to login using invalid username and invalid password")
    public void doEmployeeLoginInvalidUname(){
        loginPage = new LoginPage(driver);
        String userName = "fake";
        String password = "fake";
        loginPage.doLogin(userName, password);

        String validationError_actual = driver.findElement(By.className("oxd-alert-content-text")).getText();
        String validationError_expected = "Invalid credentials";
        Assert.assertTrue(validationError_actual.contains(validationError_expected));
    }
    @Test(priority = 2, description = "Employee shall be able to login using invalid username and valid password")
    public void doEmployeeLoginInvalidPass() throws IOException, ParseException {
        loginPage = new LoginPage(driver);
        JSONObject userObject = Utils.loadJson("./src/test/resources/Users.json", 1);
        String userName = "fake";
        String password = userObject.get("password").toString();
        loginPage.doLogin(userName, password);

        String validationError_actual = driver.findElement(By.className("oxd-alert-content-text")).getText();
        String validationError_expected = "Invalid credentials";
        Assert.assertTrue(validationError_actual.contains(validationError_expected));
    }
    @Test(priority = 3, description = "Employee shall be able to login using valid username and valid password")
    public void doEmployeeLoginValid() throws IOException, ParseException {
        loginPage = new LoginPage(driver);
        JSONObject userObject = Utils.loadJson("./src/test/resources/Users.json", 1);
        String userName = userObject.get("username").toString();
        String password = userObject.get("password").toString();
        loginPage.doLogin(userName, password);
        String headerActual = driver.findElement(By.tagName("h6")).getText();
        String headerExpected = "Dashboard";
        Assert.assertTrue(headerActual.contains(headerExpected));
    }
    @Test(priority = 4, description = "Go to pimpage")
    public void myInfoPage(){
        pimPage = new PIMPage(driver);
        pimPage.btnInfo.get(2).click();
    }
    @Test(priority = 5, description = "Employee shall be able to set his details")
    public void setEmployeeDetails() throws InterruptedException {
        myInfo = new MyInfo(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,800)", "");
        Thread.sleep(1500);
        myInfo.setGender();
        Thread.sleep(1500);
        js.executeScript("window.scrollBy(0,300)", "");
        myInfo.setBloodType();
        Thread.sleep(1500);
        Utils utils = new Utils();
        js.executeScript("window.scrollBy(0,-700)", "");
        Thread.sleep(1500);
        utils.generateEmployeeAddress();
        myInfo.setContactDetails(utils.getStreet(), utils.getCity(), utils.getState(), utils.getZipCode(), utils.getEmail());
        Thread.sleep(1500);
    }
    @Test (priority = 6, description = "Employee can logout from his account")
    public void doLogout() throws InterruptedException {
        loginPage=new LoginPage(driver);
        loginPage.btnUserName.click();
        driver.findElement(By.partialLinkText("Logout")).click();
        String url= driver.getCurrentUrl();
        Assert.assertTrue(url.contains("login"));
        Thread.sleep(1500);
        driver.close();
    }
}
