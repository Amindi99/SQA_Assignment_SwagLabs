package SwagLabs_Assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class OnlineShoppingCart {

    //Global variable section
    String BaseURL = "https://www.saucedemo.com/";
    WebDriver driver;
    String actualText;
    String expectedText;

    //------------Before test section
    @BeforeTest
    public void BeforeTestMethod() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    //-----------Test case section

    //Test case - Login to swag labs (TC: 001)
    @Test(priority = 1)
    public void landingToProductPage() throws Exception {
        System.out.println("--------------------------------");
        System.out.println("             TC 001             ");
        System.out.println("--------------------------------");

        //Login to the system
        customerLogin();
        System.out.println("Customer login to the SwagLabs success");

        //Verify the landing page is product page
        expectedText = "Products";
        actualText = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();

        if (expectedText.equals(actualText)) {
            System.out.println("Verified the customer is in the " + expectedText + " Page");
        } else {
            System.out.println("Unsuccessful landing page" + '\n');
        }

        //Click menu item
        menuItemClick();
        System.out.println("Menu item clicked and closed successfully");
        System.out.println("------------ TC 001 finished ------------" + '\n');
    }

    //Test case - Add item to cart and remove the added item (TC: 002)
    @Test(priority = 2)
    public void addItem_RemoveItem() throws Exception {
        System.out.println("--------------------------------");
        System.out.println("             TC 002             ");
        System.out.println("--------------------------------");

        //Add item to cart and remove the added item
        addToCart_RemoveClick();
        System.out.println("------------ TC 002 finished ------------" + '\n');

    }

    //Test case - View shopping cart (TC: 003)
    @Test(priority = 3)
    public void viewTheCart() throws Exception {
        System.out.println("--------------------------------");
        System.out.println("             TC 003             ");
        System.out.println("--------------------------------");

        //View the shopping cart
        shoppingCartClick();
        System.out.println("------------ TC 003 finished ------------" + '\n');

    }

    //Test case - Complete checkout process (TC: 004)
    @Test(priority = 4)
    public void completeCheckout() throws Exception {
        System.out.println("--------------------------------");
        System.out.println("             TC 004             ");
        System.out.println("--------------------------------");

        //Checkout
        checkoutButtonClick();
        Thread.sleep(2000);

        //Fill checkout information
        completeCheckoutInfo();
        Thread.sleep(2000);
        System.out.println("------------ TC 004 finished ------------" + '\n');

    }

    //Test case - Validate order details (TC: 005)
    @Test(priority = 5)
    public void validateAndPlaceOrder() throws Exception {
        System.out.println("--------------------------------");
        System.out.println("             TC 005             ");
        System.out.println("--------------------------------");

        //Validate details
        validateDataBeforeFinish();
        Thread.sleep(2000);

        //Finish data validation
        finishButtonClick();
        Thread.sleep(2000);

        //Back to home page
        backToHomeButtonClick();
        Thread.sleep(2000);
        System.out.println("------------ TC 005 finished ------------" + '\n');
    }


    //----------After test section
    @AfterTest
    public void AfterTestMethod() {

    }

    //Supportive method section
    public void customerLogin() throws InterruptedException {

        //Calling the SwagLabs login page
        driver.get(BaseURL);
        Thread.sleep(2000);

        //Get element name for userName and send values
        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        Thread.sleep(2000);
        //Get element name for password and send values
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        Thread.sleep(2000);
        //Get element name for submitButton and send values
        driver.findElement(By.name("login-button")).click();
        Thread.sleep(2000);
    }

    public void menuItemClick() throws InterruptedException {

        //Click menu item
        driver.findElement(By.xpath("//*[@id=\"react-burger-menu-btn\"]")).click();
        Thread.sleep(2000);

        //Close the menu item
        driver.findElement(By.xpath("//*[@id=\"react-burger-cross-btn\"]")).click();
        Thread.sleep(2000);
    }

    public void addToCart_RemoveClick() throws InterruptedException {

        // Add item to cart
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")).click();
        Thread.sleep(2000);
        System.out.println("Item added to the cart");

        // Remove item from the cart
        driver.findElement(By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]")).click();
        Thread.sleep(2000);
        System.out.println("Item removed from the cart");

        // Again add item to cart
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")).click();
        Thread.sleep(2000);
        System.out.println("Again item added to the cart");
    }

    public void shoppingCartClick() throws InterruptedException {

        //Click on shopping cart icon
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        Thread.sleep(2000);

        //Verify that navigate to correct Your cart page
        expectedText = "Your Cart";
        actualText = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();

        if (expectedText.equals(actualText)) {
            System.out.println("Verified the customer is in the " + expectedText + " Page");
        } else {
            System.out.println("Unsuccessful page navigation");
        }

        //Check the selected item count
        expectedText = "1";
        actualText = driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[1]")).getText();

        if (expectedText.equals(actualText)) {
            System.out.println("Correct item count display successful");
        } else {
            System.out.println("Item count display unsuccessful");
        }
        Thread.sleep(3000);

        //Check the currency element
        // Locate the price element
        WebElement priceElement = driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[2]/div[2]/div"));

        String priceText = priceElement.getText();

        // Check the price contains $ mark
        Assert.assertTrue(priceText.contains("$"), "Price does not contain a dollar sign: " + priceText);

        System.out.println("Price contains a dollar($) sign.");
        Thread.sleep(3000);
    }

    public void checkoutButtonClick() throws InterruptedException {

        //click the checkout button
        driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click();
        Thread.sleep(2000);

        //Verify that navigate to Checkout: Your Information page
        expectedText = "Checkout: Your Information";
        actualText = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();

        if (expectedText.equals(actualText)) {
            System.out.println("Verified the customer is in the " + expectedText + " Page");
        } else {
            System.out.println("Unsuccessful page navigation");
        }
    }

    public void completeCheckoutInfo() throws InterruptedException {

        //------VALIDATE CHECKOUT INFO ONLY FILLING FIRST NAME----------
        //Get element name for FirstName and send values
        driver.findElement(By.name("firstName")).sendKeys("Amindi");
        //Click continue button
        driver.findElement(By.name("continue")).click();
        System.out.println("1. First name added successfully");
        System.out.println("Error: Last Name is required");
        Thread.sleep(2000);

        //------VALIDATE CHECKOUT INFO ONLY FILLING FIRST NAME, LAST NAME--------
        //Get element name for LastName and send values
        driver.findElement(By.name("lastName")).sendKeys("Perera");
        //Click continue button
        driver.findElement(By.name("continue")).click();
        System.out.println("2. First name and last name added successfully");
        System.out.println("Error: Postal Code is required");
        Thread.sleep(2000);

        //------VALIDATE CHECKOUT INFO FILLING ALL FIELDS--------
        //Get element name for zipCode and send values
        driver.findElement(By.name("postalCode")).sendKeys("11010");
        //Click continue button
        driver.findElement(By.name("continue")).click();
        System.out.println("3. First name, Last name, Postal code added successfully");
        System.out.println("Checkout information entered successfully");
        Thread.sleep(2000);

        //Verify that navigate to Checkout: Overview page
        expectedText = "Checkout: Overview";
        actualText = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();

        if (expectedText.equals(actualText)) {
            System.out.println("Verified the customer is in the " + expectedText + " Page");
        } else {
            System.out.println("Unsuccessful page navigation");
        }
        Thread.sleep(2000);
    }

    public void validateDataBeforeFinish() throws InterruptedException {

        //Check the selected item count
        expectedText = "1";
        actualText = driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[1]/div[3]/div[1]")).getText();

        if (expectedText.equals(actualText)) {
            System.out.println("Item count displayed successfully");
        } else {
            System.out.println("Incorrect item count displayed");
        }
        Thread.sleep(2000);

        //Check the currency element
        WebElement priceElement = driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[1]/div[3]/div[2]/div[2]/div"));
        // Get the text of the price element
        String priceText = priceElement.getText();
        // Verify that the price contains a dollar sign
        Assert.assertTrue(priceText.contains("$"), "Price does not contain a dollar sign: " + priceText);
        System.out.println("Price contains a dollar($) sign.");
        Thread.sleep(2000);

        //Check the payment information
        expectedText = "SauceCard #31337";
        actualText = driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[2]")).getText();

        if (expectedText.equals(actualText)) {
            System.out.println("Payment Information displayed successfully");
        } else {
            System.out.println("Incorrect Payment Information displayed");
        }
        Thread.sleep(2000);

        //Check the Shipping information
        expectedText = "Free Pony Express Delivery!";
        actualText = driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[4]")).getText();

        if (expectedText.equals(actualText)) {
            System.out.println("Shipping information displayed successfully");
        } else {
            System.out.println("Incorrect Shipping information displayed");
        }
        Thread.sleep(2000);

        //--------------------------------------------------------------------------------------------------------------
        //Verify total amount calculation
        System.out.println("Implement total calculation");

        
    }
        //-------------------------------------------------------------------------------------------------------------

    public void finishButtonClick() throws InterruptedException {

        //click the finish button
        driver.findElement(By.xpath("//*[@id=\"finish\"]")).click();
        Thread.sleep(2000);

        //Verify that navigate to Checkout: Complete! page
        expectedText = "Checkout: Complete!";
        actualText = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();

        if (expectedText.equals(actualText)) {
            System.out.println("Customer is in the Checkout: Complete! page successful");
        } else {
            System.out.println("Unsuccessful page navigation");
        }

        //Verify that navigate to Checkout: Complete! page
        expectedText = "Thank you for your order!";
        actualText = driver.findElement(By.xpath("//*[@id=\"checkout_complete_container\"]/h2")).getText();

        if (expectedText.equals(actualText)) {
            System.out.println("Customer order confirmed successfully");
        } else {
            System.out.println("Customer order is unsuccessful");
        }
    }

    public void backToHomeButtonClick() throws InterruptedException {
        //click the back to home button
        driver.findElement(By.xpath(" //*[@id=\"back-to-products\"]")).click();

        //Verify direct back to product page
        expectedText = "Products";
        actualText = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();

        if (expectedText.equals(actualText)) {
            System.out.println("Customer navigate back to Product page successfully");
        } else {
            System.out.println("Unsuccessful navigate to Product page");
        }
        Thread.sleep(2000);
    }
}






