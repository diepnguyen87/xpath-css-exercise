package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
	
	@Test
	public void TC_03_Custom_Checkbox_Radio() {
		//Open the app under test
		driver.get("https://material.angular.io/components/radio/examples");
		
		//Select 'Summer'
		WebElement seasonRadio = driver.findElement(By.xpath("//div[contains(text(),'Summer')]/preceding-sibling::div/input"));
		seasonRadio.click();
		
		//Verify 'Summer' is selected
		Assert.assertTrue(seasonRadio.isSelected());
		
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
