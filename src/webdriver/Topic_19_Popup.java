package webdriver;

import org.testng.annotations.Test;

import com.thoughtworks.selenium.condition.Condition;

import org.testng.annotations.BeforeClass;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class Topic_19_Popup {

	WebDriver driver;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "./Browser-Drivers/chromedriver.exe");
		//driver = new FirefoxDriver();
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test(enabled = false)
	public void TC_01_Popup() {
		//Open the app under test
		driver.get("https://ngoaingu24h.vn");
		
	}
	
	@Test
	public void TC_02_Random_Popup() {
		//Open the app under test
		driver.get("https://www.javacodegeeks.com/");
		sleepInSecond(90);
			
		//Check popup is display or not
		boolean popupStatus = isElementDisplay("//div[contains(@class,'window-middle-center')]/div[@class='ulp-content']");
		
		//if a popup is displayed, then close it
		if (popupStatus == true) {
				driver.findElement(By.xpath("//a[text()='Close Popup']")).click();
		}
		
		//Identify element 'email'
		WebElement email = driver.findElement(By.name("ulp-email"));
		
		//Scroll to the field 'email'
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", email);
		
		//Enter valid email
		email.sendKeys("diep.test200@gmail.com");
		
		//Click button 'Submit'
		driver.findElement(By.xpath("//a[contains(@class,'ulp-submit')]")).click();
	}
	
	private boolean isElementDisplay(String xpathLocator) {
		try {
			return driver.findElement(By.xpath(xpathLocator)).isDisplayed();
		}catch(NoSuchElementException e) {
			return false;
		}
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
