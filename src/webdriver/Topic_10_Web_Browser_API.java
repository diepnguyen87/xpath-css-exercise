package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_10_Web_Browser_API {
	
	WebDriver driver;
	
	  @BeforeClass
	  public void beforeClass() {
		  driver = new FirefoxDriver();
		  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		  driver.manage().window().maximize();
	  }
	  
	  @Test(enabled = false)
	  public void TC_01_Verify_URL() {
		  driver.get("http://live.demoguru99.com");
		  driver.findElement(By.xpath("//div[@class='footer-container']//a[@title='My Account']")).click();
		  //driver.findElement(By.cssSelector("div.footer-container a[title='My Account']")).click();
		  String actualLoginPageURL = driver.getCurrentUrl();
		  String expectedLoginPageURL = "http://live.demoguru99.com/index.php/customer/account/login/";
		  Assert.assertEquals(actualLoginPageURL, expectedLoginPageURL);
		  driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		  String actualRegisterPageURL = driver.getCurrentUrl();
		  String expectedRegisterPageURL = "http://live.demoguru99.com/index.php/customer/account/create/";
		  Assert.assertEquals(actualRegisterPageURL, expectedRegisterPageURL);
	  }
	  
	  @Test(enabled = false)
	  public void TC_02_Verify_Title() {
		  driver.get("http://live.demoguru99.com");
		  driver.findElement(By.cssSelector("div.footer-container a[title='My Account']")).click();
		  String actualLoginPageTitle = driver.getTitle();
		  String expectedLoginPageTitle = "Customer Login";
		  Assert.assertEquals(actualLoginPageTitle, expectedLoginPageTitle);
		  driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		  String actualRegisterPageTitle = driver.getTitle();
		  String expectedRegisterPageTitle = "Create New Customer Account";
		  Assert.assertEquals(actualRegisterPageTitle, expectedRegisterPageTitle);
		  
	  }
	  
	  @Test(enabled = false)
	  public void TC_03_Navigate() {
		  driver.get("http://live.demoguru99.com");
		  driver.findElement(By.cssSelector("div.footer-container a[title='My Account']")).click();
		  driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		  String actualRegisterPageURL = driver.getCurrentUrl();
		  String expectedRegisterPageURL = "http://live.demoguru99.com/index.php/customer/account/create/";
		  Assert.assertEquals(actualRegisterPageURL, expectedRegisterPageURL);
		  driver.navigate().back();
		  String actualLoginPageURL = driver.getCurrentUrl();
		  String expectedLoginPageURL = "http://live.demoguru99.com/index.php/customer/account/login/";
		  Assert.assertEquals(actualLoginPageURL, expectedLoginPageURL);
		  driver.navigate().forward();
		  String actualRegisterPageTitle = driver.getTitle();
		  String expectedRegisterPageTitle = "Create New Customer Account";
		  Assert.assertEquals(actualRegisterPageTitle, expectedRegisterPageTitle);
	  }
	  
	  @Test
	  public void TC_04_Get_Page_Source_Code() {
		  driver.get("http://live.demoguru99.com");
		  driver.findElement(By.cssSelector("div.footer-container a[title='My Account']")).click();
		  String loginPageSource = driver.getPageSource();
		  Assert.assertTrue(loginPageSource.contains("Login or Create an Account"));
		  driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		  String registerPageSource = driver.getPageSource();
		  Assert.assertTrue(registerPageSource.contains("Create an Account"));
	  }
	
	  @AfterClass
	  public void afterClass() {
	  }

}
