package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage{
    private final WebDriver webDriver;

    public LoginPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    @FindBy(linkText = "LOGIN")
    WebElement login;
    @FindBy(name = "username")
    WebElement username;
    @FindBy(name = "password")
    WebElement password;
    @FindBy(name = "buttonlogin")
    WebElement buttonlogin;
    @FindBy(className = "error")
    WebElement error;
    @FindBy(className = "errorUsername")
    WebElement errorUsername;
    @FindBy(className = "errorPassword")
    WebElement errorPassword;

    public void getLoginLink(){
        login.click();
    }

    public void setUsername(String username){
        this.username.sendKeys(username);
    }

    public void setPassword(String password){
        this.password.sendKeys(password);
    }

    public void click_login(){
        buttonlogin.click();
    }

    public String getError(){
        return error.getText();
    }

    public String getErrorUsername(){
        return errorUsername.getText();
    }

    public String getErrorPassword(){
        return errorPassword.getText();
    }
}
