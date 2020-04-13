package selenium.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.selenium.wrapper.HighlightingWrapper;

public class SignUpTest {
    private WebDriver webDriver;

    @Before
    public void init(){
        System.setProperty("webdriver.chrome.driver","C://Drivers//ChromeDrivers//chromedriver_win32//chromedriver.exe");
        webDriver = new HighlightingWrapper(new ChromeDriver());
        webDriver.get("http://localhost:4200/ask");
    }

    @After
    public void after(){
//        if(webDriver != null){
//            webDriver.close();
//        }
    }

    // @Test
    public void signUpTest_success() throws Exception{
        webDriver.findElement(By.linkText("LOGIN")).click();
        webDriver.findElement(By.name("buttonsignup")).click();
        webDriver.findElement(By.name("name")).sendKeys("Oleg Kovalev");
        webDriver.findElement(By.name("username")).sendKeys("oleg_kovalek");
        webDriver.findElement(By.name("email")).sendKeys("oleg@gmail.com");
        webDriver.findElement(By.name("password")).sendKeys("qwerty007");
        webDriver.findElement(By.name("singupbutton")).click();
        //login
        webDriver.findElement(By.linkText("LOGIN")).click();
        webDriver.findElement(By.name("username")).sendKeys("oleg_kovalek");
        webDriver.findElement(By.name("password")).sendKeys("qwerty007");
        webDriver.findElement(By.name("buttonlogin")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.id("home")).click();
    }
}
