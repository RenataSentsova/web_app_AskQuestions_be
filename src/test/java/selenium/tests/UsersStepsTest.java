package selenium.tests;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.stqa.selenium.wrapper.HighlightingWrapper;

import java.util.List;


public class UsersStepsTest {
    private WebDriver webDriver;

    @Before
    public void init(){
        System.setProperty("webdriver.chrome.driver","C://Drivers//ChromeDrivers//chromedriver_win32//chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        webDriver = new HighlightingWrapper(new ChromeDriver(options));
        webDriver.get("http://localhost:4200/ask");
    }

    // @Test
    public void StepsTest() throws Exception{
        // log in
        webDriver.findElement(By.linkText("LOGIN")).click();
        webDriver.findElement(By.name("username")).sendKeys("sofi_21");
        webDriver.findElement(By.name("password")).sendKeys("qwerty007");
        webDriver.findElement(By.name("buttonlogin")).click();
        Thread.sleep(2000); //!!!
        webDriver.findElement(By.id("home")).click();

        // add a new q
        webDriver.findElement(By.id("ask")).click();
        webDriver.findElement(By.name("topic")).sendKeys("Can you help me to find one book?");
        webDriver.findElement(By.name("text")).sendKeys("I don't remember name of this book. Its author is....");
        webDriver.findElement(By.linkText("Art, Culture")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.linkText("Other Arts, Culture")).click();
        webDriver.findElement(By.id("addButton")).click();
        Thread.sleep(2000);

        // look at new question
        webDriver.findElement(By.id("cats")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.linkText("Art, Culture")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.linkText("Other Arts, Culture")).click();
        Thread.sleep(5000);
        webDriver.findElement(By.linkText("Go to the question")).click();
        Thread.sleep(2000);

        // log out
        webDriver.findElement(By.id("home")).click();
        webDriver.findElement(By.id("logoutButton")).click();
        Thread.sleep(2000);

        // log in by another
        webDriver.findElement(By.linkText("LOGIN")).click();
        webDriver.findElement(By.name("username")).sendKeys("gri_g");
        webDriver.findElement(By.name("password")).sendKeys("qwerty007");
        webDriver.findElement(By.name("buttonlogin")).click();
        Thread.sleep(2000);

        // find a new q
        webDriver.findElement(By.id("cats")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.linkText("Art, Culture")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.linkText("Other Arts, Culture")).click();
        Thread.sleep(2000);
        List<WebElement> butts = webDriver.findElements(By.className("knopka"));
        butts.get(0).click();
        Thread.sleep(2000);

        // add answer
        webDriver.findElement(By.className("knopka")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.name("text")).sendKeys("Lets see...");
        Thread.sleep(2000);
        webDriver.findElement(By.className("knopka")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.id("back")).click();
    }

}
