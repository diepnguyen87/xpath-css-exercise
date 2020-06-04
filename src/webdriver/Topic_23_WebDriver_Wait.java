package webdriver;

import org.testng.annotations.Test;

import com.sun.glass.events.KeyEvent;

import org.testng.annotations.BeforeClass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_23_WebDriver_Wait {

	WebDriver driver;
	WebDriverWait explicitWait;
	
	@BeforeClass
	public void beforeClass() {
		String projectDir = System.getProperty("user.dir");
		//FireFox Browser
		//System.setProperty("webdriver.gecko.driver", projectDir + "\\Browser-Drivers\\geckodriver0-26-0.exe");
		driver = new FirefoxDriver();
		
		//Chrome Browser
		//System.setProperty("webdriver.chrome.driver", "./Browser-Drivers/chromedriver.exe");
		//driver = new ChromeDriver();
		
		driver.manage().window().maximize();
			
	}
	
	public void TC_02_Static_Wait() {
		//Open the app under test
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		//Click the 'Start' button
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		sleepInSecond(5);
		
		//Check result text is 'Hello World!'
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed());
		
	}
	

	public void TC_03_Implicit_Wait() {
		//Open the app under test
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		//define implicit wait
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		//Click the 'Start' button
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		//Check result text is 'Hello World!'
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed());
	
	}
	

	public void TC_04_Explicit_Wait_Invisible() {
		//define explicit wait
		explicitWait = new WebDriverWait(driver, 5);		
		
		//Open the app under test
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		//Click the 'Start' button
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		//Wait loading icon invisible
		WebElement loadingElement = driver.findElement(By.id("loading"));
		explicitWait.until(ExpectedConditions.invisibilityOf(loadingElement));
		
		//Check result text is 'Hello World!'
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed());
	}
	
	public void TC_05_Explicit_Wait_Visible() {
		//define explicit wait
		explicitWait = new WebDriverWait(driver, 5);		
		
		//Open the app under test
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		//Click the 'Start' button
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		//Wait 'Hello World!' visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));
		
		//Check result text is 'Hello World!'
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
	}
	

	@Test
	public void TC_06_Explicit_Wait_Ajax() {
		explicitWait = new WebDriverWait(driver, 30);		
		
		//Open the app under test
		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
	
		WebElement selectedDate = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
		
		Assert.assertEquals(selectedDate.getText(), "No Selected Dates to display.");
		//Select a day on calendar
		driver.findElement(By.xpath("//td[@title='Tuesday, June 23, 2020']")).click();
		
		//Wait for loading icon disappear
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv' and @style=not('display: none;')]")));
		
		//Verify selected date active with blue color
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected' and @title='Tuesday, June 23, 2020']")).isDisplayed());
		
		//Verify selected date displayed
		Assert.assertEquals(selectedDate.getText().trim(), "Tuesday, June 23, 2020");
		
		
	}
	
	private void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
			
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
