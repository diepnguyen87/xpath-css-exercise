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
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_22_Upload_File {

	WebDriver driver;
	String file1, file2, file3;
	String file1Path, file2Path, file3Path;
	String chromePath1, firefoxPath1, edgePath1;
	String chromePathN, firefoxPathN, edgePathN;
	
	@BeforeClass
	public void beforeClass() {
		String projectDir = System.getProperty("user.dir");
		String imageDir = "D:\\";
		
		//FireFox Browser
		System.setProperty("webdriver.gecko.driver", projectDir + "\\Browser-Drivers\\geckodriver0-26-0.exe");
		driver = new FirefoxDriver();
		
		//Chrome Browser
		//System.setProperty("webdriver.chrome.driver", "./Browser-Drivers/chromedriver.exe");
		//driver = new ChromeDriver();
		
		//Edge Browser
		//System.setProperty("webdriver.edge.driver", "./Browser-Drivers/msedgedriver.exe");
		//driver = new EdgeDriver();
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		file1 = "9.jpg";
		file2 = "10.jpg";
		file3 = "11.jpg";
		
		file1Path = imageDir + file1;
		file2Path = imageDir + file2;
		file3Path = imageDir + file3;
		
		chromePath1 = projectDir + "\\uploadAutoIT\\" + "chromeUploadOneTime.exe";
		firefoxPath1 = projectDir + "\\uploadAutoIT\\" + "firefoxUploadOneTime.exe";
		
		chromePathN = projectDir + "\\uploadAutoIT\\" + "chromeUploadMultiple.exe";
		firefoxPathN = projectDir + "\\uploadAutoIT\\" + "firefoxUploadMultiple.exe";
	
	}
	

	public void TC_01_Upload_1_File_By_SendKey() {
		//Open the app under test
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		//Upload one by one file by sendkey
		String addFileXpath = "//input[@name='files[]']";
		driver.findElement(By.xpath(addFileXpath)).sendKeys(file1Path);
		sleepInSecond(5);
		driver.findElement(By.xpath(addFileXpath)).sendKeys(file2Path);
		sleepInSecond(5);
		driver.findElement(By.xpath(addFileXpath)).sendKeys(file3Path);
		sleepInSecond(5);
	}
	
		
	public void TC_01_Upload_N_File_By_SendKey() {
		//Open the app under test
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		//Upload n file at the same time by sendkey
		String addFileXpath = "//input[@name='files[]']";
		driver.findElement(By.xpath(addFileXpath)).sendKeys(file1Path + "\n" + file2Path + "\n" + file3Path);
		sleepInSecond(5);
		//Verify get 3 files successfully
		List<WebElement> files = driver.findElements(By.xpath("//table[contains(@class,'table-striped')]//tr"));
		Assert.assertEquals(files.size(), 3);
		
		//Click 'Start' on 3 files
		List<WebElement> startElments = driver.findElements(By.xpath("//span[text()='Start']"));
		for(WebElement element : startElments) {
			element.click();
			sleepInSecond(2);
		}
		
		//Verify 3 files uploaded successfully
		List<WebElement> deleteElements = driver.findElements(By.xpath("//span[text()='Delete']"));
		
		for(WebElement element : deleteElements) {
			Assert.assertTrue(element.isDisplayed());
		}
		
	}
	

	public void TC_02_Upload_1_File_By_AutoIT() throws IOException {
		//Open the app under test
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		//Click button 'Add files...'
		driver.findElement(By.xpath("//span[contains(@class, 'fileinput-button')]")).click();
		
		//User AutoIT to handle Open File Dialog
		//Runtime.getRuntime().exec(new String[]{firefoxPath1, file1Path});
		Runtime.getRuntime().exec(new String[]{chromePath1, file2Path});
	}
	
	public void TC_02_Upload_N_File_By_AutoIT() throws IOException {
		//Open the app under test
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		//Click button 'Add files...'
		driver.findElement(By.xpath("//span[contains(@class, 'fileinput-button')]")).click();
		
		//User AutoIT to handle Open File Dialog
		//Runtime.getRuntime().exec(new String[]{firefoxPath, file1Path});
		if(driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[]{firefoxPathN, file1Path, file2Path, file3Path});
		}else if(driver.toString().contains("chrome") || driver.toString().contains("edge")) {
			Runtime.getRuntime().exec(new String[]{chromePathN, file1Path, file2Path, file3Path});
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + file1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + file2 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + file3 + "']")).isDisplayed());
	}
	

	public void TC_03_Upload_File_By_Robot() throws AWTException {
		//Open the app under test
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		//Click button 'Add files...'
		driver.findElement(By.xpath("//span[contains(@class, 'fileinput-button')]")).click();
				
		StringSelection select = new StringSelection(file1Path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		Robot robot = new Robot();
		sleepInSecond(2);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		sleepInSecond(2);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
	}
	
	@Test
	public void TC_04() throws AWTException {
		//Open the app under test
		driver.get("https://gofile.io/?t=uploadFiles");
		
		//Click the button 'Click here'
		driver.findElement(By.id("btnChooseFiles")).click();
		
		//Upload 3 files using robot frame-work
		StringSelection select = new StringSelection("\"" + file1Path + "\" \"" + file2Path + "\" \"" + file3Path + "\"");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		Robot robot = new Robot();
		sleepInSecond(2);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		sleepInSecond(2);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		//Verify 3 files uploaded successfully
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + file1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + file2 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='" + file3 + "']")).isDisplayed());
		
		//Click the button 'Upload'
		driver.findElement(By.id("btnUpload")).click();
		sleepInSecond(15);
		
		//Click 'Ok' on the confirm popup
		driver.findElement(By.xpath("//button[contains(@class,'swal2-confirm')]")).click();
		
		//Click on download link
		driver.findElement(By.id("link")).click();
		
		//Switch to new window
		String parentWindowID = driver.getWindowHandle();
		switchBetween2Windows(parentWindowID);
		
		//turn off VPN popup
		sleepInSecond(15);
		driver.findElement(By.xpath("//button[text()='I have a VPN already']")).click();
		
		//Verify Download icon displayed
		isDowloadIconDisplayed(file1, "Download");
		isDowloadIconDisplayed(file2, "Download");
		isDowloadIconDisplayed(file3, "Download");
		
		//Verify Play icon displayed
		isDowloadIconDisplayed(file1, "Play / Info");
		isDowloadIconDisplayed(file2, "Play / Info");
		isDowloadIconDisplayed(file3, "Play / Info");
		
	}
	
	private boolean isDowloadIconDisplayed(String fileName, String actionName) {
		WebElement element = driver.findElement(By.xpath("//td[text()='" + fileName + "']//following-sibling::td[@class='text-right']//button[@data-original-title='" + actionName + "']"));
		return element.isDisplayed();
	}
	
	private void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
		
	private void switchBetween2Windows(String windowID) {
		Set<String> allWindows = driver.getWindowHandles();
		for(String currentWindow : allWindows) {
			if(!currentWindow.equals(windowID)) {
				driver.switchTo().window(currentWindow);
			}
		}
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
