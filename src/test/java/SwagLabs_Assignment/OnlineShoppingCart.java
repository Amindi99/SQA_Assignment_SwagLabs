package SwagLabs_Assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

public class OnlineShoppingCart {

    //Global variable section
    String BaseURL = "https://www.saucedemo.com/";
    WebDriver driver;
    String actualText;
    String expectedText;
    Boolean status;

    //Before test section
    @BeforeTest
    public void BeforeTestMethod(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    //Test case section


    //After test section


    //Supportive method section


}
