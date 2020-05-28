package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.fail;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_15_Button_Radio_Checkbox {

	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Alert alert;
	By resultBy = By.id("result");
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 20);
		jsExecutor = (JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test(enabled = false)
	public void TC_01_Click_Button_By_JS() {
		//Open the app under test
		driver.get("http://live.guru99.com/");
		
		//Click on 'My Account' by JS
		WebElement myAccountLink = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
		clickElementByJS(myAccountLink);
		
		//Verify login url
		String actualLoginURL = driver.getCurrentUrl();
		String expectedLoginURL = "http://live.demoguru99.com/index.php/customer/account/login/";
		Assert.assertEquals(actualLoginURL, expectedLoginURL);
		
		//Click on 'Create An Account' button
		WebElement createAnAccountButton = driver.findElement(By.xpath("//a[@title='Create an Account']"));
		clickElementByJS(createAnAccountButton);

		//Verify login url
		String actualCreateAccountURL = driver.getCurrentUrl();
		String expectedCreateAccountURL = "http://live.demoguru99.com/index.php/customer/account/create/";
		Assert.assertEquals(actualCreateAccountURL, expectedCreateAccountURL);			
	}
	
	@Test(enabled = false)
	public void TC_02_Defaul_Checkbox_Radio() {
		//Open the app under test
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		
		//Check on 'Dual-zone air conditioning'
		WebElement dualZoneChk = driver.findElement(By.xpath("//label[contains(text(),'Dual-zone')]/preceding-sibling::input"));
		dualZoneChk.click();
		sleepInSecond(2);
		
		//Verify 'Dual-zone air conditioning' is selected
		Assert.assertTrue(dualZoneChk.isSelected());
		
		//Uncheck 'Dual-zone air conditioning'
		dualZoneChk.click();
		
		//Verify 'Dual-zone air conditioning' is unselected
		Assert.assertFalse(dualZoneChk.isSelected());
		
		//Open new app under test
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		
		//Check on '2.0 Petrol, 147kW'
		WebElement carEngineRadio = driver.findElement(By.xpath("//label[contains(text(),'2.0 Petrol')]/preceding-sibling::input"));
		carEngineRadio.click();
		
		//Verify '2.0 Petrol, 147kW' is selected
		Assert.assertTrue(carEngineRadio.isSelected());
	}
	
	@Test(enabled = true)
	public void TC_03_Custom_Checkbox_Radio() {
		//Open the app under test
		driver.get("https://material.angular.io/components/radio/examples");
		
		//Select 'Summer'
		//WebElement seasonRadio = driver.findElement(By.xpath("//div[contains(text(),'Summer')]/preceding-sibling::div/input"));
		driver.findElement(By.xpath("//input[@value='Summer']")).click();;
		
		//seasonRadio.click();
		
		//Verify 'Summer' is selected
		//Assert.assertTrue(seasonRadio.isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@value='Summer']")).isSelected());
		
		//Open new app under test
		driver.get("https://material.angular.io/components/checkbox/examples");
		
		//Select 'Checked' and 'Indeterminate'
		WebElement checkedChk = driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::div/input"));
		checkedChk.click();
		WebElement indeterminateChk = driver.findElement(By.xpath("//span[text()='Indeterminate']/preceding-sibling::div/input"));
		indeterminateChk.click();
		
		//Verify 'Checked' and 'Indeterminate' are selected
		Assert.assertTrue(checkedChk.isSelected());
		Assert.assertTrue(indeterminateChk.isSelected());
		
		//Unselect 'Checked' and 'Indeterminate'
		checkedChk.click();
		indeterminateChk.click();
		
		//Verify 'Checked' and 'Indeterminate' are unselected
		Assert.assertFalse(checkedChk.isSelected());
		Assert.assertFalse(indeterminateChk.isSelected());
	}
	
	@Test(enabled = false)
	public void TC_04_Accept_Alert() {
		//Open the app under test
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(2);
		
		//click on 'Click for JS Alert'
		WebElement jsAlertButton = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
		jsAlertButton.click();
		
		//wait 20s to load the web fully
		explicitWait.until(ExpectedConditions.alertIsPresent());
	
		//Switch to alert
		alert = driver.switchTo().alert();
		
		//Verify message in alert
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		//Accep alert
		alert.accept();
		
		//Verify message in result
		Assert.assertEquals(driver.findElement(resultBy).getText(), "You clicked an alert successfully");
	}

	@Test(enabled = false)
	public void TC_05_Confirm_Alert() {
		//Open the app under test
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(2);
		
		//click on 'Click for JS Confirm'
		WebElement jsConfirmButton = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
		jsConfirmButton.click();
	
		//wait 20s to load the web fully
		explicitWait.until(ExpectedConditions.alertIsPresent());
		
		//Switch to alert
		alert = driver.switchTo().alert();
		
		//Verify message in alert
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		//Cancel alert
		alert.dismiss();
		
		//Verify message in result
		Assert.assertEquals(driver.findElement(resultBy).getText(), "You clicked: Cancel");
	
	
	}
	
	@Test(enabled = false)
	public void TC_06_Prompt_Alert() {
		//Open the app under test
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(2);
		
		//click on 'Click for JS Alert'
		WebElement jsPromptButton = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
		jsPromptButton.click();
		
		//wait 20s to load the web fully
		explicitWait.until(ExpectedConditions.alertIsPresent());
	
		//Switch to alert
		alert = driver.switchTo().alert();
		
		//Verify message in alert
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		//Enter random text into prompt
		String message = "Hello World";
		alert.sendKeys(message);
		
		//accep alert
		alert.accept();
		
		//Verify message in result
		Assert.assertEquals(driver.findElement(resultBy).getText(), "You entered: " + message);
	
	}
	
	@Test(enabled = false)
	public void TC_07_Authentication_Alert() {
		//Open the app under test
		driver.get("http://the-internet.herokuapp.com");
		
		//get link in href attribue
		String basicAuthURL = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		
		//get url with username and password
		String authenticatedUrl = getURLWithUserNameAndPassword(basicAuthURL, "admin", "admin");
		
		driver.get(authenticatedUrl);
		
		//Verify messsage after login successfully
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	
	private String getURLWithUserNameAndPassword(String url, String userName, String password) {
		String[] stringList = url.split("//");
		url = stringList[0] + userName + ":" + password + "@" + stringList[1];
		return url;
	}
	
	private void clickElementByJS(WebElement element) {
		jsExecutor.executeScript("arguments[0].click();", element);
	}
	
	private void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
