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
        driver.manage().timeouts().implicitlyWait(10 , TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }
    @Test
    public void testInsurance() throws InterruptedException {
        try {
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
        action.moveToElement(strahovaniePuteshestvenikov);
        strahovaniePuteshestvenikov.click();

        WebElement title = driver.findElement(By.xpath("//div[@class = 'kit-col_xs_12 kit-col_md_0 kit-col_lg_6 kit-col_xs-bottom_20 kit-col_lg-bottom_10 kit-col_xs-top_20 kit-col_lg-top_40']/h1"));
        wait.until(ExpectedConditions.visibilityOf(title));

        Assert.assertEquals("Страхование путешественников", title.getText());

        WebElement oformitOnline = driver.findElement(By.xpath("//div[@class= 'product-teaser-full-width__button']/a[@href = '/dl/travel_ins']"));
        oformitOnline.click();

        WebElement minimalnaya = driver.findElement(By.xpath("//h3[text() = 'Минимальная']"));
        wait.until(ExpectedConditions.visibilityOf(minimalnaya));
        minimalnaya.click();

        WebElement oformit1 = driver.findElement(By.xpath("//button[@class= 'btn btn-primary btn-large']"));
        oformit1.click();


        fillField(By.xpath("//input[@id= 'surname_vzr_ins_0']", "Familia"));
        fillField(By.xpath("//input[@id= 'name_vzr_ins_0']", "Imya"));
        fillField(By.xpath("//input[@id= 'birthDate_vzr_ins_0']", "03.07.1994"));


        fillField(By.xpath("//input[@id= 'person_lastName']", "Летом"));
        fillField(By.xpath("//input[@id= 'person_firstName']", "Хочется"));
        fillField(By.xpath("//input[@id= 'person_middleName']", "Гулять"));
        fillField(By.xpath("//input[@id= 'person_birthDate']", "03.07.1994"));

        WebElement pol = driver.findElement(By.xpath("//label[text()= 'Женский']"));
        pol.click();

        fillField(By.xpath("//input[@id= 'passportSeries']", "0000"));
        fillField(By.xpath("//input[@id= 'passportNumber']", "632123"));
        fillField(By.xpath("//input[@id= 'documentDate']", "05.11.2015"));
        fillField(By.xpath("//input[@id= 'documentIssue']", "УФМС РФ по городу Москве"));

        Assert.assertEquals("Familia", driver.findElement(By.xpath("//input[@id= 'surname_vzr_ins_0']")).getAttribute("value"));
        Assert.assertEquals("Imya", driver.findElement(By.xpath("//input[@id= 'name_vzr_ins_0']")).getAttribute("value"));
        Assert.assertEquals("03.07.1994", driver.findElement(By.xpath("//input[@id= 'birthDate_vzr_ins_0']")).getAttribute("value"));


        Assert.assertEquals("Летом", driver.findElement(By.xpath("//input[@id= 'person_lastName']")).getAttribute("value"));
        Assert.assertEquals("Хочется", driver.findElement(By.xpath("//input[@id= 'person_firstName']")).getAttribute("value"));
        Assert.assertEquals("Гулять", driver.findElement(By.xpath("//input[@id= 'person_middleName']")).getAttribute("value"));
        Assert.assertEquals("03.07.1994", driver.findElement(By.xpath("//input[@id= 'person_birthDate']")).getAttribute("value"));


        driver.findElement(By.xpath("//button[@class= 'btn btn-primary page__btn']")).click();

        Assert.assertEquals("При заполнении данных произошла ошибка", driver.findElement(By.xpath("alert-form alert-form-error")).getText());

    }
    public void fillField(By xpath, String value){
        driver.findElement(xpath).clear();
        driver.findElement(xpath).sendKeys(value);
    }



    @After
    public void afterTest(){
        driver.quit();
    }
}
