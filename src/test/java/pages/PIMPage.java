package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PIMPage {

    @FindBy(css = "[type=submit]")
    public WebElement btnSubmit;
    @FindBy(className = "oxd-button--medium")
    public List<WebElement> button;
    @FindBy(name = "firstName")
    public WebElement txtFirstName;
    @FindBy(name = "lastName")
    public WebElement txtLastName;
    @FindBy(className = "oxd-switch-input")
    public WebElement btnToggle;
    @FindBy(className = "oxd-input--active")
    public List<WebElement> txtInput;
    @FindBy(css = "[type = password")
    public List<WebElement> txtPassword;
    @FindBy(tagName = "input")
    public List<WebElement> txtSearchEmployee;
    @FindBy(tagName = "button")
    public List<WebElement> btnUpdate;
    @FindBy(className = "oxd-input")
    public List<WebElement> txtId;
    @FindBy(className = "oxd-main-menu-item")
    public List <WebElement> btnInfo;
    @FindBy(className = "oxd-main-menu-item--name")
    public List <WebElement> btnPim;

    public PIMPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void createEmployee(String firstName, String lastName, String username, String password, String confirmPassword) throws InterruptedException {
        txtFirstName.sendKeys(firstName);
        Thread.sleep(1000);
        txtLastName.sendKeys(lastName);
        Thread.sleep(1000);
        btnToggle.click();
        Thread.sleep(1000);
        txtInput.get(5).sendKeys(username);
        Thread.sleep(1000);
        txtPassword.get(0).sendKeys(password);
        Thread.sleep(1000);
        txtPassword.get(1).sendKeys(confirmPassword);
        Thread.sleep(1000);
        btnSubmit.click();
        Thread.sleep(1000);
    }

    public void searchValidEmployee(String employeeName) throws InterruptedException {
        txtSearchEmployee.get(1).sendKeys(employeeName);
        Thread.sleep(2000);
        btnSubmit.click();
    }

    public void updateEmployee(String randomId) throws InterruptedException {
        btnUpdate.get(6).click();
        Thread.sleep(2000);
        txtId.get(5).sendKeys(Keys.CONTROL + "a" + Keys.BACK_SPACE);
        Thread.sleep(1000);
        txtId.get(5).sendKeys(randomId);
        Thread.sleep(2000);
        btnUpdate.get(1).click();
    }

    public void searchEmployeeID(String employeeId) throws InterruptedException {
        txtId.get(1).sendKeys(employeeId);
        Thread.sleep(2000);
        btnSubmit.click();
        Thread.sleep(2000);
    }

}
