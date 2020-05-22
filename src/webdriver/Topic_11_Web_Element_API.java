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

public class Topic_11_Web_Element_API {
	
	WebDriver driver;
	By inputMail = By.id("mail");
	By radAgeUnder18 = By.id("under_18");
	By txtAreaEdu = By.id("edu");
	By dropJob1 = By.id("job1");
	By dropJob2 = By.id("job2");
	By chkDevelopment = By.id("development");
	By slider01 = By.id("slider-1");
	By inputPassword = By.id("password");
	By radAgeDisabled = By.id("radio-disabled");
	By txtAreaBio = By.id("bio");
	By dropJob3 = By.id("job3");
	By chkCheckDisabled = By.id("check-disbaled");
	By slider02 = By.id("slider-2");
	
	  @BeforeClass
	  public void beforeClass() {
		  driver = new FirefoxDriver();
		  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		  driver.manage().window().maximize();
	  }
	  
	  @Test(enabled = false)
	  public void TC_01_Verify_Element_Displayed() {
		  driver.get("https://automationfc.github.io/basic-form/index.html");
		  if(isElementDisplay(inputMail)) {
			  driver.findElement(inputMail).sendKeys("Automation Testing");
		  }
		  if(isElementDisplay(radAgeUnder18)) {
			  driver.findElement(radAgeUnder18).click();
		  }
		  if(isElementDisplay(txtAreaEdu)) {
			  driver.findElement(txtAreaEdu).sendKeys("Automation Testing");
		  } 
	  }
	  
	  @Test(enabled = false)
	  public void TC_02_Verify_Element_Enabled() {
		  driver.get("https://automationfc.github.io/basic-form/index.html");
		  Assert.assertTrue(isElementEnabled(inputMail));
		  Assert.assertTrue(isElementEnabled(radAgeUnder18));
		  Assert.assertTrue(isElementEnabled(txtAreaEdu));
		  Assert.assertTrue(isElementEnabled(dropJob1));
		  Assert.assertTrue(isElementEnabled(dropJob2));
		  Assert.assertTrue(isElementEnabled(chkDevelopment));
		  Assert.assertTrue(isElementEnabled(slider01));
		  
		  Assert.assertFalse(isElementEnabled(inputPassword));
		  Assert.assertFalse(isElementEnabled(radAgeDisabled));
		  Assert.assertFalse(isElementEnabled(txtAreaBio));
		  Assert.assertFalse(isElementEnabled(chkCheckDisabled));
		  Assert.assertFalse(isElementEnabled(slider02));
	  }
	  
	  @Test
	  public void TC_03_Verify_Element_Selected() {
		  driver.get("https://automationfc.github.io/basic-form/index.html");
		  driver.findElement(radAgeUnder18).click();
		  driver.findElement(chkDevelopment).click();
		  Assert.assertTrue(isElementSelected(radAgeUnder18));
		  Assert.assertTrue(isElementSelected(chkDevelopment));
		  driver.findElement(chkDevelopment).click();
		  Assert.assertFalse(isElementSelected(chkDevelopment));
	  }
	  
	  private boolean isElementDisplay(By by) {
		  if (driver.findElement(by).isDisplayed()) {
			  return true;
		  }
		  return false;
	  }
	  
	  private boolean isElementEnabled (By by) {
		  if(driver.findElement(by).isEnabled()) {
			  System.out.println(by + " is enabled");
			  return true;
		  }
		  System.out.println(by + " is disabled");
		  return false;
	  }
	  
	  private boolean isElementSelected(By by) {
		  if(driver.findElement(by).isSelected()) {
			  System.out.println(by + " is selected");
			  return true;
		  }
		  System.out.println(by + " is NOT selected");
		  return false;
	  }
	  @AfterClass
	  public void afterClass() {
		  //driver.quit();
	  }
}
