package webdriver;

import org.testng.annotations.Test;

import netscape.javascript.JSException;

import org.testng.annotations.BeforeClass;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_20_Iframe_Window {

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("dom.webnotification.enabled", false);
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test(enabled = false)
	public void TC_01_Iframe() {
		//Declare data
		String userName = "Bunny";
		String email = "test@gmail.com";
		String mobile = "0123456789";
				
		//Open the app under test
		driver.get("https://kyna.vn/");
		sleepInSecond(20);
		
		//Verify facebook iframe is enaled
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='fanpage']//iframe")).isEnabled());
		
		//declare facebook iframe
		WebElement fbIframe = driver.findElement(By.xpath("//div[@class='fanpage']//iframe"));
				
		//Switch to facebook iframe by web element
		driver.switchTo().frame(fbIframe);
		
		//Verify switch to facebook iframe successfully by checking 1 element on fbIframe display
		WebElement fbLikes = driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div"));
		Assert.assertTrue(fbLikes.isDisplayed());
		
		//facebook iframe back to kyna
		driver.switchTo().defaultContent();
		
		//Verify web chat iframe displayy
		Assert.assertTrue(driver.findElement(By.id("cs_chat_iframe")).isDisplayed());
		WebElement chatIframe = driver.findElement(By.id("cs_chat_iframe"));
		
		//kyna->chat iframe
		driver.switchTo().frame(chatIframe);
		
		//Click on 'Dang nhap'
		driver.findElement(By.xpath("//button[@class='profile']/span[text()='Đăng nhập']")).click();
		
		//Enter user name
		driver.findElement(By.xpath("//input[@ng-model='userInfo.username']")).sendKeys(userName);
		sleepInSecond(5);
		
		//Enter email
		driver.findElement(By.xpath("//input[@ng-model='userInfo.email']")).sendKeys(email);
		sleepInSecond(5);
		
		//Enter mobile
		driver.findElement(By.xpath("//input[@ng-model='userInfo.phone']")).sendKeys(mobile);
		sleepInSecond(5);
		
		//Select a value in dropdown list
		WebElement serviceList = driver.findElement(By.name("serviceSelect"));
		Select serviceSelect = new Select(serviceList);
		serviceSelect.selectByVisibleText("TƯ VẤN TUYỂN SINH");
		sleepInSecond(5);
		
		//Click on the button 'Luu'
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	
		//Verify entered data above are displayed correctly
		driver.findElement(By.xpath("//button[@class='profile']/span[contains(text(), 'Xin chào')]")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='name_email']/div[contains(@class,'name')]")).getText(), userName);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='name_email']/div[contains(@class,'email')]")).getText(), email);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='name_email']/div[contains(@class,'phone')]")).getText(), mobile);
		
		//back to kyna
		driver.switchTo().defaultContent();
		
		//Close marketing popup
		driver.findElement(By.className("fancybox-close")).click();
		sleepInSecond(2);
		
		//Enter keyword to search
		String keyword = "Java";
		driver.findElement(By.id("live-search-bar")).sendKeys(keyword);
		//driver.findElement(By.xpath("//div[contains(@class,'button-search')]//i[contains(@class,'icon-search')]")).click();
		driver.findElement(By.xpath("//button[contains(@class,'search-button')]")).click();
		
		//Verify keyword after search
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='menu-heading']/h1[contains(text(),'" + keyword + "')]")).getText().contains(keyword));
	}
		
	@Test(enabled = false)
	public void TC_02_Window_1() {
		//Open the app under test
		driver.get("https://automationfc.github.io/basic-form/index.html");

		//Click the link 'GOOGLE'
		String parentID = driver.getWindowHandle();
		driver.findElement(By.linkText("GOOGLE")).click();
		sleepInSecond(10);
		
		//Switch to window 'Google'
		switchToWindowByTitle("Google");
		String googleID = driver.getWindowHandle();
		
		//Verify title of new window = 'Google'
		Assert.assertEquals(driver.getTitle(), "Google");
		
		//Switch to parent window
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		
		//Click the link 'FACEBOOK'
		driver.findElement(By.linkText("FACEBOOK")).click();
		sleepInSecond(10);
		
		//Switch to facebook tab
		switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
		
		//Verify title of new window = 'Facebook - Đăng nhập hoặc đăng ký'
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		
		//switch to parent window
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		
		//Click on 'Tiki' link
		driver.findElement(By.linkText("TIKI")).click();;
		sleepInSecond(10);
		
		//Switch to tiki tab
		switchToWindowByTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		
		//Verify title of new window = 'Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn'
		Assert.assertEquals(driver.getTitle(), "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		
		//Close all windows exclude parent
		closeAllWindowExcludeParent(parentID);
		
		//Verify back to parent window successfully
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
		Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
	}
	
	@Test(enabled = false)
	public void TC_03_Window_2() {
		//Open the app under test
		driver.get("https://kyna.vn/");
		
		//Close the marketing popup
		driver.findElement(By.xpath("//a[@title='Close']")).click();
		String parentTitle = "Kyna.vn - Học online cùng chuyên gia";
		sleepInSecond(2);
		
		//Click icon facebook
		clickByJS("//div[@class='social']//img[@alt='facebook']");
		sleepInSecond(2);
		String fbTitle = "Kyna.vn - Trang chủ | Facebook";
		switchToWindowByTitle(fbTitle);
		Assert.assertEquals(driver.getTitle(), fbTitle);
		
		//Click icon youtube
		switchToWindowByTitle(parentTitle);
		clickByJS("//div[@class='social']//img[@alt='youtube']");
		sleepInSecond(2);
		String ytTitle = "Kyna.vn - YouTube";
		switchToWindowByTitle(ytTitle);
		Assert.assertEquals(driver.getTitle(), ytTitle);
		
		//Click icon zalo
		switchToWindowByTitle(parentTitle);
		clickByJS("//div[@class='social']//img[@alt='zalo']");
		sleepInSecond(2);
		String zlTitle = "Kyna.vn";
		switchToWindowByTitle(zlTitle);
		Assert.assertEquals(driver.getTitle(), zlTitle);
		
		//Click icon apple app
		switchToWindowByTitle(parentTitle);
		clickByJS("//div[@class='icon-app']//img[@alt='apple-app-icon']");
		sleepInSecond(2);
		String appleTitle = "‎KYNA on the App Store";
		switchToWindowByTitle(appleTitle);
		Assert.assertEquals(driver.getTitle(), appleTitle);
		
		//Click icon android app
		switchToWindowByTitle(parentTitle);
		clickByJS("//div[@class='icon-app']//img[@alt='android-app-icon']");
		sleepInSecond(2);
		String androidTitle = "‎KYNA - Học online cùng chuyên gia - Apps on Google Play";
		switchToWindowByTitle(androidTitle);
		//Assert.assertEquals(driver.getTitle(), androidTitle);
		
		//Click icon Kyna on iframe
		switchToWindowByTitle(parentTitle);
		WebElement fbIframe = driver.findElement(By.xpath("//div[@class='face-content']/iframe"));
		driver.switchTo().frame(fbIframe);
		clickByJS("//div[contains(text(),'likes')]/parent::div/preceding-sibling::a");
		sleepInSecond(2);
		switchToWindowByTitle(fbTitle);
		Assert.assertEquals(driver.getTitle(), fbTitle);
				
		//Click icon kyna biz
		switchToWindowByTitle(parentTitle);
		clickByJS("//p[contains(text(),'Đào')]/preceding-sibling::a");
		sleepInSecond(2);
		String bizTitle = "Giải pháp đào tạo nhân sự online toàn diện - KynaBiz.vn";
		switchToWindowByTitle(bizTitle);
		Assert.assertEquals(driver.getTitle(), bizTitle);
		
	}
	
	@Test
	public void TC_04_Window_3() {
		//Open the app under test
		driver.get("http://live.demoguru99.com/index.php/");
		String parentTitle = driver.getTitle();
		
		//Click on Mobile
		driver.findElement(By.linkText("MOBILE")).click();
		
		//Click link 'Add to compare' on Sony Xperia
		By sonyBy = By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']");
		driver.findElement(sonyBy).click();
		
		//Verify 'The product Sony Xperia has been added to comparison list.' display
		By successMsgBy = By.xpath("//li[@class='success-msg']//span");
		Assert.assertEquals(driver.findElement(successMsgBy).getText(), "The product Sony Xperia has been added to comparison list.");
	
		//Click link 'Add to compare' on Samsung
		By samsungBy = By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']");
		driver.findElement(samsungBy).click();
		
		//Verify 'The product Sony Xperia has been added to comparison list.' display
		Assert.assertEquals(driver.findElement(successMsgBy).getText(), "The product Samsung Galaxy has been added to comparison list.");
	
		//Click on 'Compare'
		driver.findElement(By.xpath("//span[text()='Compare']")).click();
		
		//Switch to new window that contain 2 items added to compare
		String newWindow = "Products Comparison List - Magento Commerce";
		switchToWindowByTitle(newWindow);
		
		//Verify title of newWindow
		Assert.assertEquals(driver.getTitle(), newWindow);
		
		//Close newWindow
		driver.close();
		
		//Switch back to parent window
		switchToWindowByTitle(parentTitle);
		
		//Click link 'Clear All'
		driver.findElement(By.linkText("Clear All")).click();
		
		//Accept alert
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		//Verify message 'The comparison list was cleared.' is displayed
		Assert.assertEquals(driver.findElement(successMsgBy).getText(), "The comparison list was cleared.");
		
	}
	
	private void clickByJS(String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
		WebElement element = driver.findElement(By.xpath(xpathLocator));
		jsExecutor.executeScript("arguments[0].click();", element);
	}
	
	private void switchToWindowByID(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for(String window : allWindows) {
			if(!window.equals(parentID)) {
				driver.switchTo().window(window);
				break;
			}
		}
	}
	
	private void switchToWindowByTitle(String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for(String currentWindow : allWindows) {
			driver.switchTo().window(currentWindow);
			if(driver.getTitle().equals(title)) {
				break;
			}
		}
	}
	
	private void closeAllWindowExcludeParent(String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for(String currentWindow : allWindows) {
			if(!currentWindow.equals(parentID)) {
				driver.switchTo().window(currentWindow);
				driver.close();
			}
		}
		
		driver.switchTo().window(parentID);
		
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
