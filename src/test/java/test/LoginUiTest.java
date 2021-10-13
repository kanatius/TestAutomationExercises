package test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
        LoginUiTest.driver.quit();
    }
    
    @Test
    //verifies wether the first page shown in the search is from wikipedia
    public void testFisrtPageIsWikipedia(){
        
        LoginUiTest.driver.get("https://google.com/");
        
        WebElement input = LoginUiTest.driver.findElement(By.name("q"));
        
        WebDriverWait wait = new WebDriverWait(LoginUiTest.driver, 10);
        
        //put the string in the input and press ENTER
        input.sendKeys("Jhon law" + Keys.ENTER);
        
        WebElement firstResult =  wait.until(presenceOfElementLocated(By.cssSelector("h3")));
        
        //navigate to the first link
        firstResult.click();
        
        assertTrue(LoginUiTest.driver.getCurrentUrl().toLowerCase().contains("wikipedia"));
    }
    
}
