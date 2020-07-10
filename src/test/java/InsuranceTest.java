import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class InsuranceTest {
    WebDriver driver;
    String baseUrl;

    @Before
    public void beforeTest(){
        System.setProperty("webdriver.chrome.driver", "DRV/chromedriver.exe");
        baseUrl = "http://www.sberbank.ru/ru/person";
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5 , TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }
    @Test
    public void testInsurance() throws InterruptedException {
        Thread.sleep(5000);
        t   try {

            WebElement buttonCookie = driver.findElement(By.xpath("//button[@class = 'kitt-cookie-warning__close']"));
            buttonCookie.click();
        } catch (NoSuchElementException ex) {

        }



        Actions action = new Actions(driver);
        WebElement strahovanie = driver.findElement(By.xpath("//button[@aria-label = 'Меню Страхование'] | //label[@for = 'kitt-top-menu-5']"));

        action.moveToElement(strahovanie).build().perform();
        WebElement strahovaniePuteshestvenikov = driver.findElement(By.xpath("//li[@class = 'lg-menu__sub-item']/a[@href = '/ru/person/bank_inshure/insuranceprogram/life/travel']"));

        Wait<WebDriver> wait = new WebDriverWait(driver,10,1000);
        wait.until(ExpectedConditions.visibilityOf(strahovaniePuteshestvenikov));
        // action.moveToElement(strahovaniePuteshestvenikov);
        strahovaniePuteshestvenikov.click();

        WebElement title = driver.findElement(By.xpath("//div[@class = 'kit-col_xs_12 kit-col_md_0 kit-col_lg_6 kit-col_xs-bottom_20 kit-col_lg-bottom_10 kit-col_xs-top_20 kit-col_lg-top_40']/h1"));
        wait.until(ExpectedConditions.visibilityOf(title));

        Assert.assertEquals("Страхование путешественников", title.getText());
    }

    @After
    public void afterTest(){
        driver.quit();
    }
}
