package webdriver;

import org.testng.annotations.Test;

import com.google.common.base.Function;
import org.testng.annotations.BeforeClass;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_23_WebDriver_Wait {

	WebDriver driver;
	WebDriverWait explicitWait;
	FluentWait<WebElement> fluentElement;
	FluentWait<WebDriver> fluentDriver;
	
	@BeforeClass
	public void beforeClass() {
		//String projectDir = System.getProperty("user.dir");
		//FireFox Browser
		//System.setProperty("webdriver.gecko.driver", projectDir + "\\Browser-Drivers\\geckodriver0-26-0.exe");
		driver = new FirefoxDriver();
		
		//Chrome Browser
		//System.setProperty("webdriver.chrome.driver", "./Browser-Drivers/chromedriver.exe");
		//driver = new ChromeDriver();
		
		//System.setProperty("webdriver.edge.driver", "./Browser-Drivers/msedgedriver.exe");
		//driver = new EdgeDriver();
				
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
		//explicitWait.until(ExpectedConditions.invisibilityOf(loadingElement));
		
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
	

	public void TC_06_Explicit_Wait_Ajax() {
		explicitWait = new WebDriverWait(driver, 30);		
		
		//Open the app under test
		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
	
		WebElement selectedDate = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
		
		Assert.assertEquals(selectedDate.getText().trim(), "No Selected Dates to display.");
		
		//Select a day on calendar
		driver.findElement(By.xpath("//td[@title='Tuesday, June 23, 2020']")).click();
		
		
		//Wait for loading icon disappear
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv' and @style=not('display: none;')]")));
		
		//Verify selected date active with blue color
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected' and @title='Tuesday, June 23, 2020']")).isDisplayed());
		
		//Redefine
		selectedDate = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
		
		 //Verify selected date displayed
		Assert.assertEquals(selectedDate.getText().trim(), "Tuesday, June 23, 2020");
		
		
	}
	
	@Test
	public void TC_07_Fluent_Wait() {
		//Open the app under test
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		//wait until coutdown time is visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id, 'countdown_time')]")));
				
		//Create fluent wait
		//set countdown time = 15s
		//check condition 'count down == 02' every 1s
		WebElement element = driver.findElement(By.xpath("//div[contains(@id, 'countdown_time')]"));
		fluentElement = new FluentWait<WebElement>(element);
		fluentElement.withTimeout(15, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		
		fluentElement.until(new Function<WebElement, Boolean>(){
			@Override
			public Boolean apply(WebElement element) {
				String temp = element.getText();
				System.out.println(temp);
				boolean flag = temp.endsWith("02");
				return flag;
			}
		});
	}
	
	
	public void TC_08_Fluent_Wait() {
		//Open the app under test
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		//Click the 'Start' button
		driver.findElement(By.xpath("//button[text()='Start']")).click();
		
		//Create fluent wait
		//set countdown time = 15s
		//every 0.1s verify 'Hello World' is display or not
		fluentDriver = new FluentWait<WebDriver>(driver);
		fluentDriver.withTimeout(15, TimeUnit.SECONDS)
				.pollingEvery(100, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
		
		WebElement helloText = fluentDriver.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath("//div[@id='finish']/h4"));
			}
		});
		Assert.assertTrue(helloText.getText().equals("Hello World!"));
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
