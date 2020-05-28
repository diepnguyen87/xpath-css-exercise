package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.fail;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_17_User_Interaction {

	String rootFolder = System.getProperty("user.dir");
	WebDriver driver;
	Actions action;
	String javaScriptPath = rootFolder + "/Drag-And-Drop/drag_and_drop_helper.js";
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		//FirefoxProfile profile = new FirefoxProfile();
		//profile.setPreference("dom.webnotifications.enabled", true);
		driver = new FirefoxDriver();
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor)driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		System.out.println(rootFolder);
	}
	
	@Test(enabled = false)
	public void TC_01_Mouse_Hover() {
		/*Open the app under test
		driver.get("https://automationfc.wordpress.com/");
		
		//Check status sub-menu 'Unit Test' before mouse hover
		WebElement unitTestSubMenu = driver.findElement(By.xpath("//ul[@class='sub-menu']//a[text()='UNIT TEST']")); 
		boolean status = unitTestSubMenu.isDisplayed();
		System.out.println("Status: " + status);
		
		//Move hover on menu 'AUTO TEST LEVEL'
		WebElement autoTestLevelMenu = driver.findElement(By.xpath("//a[text()='AUTO TEST LEVEL']"));
		action.moveToElement(autoTestLevelMenu).perform();
		sleepInSecond(2);
		
		//Check status sub-menu 'Unit Test' after mouse hover
		status = unitTestSubMenu.isDisplayed();
		System.out.println("Status: " + status);
		
		//Click on sub-menu 'Unit Test'
		action.click(unitTestSubMenu).perform();
		
		//Verify Page 'Unit Test' is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Category: UNIT TEST')]")).isDisplayed());
		*/
		//===================Hover and Notification=====================//
		driver.get("http://www.myntra.com/");
		sleepInSecond(10);
	}
	
	@Test(enabled = false)
	public void TC_02_Press_And_Hold() {
		//Open the app under test
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		//Click and hold 1->4
		WebElement source = driver.findElement(By.xpath("//ol[@id='selectable']/li[text()='1']"));
		WebElement target = driver.findElement(By.xpath("//ol[@id='selectable']/li[text()='4']"));
		action.clickAndHold(source).moveToElement(target).release().perform();
		
		//Verify
		List<WebElement> selectedItems = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(selectedItems.size(), 4);
	}
	
	@Test(enabled = false)
	public void TC_03_Click_And_Select_Random_Items() {
		//Open the app under test
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		//get all items on list
		List<WebElement> allItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		
		//Keep the Ctr key
		action.keyDown(Keys.CONTROL).perform();;
		
		//Click and select random item 1-3-6-11
		action.click(allItems.get(0)).perform();
		action.click(allItems.get(2)).perform();
		action.click(allItems.get(5)).perform();
		action.click(allItems.get(10)).perform();
		
		//Release the Ctr key
		action.keyUp(Keys.CONTROL).perform();
		
		//get selected items
		List<WebElement> selectedItems = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		
		//Verify
		Assert.assertEquals(selectedItems.size(), 4);
	}
	
	@Test(enabled = false)
	public void TC_04_Double_Click() {
		//Open the app under test
		driver.get("https://automationfc.github.io/basic-form/index.html");
	
		//Click on button 'Double Click Me'
		WebElement source = driver.findElement(By.xpath("//button[contains(text(),'Double click me')]"));
		action.doubleClick(source);
		
		//Verify message 'Hello Automation Guys!' is displayed
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
	}
	

	@Test(enabled = false)
	public void TC_05_Right_Click() {
		//Open the app under test
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		//Right click on the button 'right click me'
		WebElement source = driver.findElement(By.xpath("//p/span[text()='right click me']"));
		action.contextClick(source).perform();
		
		//Hover on 'Quit' at context menu of 'right click me'
		WebElement quitContextMenu = driver.findElement(By.xpath("//ul[contains(@class,'context-menu-root')]//span[text()='Quit']"));
		action.moveToElement(quitContextMenu).perform();;
		
		//Verify
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class, 'hover') and contains(@class, 'visible')]/span[text()='Quit']")).isDisplayed());
		
		//Click 'Quit' on context menu
		action.click(quitContextMenu).perform();
		
		//Switch to alert
		Alert alert = driver.switchTo().alert();
		
		//Verify message on alert
		Assert.assertEquals(alert.getText(), "clicked: quit");
	}
	
	@Test(enabled = false)
	public void TC_06_Drag_And_Drop() {
		//Open the app under test
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		sleepInSecond(2);
		
		//accept cookies
		driver.findElement(By.xpath("//button[@title='Accept Cookies']")).click();
		
		//indicate source and targe element
		WebElement source = driver.findElement(By.id("draggable"));
		WebElement target = driver.findElement(By.id("droptarget"));
		
		//Check message before drag and drop
		Assert.assertEquals(target.getText(), "Drag the small circle here.");
		
		//drag source into target
		action.dragAndDrop(source, target).perform();
		sleepInSecond(2);
		
		//Verify message after drag and drop
		Assert.assertEquals(target.getText(), "You did great!");
	}
	
	@Test(enabled = false)
	public void TC_07_Drag_And_Drop_HTML5_CSS() throws IOException {
		//Open the app under test
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		String sourceCSS = "#column-a";
		String targetCSS = "#column-b";
		
		//indicate source and targe element
		WebElement source = driver.findElement(By.cssSelector(sourceCSS));
		WebElement target = driver.findElement(By.cssSelector(targetCSS));
		
		//inject javascript lib into site, Drag A->B
		String javascript = readFile(javaScriptPath);
		javascript = javascript + "$(\"" + sourceCSS + "\").simulateDragDrop({ dropTarget: \"" + targetCSS + "\"});";
		jsExecutor.executeScript(javascript);
		
		//Verify message after drag and drop
		Assert.assertEquals(target.getText(), "A");
		Assert.assertEquals(source.getText(), "B");
		
		//Drag B->A
		jsExecutor.executeScript(javascript);
		
		//Verify message after drag and drop
		Assert.assertEquals(target.getText(), "B");
		Assert.assertEquals(source.getText(), "A");			
	}
	
	@Test
	public void TC_07_Drag_And_Drop_HTML5_Xpath() throws AWTException{
		//Open the app under test
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		String sourceXpath = "//div[@id='column-a']";
		String targetXpath = "//div[@id='column-b']";
		
		//Drag A->B
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		
		Assert.assertEquals(driver.findElement(By.xpath(sourceXpath)).getText(), "B");
		Assert.assertEquals(driver.findElement(By.xpath(targetXpath)).getText(), "A");			
	
	}
	
	public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	public String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
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
