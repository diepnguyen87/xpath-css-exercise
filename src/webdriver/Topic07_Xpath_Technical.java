package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic07_Xpath_Technical {
  WebDriver driver;
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	  
  }

  @Test(enabled = false)
  public void TC_01_LoginWithEmptyEmailAndPassword() {
	  driver.get("http://live.demoguru99.com/");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.id("send2")).click();
	  String actualErrorMessage = driver.findElement(By.id("advice-required-entry-pass")).getText();
	  String expectedErrorMessage = "This is a required field.";
	  Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
  }
  
  @Test(enabled = false)
  public void TC_02_LoginWithInvalidEmail() {
	  driver.get("http://live.demoguru99.com/");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.id("email")).sendKeys("123434234@12312.123123");
	  driver.findElement(By.id("pass")).sendKeys("123456");
	  driver.findElement(By.name("send")).click();
	  String actualErrorMessage = driver.findElement(By.id("advice-validate-email-email")).getText();
	  String expectedErrorMessage = "Please enter a valid email address. For example johndoe@domain.com.";
	  Assert.assertEquals(actualErrorMessage, expectedErrorMessage);	
  }

  @Test (enabled = false)
  public void TC_03_LoginWithPasswordUnder6Characters() {
	  driver.get("http://live.demoguru99.com/");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.id("email")).sendKeys("diep@gmail.com");
	  driver.findElement(By.id("pass")).sendKeys("123");
	  driver.findElement(By.name("send")).click();
	  String actualErrorMessage = driver.findElement(By.id("advice-validate-password-pass")).getText();
	  String expectedErrorMessage = "Please enter 6 or more characters without leading or trailing spaces.";
	  Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
  }

  @Test (enabled = false )
  public void TC_04_LoginWitInvalidEmail() {
	  driver.get("http://live.demoguru99.com/");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.id("email")).sendKeys("diep@gmail.com");
	  driver.findElement(By.id("pass")).sendKeys("123123");
	  driver.findElement(By.name("send")).click();
	  String actualErrorMessage = driver.findElement(By.xpath("//span[contains(text(), 'Invalid login or p')]")).getText();
	  String expectedErrorMessage = "Invalid login or password.";
	  Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    
  }
  
  @Test (enabled = false)
  public void TC_05_LoginWithValidEmailAndPassword() {
	  driver.get("http://live.demoguru99.com/");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.id("email")).sendKeys("automation_13@gmail.com");
	  driver.findElement(By.id("pass")).sendKeys("123123");
	  driver.findElement(By.name("send")).click();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//strong[contains(text(), 'Hello,')]")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(text(), 'Automation')]")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(., 'automation_13')]")).isDisplayed());
	 
	  driver.findElement(By.xpath("//span[contains(.,'Account')]")).click();
	  driver.findElement(By.xpath("//div[@class='links']//li[last()]")).click();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']//img")).isDisplayed());
  }
  
  @Test (enabled = true)
  public void TC_06_CreateNewAccount() {
	  driver.get("http://live.demoguru99.com/");
	  driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	  driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	  
	  String firstName = "Diep";
	  String lastName = "Nguyen";
	  String email = firstName + "." + lastName + randomNum()+ "@gmail.com";
	  String password = "123123";
	  driver.findElement(By.id("firstname")).sendKeys(firstName);
	  driver.findElement(By.id("lastname")).sendKeys(lastName);
	  driver.findElement(By.id("email_address")).sendKeys(email);
	  driver.findElement(By.id("password")).sendKeys(password);
	  driver.findElement(By.id("confirmation")).sendKeys(password);
	  driver.findElement(By.cssSelector("button[title='Register']")).click();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span[contains(text(),'Thank you for registering')]")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//p[@class='hello']/Strong[contains(text(),'" + firstName + " " + lastName + "')]")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(text(),'" + firstName + " " + lastName + "')]")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p[contains(.,'" + email +"')]")).isDisplayed());
	 
  }
  
  public int randomNum() {
	Random r = new Random();
	return r.nextInt(999999);
  }
  
  @AfterClass
  public void afterClass() {
  }

}
