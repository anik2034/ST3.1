import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BarnesAndNobleTest {



        public static WebDriver driver;
        public static String baseUrl = "https://www.barnesandnoble.com";

        @BeforeClass
        public static void initWebDriver(){
            //"C:\Users\aneek\Downloads\chromedriver_win32\chromedriver.exe"
            System.setProperty("webdriver.chrome.driver","C:\\Users\\aneek\\Downloads\\chromedriver_win32\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.get(baseUrl);
        }

        @Test
        public void checkFirstBestSellerTest(){
            SoftAssert sa = new SoftAssert();
         WebElement bestSellers = driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[2]/ul[1]/li[5]/a"));
         bestSellers.click();
         WebElement firstBook = driver.findElement(By.id("resolve_1"));
         String title = firstBook.getAttribute("alt");
         //The first best seller is not six of crows
         sa.assertTrue(!title.equals("Six of Crows"));
         //The first best seller is crying in H mArt
         sa.assertEquals(title,"Crying in H Mart (Signed B&N Exclusive Book)");
         sa.assertAll();

        }

        @Test
        public void searchBookTest(){
            SoftAssert sa = new SoftAssert();
            WebElement searchInput = driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[3]/form/div/div[2]/div/input[1]"));
            searchInput.sendKeys("It Ends with Us");
            WebElement searchButton = driver.findElement(By.xpath("/html/body/div[2]/header/nav/div/div[3]/form/div/span/button"));
            searchButton.click();
            WebElement firstResultTitle = driver.findElement(By.cssSelector("#gridView > div > div:nth-child(1) > div.product-image-container.plp-quick-add-cont > div.pl-0.pr-0.product-shelf-image-cont.plp-qa-img-cont > div > a"));
            String firstResultTitleText = firstResultTitle.getAttribute("title");
            //we searched it ends with us, so the searched first item is it ends with us
            sa.assertTrue(firstResultTitleText.contains("It Ends with Us"));
            // we searched it ends with us, so hayots patmutyun should not be the first result
            sa.assertFalse(firstResultTitleText.contains("Hayots Patmutyun"));
            sa.assertAll();
        }


}
