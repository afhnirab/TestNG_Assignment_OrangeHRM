package testrunner;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.PIMPage;
import setup.Setup;
import utils.Utils;
import java.io.IOException;

public class PIMTestRunner extends Setup {
    LoginPage loginPage;
    PIMPage pimPage;

    @BeforeTest
    public void doLogin(){
        loginPage = new LoginPage(driver);
        loginPage.doLogin("admin", "admin123");
        driver.findElement(By.partialLinkText("PIM")).click();
    }
    @Test (priority = 1, description = "Admin shall not be able to create an user without firstname and lastname")
    public void createEmployee() throws InterruptedException {
        pimPage = new PIMPage(driver);
        pimPage.btnPim.get(1).click();
        Thread.sleep(1000);
        pimPage.button.get(2).click();
        Thread.sleep(1000);
        pimPage.btnSubmit.click();
    }

    @Test (priority = 2, description = "Admin shall be able to create an user with firstname and lastname")
    public void createEmployee1() throws IOException, ParseException, InterruptedException {
        Utils.replaceJsonFile();
        pimPage = new PIMPage(driver);
        Thread.sleep(1000);

        Utils utils = new Utils();
        utils.generateUserInfo();
        pimPage.createEmployee(utils.getFirstName(), utils.getLastName(), utils.getUserName(), utils.getPassword(), utils.getPassword());
        utils.saveUserInfo(utils.getFirstName(), utils.getLastName(), utils.getUserName(), utils.getPassword());
        Thread.sleep(1000);
    }

    @Test (priority = 3, description = "Admin shall be able to create another user")
    public void createEmployee2() throws IOException, ParseException, InterruptedException {
        driver.findElement(By.partialLinkText("PIM")).click();
        pimPage = new PIMPage(driver);
        pimPage.button.get(2).click();
        Utils utils = new Utils();
        utils.generateUserInfo();
        pimPage.createEmployee(utils.getFirstName(), utils.getLastName(), utils.getUserName(), utils.getPassword(), utils.getPassword());
        utils.saveUserInfo(utils.getFirstName(), utils.getLastName(), utils.getUserName(), utils.getPassword());
    }

    @Test (priority = 4, description = "It shall not display any records while searching with a full name that is not in the database")
    public void searchEmployeeInvalidS() throws InterruptedException{
        pimPage = new PIMPage(driver);
        driver.findElement(By.partialLinkText("PIM")).click();
        String employeeName = "Optimus Prime";
        pimPage.searchValidEmployee(employeeName);
        Thread.sleep(1500);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)", "");
        Thread.sleep(1500);

        String message_actual = driver.findElements(By.className("oxd-text--span")).get(11).getText();
        String message_expected = "No Records Found";
        Assert.assertTrue(message_actual.contains(message_expected));
        Thread.sleep(1000);
    }

    @Test (priority = 5, description = "Display the record that matches with the search employee fullname")
    public void searchEmployeeValidS() throws InterruptedException, IOException, ParseException {
        pimPage = new PIMPage(driver);
        driver.findElement(By.partialLinkText("PIM")).click();
        JSONObject userObject = Utils.loadJson("./src/test/resources/Users.json", 0);
        String firstName = userObject.get("firstname").toString();
        String lastName = userObject.get("lastname").toString();
        String employeeName = firstName + " " + lastName;
        pimPage.searchValidEmployee(employeeName);
        Thread.sleep(1500);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)", "");
        Thread.sleep(1500);

        String message_actual = driver.findElements(By.className("oxd-text--span")).get(11).getText();
        String message_expected = "Record Found";
        Assert.assertTrue(message_actual.contains(message_expected));
        Thread.sleep(1000);
    }

    @Test (priority = 6, description = "ID can be updated if it is in the limitation")
    public void updateEmployeeInfoValidS() throws InterruptedException, IOException, ParseException {
        pimPage = new PIMPage(driver);
        int generateId = Utils.genearteRandomNumber(0300, 10000);
        String randomId = String.valueOf(generateId);
        Thread.sleep(1000);
        Utils.updateJSONObject("./src/test/resources/Users.json", "id", randomId,0 );
        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)", "");
        Thread.sleep(1000);
        pimPage.updateEmployee(randomId);
        Thread.sleep(1000);
    }

    @Test (priority = 7, description = "Search the updated id with invalid id")
    public void searchUpdateIdEmployeeInvalidS() throws InterruptedException {
        pimPage = new PIMPage(driver);
        driver.findElement(By.partialLinkText("PIM")).click();
        int getId = 1000000;
        pimPage.searchEmployeeID(String.valueOf(getId));
        Thread.sleep(1500);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)", "");
        Thread.sleep(1500);

        String message_actual = driver.findElements(By.className("oxd-text--span")).get(11).getText();
        String message_expected = "No Records Found";
        Assert.assertTrue(message_actual.contains(message_expected));
        Thread.sleep(3000);
    }

    @Test (priority = 8, description = "Search the updated id with valid id")
    public void searchUpdateIdEmployeeValidS() throws IOException, ParseException, InterruptedException {
        pimPage = new PIMPage(driver);
        driver.findElement(By.partialLinkText("PIM")).click();
        Thread.sleep(1500);
        JSONObject userObject = Utils.loadJson("./src/test/resources/Users.json", 0);
        String getId = userObject.get("id").toString();
        pimPage.searchEmployeeID(getId);
        Thread.sleep(1500);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)", "");
        Thread.sleep(1500);

        String message_actual = driver.findElements(By.className("oxd-text--span")).get(11).getText();
        String message_expected = "Record Found";
        Assert.assertTrue(message_actual.contains(message_expected));
        Thread.sleep(1000);
    }
    @Test (priority = 9, description = "Admin can logout by clicking on logout button")
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
