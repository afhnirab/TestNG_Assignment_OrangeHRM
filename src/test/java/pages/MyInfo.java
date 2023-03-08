package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class MyInfo {
    @FindBy(className = "oxd-select-text-input")
    public List <WebElement> dropDownButton;
    @FindBy(tagName = "button")
    public List<WebElement> btnSave;
    @FindBy(className = "oxd-radio-input")
    public List <WebElement> btnRadio;
    @FindBy(className = "orangehrm-tabs-wrapper")
    public List <WebElement> contactBtn;
    @FindBy(className = "oxd-input")
    public List <WebElement> txtInputs;
    @FindBy(className = "oxd-select-text-input")
    public WebElement dropCountry;

    public MyInfo(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public void setGender() throws InterruptedException {
        Thread.sleep(4000);
        btnRadio.get(0).click();
        Thread.sleep(1000);
        btnSave.get(1).click();
        Thread.sleep(1000);
    }

    public void setBloodType() throws InterruptedException {
        dropDownButton.get(2).click();
        dropDownButton.get(2).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        dropDownButton.get(2).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        dropDownButton.get(2).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        dropDownButton.get(2).sendKeys(Keys.ENTER);
        btnSave.get(2).click();
        Thread.sleep(1000);
    }

    public void setContactDetails(String street, String city, String state, String zipCode, String email) throws InterruptedException {
        contactBtn.get(1).click();
        Thread.sleep(3000);
        txtInputs.get(1).sendKeys(street);
        Thread.sleep(1000);
        txtInputs.get(3).sendKeys(city);
        Thread.sleep(1000);
        txtInputs.get(4).sendKeys(state);
        Thread.sleep(1000);
        txtInputs.get(5).sendKeys(zipCode);
        Thread.sleep(1000);
        txtInputs.get(9).sendKeys(email);
        Thread.sleep(1000);
        dropCountry.click();
        dropCountry.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        dropCountry.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        dropCountry.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        dropCountry.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(1000);
        dropCountry.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        btnSave.get(1).click();
        Thread.sleep(1000);
    }

}
