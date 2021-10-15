package test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

/**
 *
 * @author natan
 */
public class LoginUiTest {

    static WebDriver driver;

    public LoginUiTest() {
    }

    @BeforeAll
    public static void setUpClass() {

        //sets chromedrive path
        System.setProperty("webdriver.chrome.driver", "E:\\Programas\\chromedriver_94_win32\\chromedriver.exe");

        //starts chrome browser
        LoginUiTest.driver = new ChromeDriver();

        LoginUiTest.driver.manage().window().maximize();
    }

    @AfterAll
    public static void tearDownClass() {
        LoginUiTest.driver.quit();
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    @Disabled
    //verifies wether the first page shown in the search is from wikipedia
    public void testFisrtPageIsWikipedia() {

        LoginUiTest.driver.get("https://google.com/");

        WebElement input = LoginUiTest.driver.findElement(By.name("q"));

        WebDriverWait wait = new WebDriverWait(LoginUiTest.driver, 10);

        //put the string in the input and press ENTER
        input.sendKeys("Jhon law" + Keys.ENTER);

        WebElement firstResult = wait.until(
                presenceOfElementLocated(By.cssSelector("h3"))
        );

        //navigate to the first link
        firstResult.click();

        assertTrue(LoginUiTest.driver.getCurrentUrl().toLowerCase().contains("wikipedia"));
    }

    @Test
    @Disabled
    //verifies wether the first page shown in the search is from wikipedia
    public void testthirdPageIsYoutube() {

        LoginUiTest.driver.get("https://google.com/");

        WebElement input = LoginUiTest.driver.findElement(By.name("q"));

        WebDriverWait wait = new WebDriverWait(LoginUiTest.driver, 10);

        //put the string in the input and press ENTER
        input.sendKeys("Jhon law" + Keys.ENTER);

        WebElement thirdElement = wait.until(
                presenceOfAllElementsLocatedBy(By.cssSelector("h3"))
        ).get(2);

        //navigate to the first link
        thirdElement.click();

        assertTrue(LoginUiTest.driver.getCurrentUrl().toLowerCase().contains("youtube"));
    }

    @Test
    @Disabled
    public void testSelect() {

        String url = "E:\\Estudos\\teste\\index.html";

        LoginUiTest.driver.get(url);

        WebElement selectElement = LoginUiTest.driver.findElement(By.id("select-number"));

        Select selectObj = new Select(selectElement);

        selectObj.selectByValue("3");

        assertEquals(selectObj.getFirstSelectedOption().getText(), "3");
    }

    @Test
    public void testExcelInput() throws FileNotFoundException, IOException, InterruptedException {

        String filePath = "E:\\Downloads\\IVSIS_Cred_26May17_1802.xlsx";

        FileInputStream fileStream = new FileInputStream(filePath);

        XSSFWorkbook workbook = new XSSFWorkbook(fileStream);

        //Sheet which needs to be accessed from within the workbook
        XSSFSheet sheet = workbook.getSheet("Credentials");

        //Count the number of rows
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

        //Iterate the Username/Password/
        for (int i = 1; i <= rowCount; i++) {
            //Pass the row number and the cell number from where the value has to be fetched

            System.out.println(sheet.getRow(i).getCell(0).getStringCellValue());
            System.out.println(sheet.getRow(i).getCell(1).getStringCellValue());
        
        }
        
        fileStream.close();
        
        assertTrue(true);
    }

}
