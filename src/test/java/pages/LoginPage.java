package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(name = "username")
    public WebElement txtUsername;
    @FindBy(name = "password")
    public WebElement txtPassword;
    @FindBy(tagName = "button")
    public WebElement btnLogin;
    @FindBy(className = "oxd-userdropdown-name")
    public WebElement btnUserName;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void doLogin(String username, String password){
        txtUsername.sendKeys(username);
        txtPassword.sendKeys(password);
        btnLogin.click();
    }


}
