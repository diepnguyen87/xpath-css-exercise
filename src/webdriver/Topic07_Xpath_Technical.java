package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic07_Xpath_Technical {
  WebDriver driver;
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
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

  @Test
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
  
  @Test
  public void TC_05_LoginWitInvalidEmail() {
	  //jdjkdjfdk
	  //dlfdj
	  ///jjhjh
  }
  
  @AfterClass
  public void afterClass() {
  }

}
