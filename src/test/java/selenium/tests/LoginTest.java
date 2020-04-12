package selenium.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.stqa.selenium.wrapper.HighlightingWrapper;
import selenium.pages.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest {

    private WebDriver webDriver;
    private LoginPage loginPage;

    @Before
    public void init(){
        System.setProperty("webdriver.chrome.driver","C://Drivers//ChromeDrivers//chromedriver_win32//chromedriver.exe");
        webDriver = new HighlightingWrapper(new ChromeDriver());
        webDriver.get("http://localhost:4200/ask");
        loginPage = PageFactory.initElements(webDriver, LoginPage.class);
    }

    @After
    public void after(){
//        if(webDriver != null){
//            webDriver.close();
//        }
    }

    // @Test
    public void loginTest_success(){
        loginPage.getLoginLink();
        //webDriver.findElement(By.linkText("LOGIN")).click();
        loginPage.setUsername("sofi_21");
        loginPage.setPassword("qwerty007");
        loginPage.click_login();
        //webDriver.findElement(By.name("username")).sendKeys("sofi_21");
//        webDriver.findElement(By.name("buttonlogin")).click();
    }

    // @Test
    public void loginTest_fail(){
        loginPage.getLoginLink();
        loginPage.setUsername("sofi");
        loginPage.setPassword("qwerty007");
        loginPage.click_login();
        assertThat(loginPage.getError()).contains("Login failed: Error -> Unauthorized");
    }

    // @Test
    public void loginTest_fail_name_required(){
        loginPage.getLoginLink();
        loginPage.setPassword("qwerty007");
        loginPage.click_login();
        assertThat(loginPage.getErrorUsername()).contains("Required");
    }

    // @Test
    public void loginTest_fail_password_required(){
        loginPage.getLoginLink();
        loginPage.setUsername("sofi_21");
        loginPage.click_login();
        assertThat(loginPage.getErrorPassword()).contains("Required");
    }
}
