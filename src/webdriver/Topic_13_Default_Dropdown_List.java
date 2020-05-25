package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_13_Default_Dropdown_List {

	WebDriver driver;
	String email;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		email = "diep.nguyen" + getRandomNumber() + "@gmail.com";
	}
	
	@Test(enabled = false)
	public void TC_01_SingleDefaultDropDownList() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		Select job1Select = new Select(driver.findElement(By.id("job1")));
		Assert.assertFalse(job1Select.isMultiple());
		
		//select "Mobile Testing" by text
		job1Select.selectByVisibleText("Mobile Testing");
		Assert.assertEquals(job1Select.getFirstSelectedOption().getText(), "Mobile Testing");
		
		//select "manual" by value
		job1Select.selectByValue("manual");
		Assert.assertEquals(job1Select.getFirstSelectedOption().getText(), "Manual Testing");
		
		//select "Functional UI Testing" by index
		job1Select.selectByIndex(9);
		Assert.assertEquals(job1Select.getFirstSelectedOption().getText(), "Functional UI Testing");
		
		//verify dropdown size
		Assert.assertEquals(job1Select.getOptions().size(), 10);
		
		//Create new select for Job Role 2
		Select job2Select = new Select(driver.findElement(By.id("job2")));
		Assert.assertTrue(job2Select.isMultiple());
		
		
		//select multiple options at the same time
		job2Select.selectByVisibleText("Automation");
		job2Select.selectByVisibleText("Mobile");
		job2Select.selectByVisibleText("Desktop");
		
		//Verify values are the same as selected
		List<WebElement> selectedOptions = job2Select.getAllSelectedOptions();
		Assert.assertEquals(selectedOptions.get(0).getText(), "Automation");
		Assert.assertEquals(selectedOptions.get(1).getText(), "Mobile");
		Assert.assertEquals(selectedOptions.get(2).getText(), "Desktop");
		
		//deselect 3 aboved options
		job2Select.deselectAll();
		Assert.assertEquals(job2Select.getAllSelectedOptions().size(), 0);
	}
	
	@Test(enabled = false)
	public void TC_02_SingleDefaultDropDownList() {
		driver.get("https://demo.nopcommerce.com");
		
		//Click Register
		driver.findElement(By.className("ico-register")).click();;
		
		//Enter infos into Register form
		driver.findElement(By.id("gender-male")).click();
		driver.findElement(By.id("FirstName")).sendKeys("Diep");
		driver.findElement(By.id("LastName")).sendKeys("Nguyen");
		
		Select dateSelect = new Select(driver.findElement(By.name("DateOfBirthDay")));
		//Select dateSelect = new Select(driver.findElement(By.xpath(" //select[@name='DateOfBirthDay']")));
		
		Assert.assertEquals(dateSelect.getOptions().size(), 32);
		dateSelect.selectByVisibleText("1");
		
		Select monthSelect = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(dateSelect.getOptions().size(), 32);
		monthSelect.selectByVisibleText("May");
		
		Select yearSelect = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(dateSelect.getOptions().size(), 32);
		yearSelect.selectByVisibleText("1980");
		
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys("123456");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");
		
		//Click Register button
		driver.findElement(By.id("register-button")).click();
		
		//Verify Register successfully
		Assert.assertEquals(driver.findElement(By.className("ico-account")).getText(), "My account");
		Assert.assertEquals(driver.findElement(By.className("ico-logout")).getText(), "Log out");
		Assert.assertEquals(driver.findElement(By.className("result")).getText(), "Your registration completed");
	}
	
	@Test
	public void TC_03_MultipleDefaultDropDownList() {
		//Open the app under test
		driver.get("http://multiple-select.wenzhixin.net.cn/examples#basic.html");
		
		//Switch to iframe to able to catch elements on it
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		
		//New intial Select
		Select dd = new Select(driver.findElement(By.xpath("//label[contains(text(),'Basic Select')]/following-sibling::div[@class='col-sm-10']/select")));
		
		//Save expected items need to select on dropdown list
		String[] expectedList = {"February", "August", "December"};
		
		//Select expected items
		for(String expected : expectedList) {
			dd.selectByVisibleText(expected);
		}
	
		//Verify
		List<WebElement> selectedItems = dd.getAllSelectedOptions();
		for(WebElement item : selectedItems) {
			for(String expected : expectedList) {
				if(item.getText().equals(expected)) {
					Assert.assertTrue(true);
					break;
				}
			}
		}
		
	}
	
	private int getRandomNumber() {
		Random random = new Random();
		return random.nextInt(999999);
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
