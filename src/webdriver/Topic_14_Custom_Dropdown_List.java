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

public class Topic_14_Custom_Dropdown_List {

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
	public void TC_01_Jquery() {
		//Open the app under test
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
	
		//select a item in dropdown list 'Select a speed'
		String parentLocator1 = "//span[@id='speed-button']";
		String childLocator1 = "//ul[@id='speed-menu']//div";
		selectItemInCustomDropdownList(parentLocator1, childLocator1, "Fast");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath(parentLocator1)).getText(), "Fast");

		//select a item in dropdown list 'Select a file'
		String parentLocator2 = "//span[@id='files-button']";
		String childLocator2 = "//ul[@id='files-menu']//div";
		selectItemInCustomDropdownList(parentLocator2, childLocator2, "ui.jQuery.js");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath(parentLocator2)).getText(), "ui.jQuery.js");

		//select a item in dropdown list 'Select a number'
		String parentLocator3 = "//span[@id='number-button']";
		String childLocator3 = "//ul[@id='number-menu']//div";
		selectItemInCustomDropdownList(parentLocator3, childLocator3, "18");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath(parentLocator3)).getText(), "18");
	
		//select a item in dropdown list 'Select a title'
		String parentLocator4 = "//span[@id='salutation-button']";
		String childLocator4 = "//ul[@id='salutation-menu']//div";
		selectItemInCustomDropdownList(parentLocator4, childLocator4, "Prof.");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath(parentLocator4)).getText(), "Prof.");
	}
	
	@Test(enabled = false)
	public void TC_02_Angular() {
		driver.get("https://bit.ly/2UV2vYi");
		
		//select a item in dropdown list 'Select a speed'
		String parentLocator1 = "//*[@id='games']//span[contains(@class,'e-search-icon')]";
		String childLocator1 = "//ul[@id='games_options']/li";
		selectItemInCustomDropdownList(parentLocator1, childLocator1, "Golf");
		sleepInSecond(2);
		Assert.assertEquals(getElementTextByDom("*[id='games_hidden'] option"), "Golf");
	}
	
	@Test
	public void TC_03_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		String parentLocator = "//i[@class='dropdown icon']";
		String childLocator = "//div[@class='item']/span";
		String expected = "Matt";
		
		selectItemInCustomDropdownList(parentLocator, childLocator, expected);
		
		//Verify
		Assert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'ui')]/div[@class='default text']")).getText(), expected);
	}
	
	public void TC_04_Vuejs() {
			
	}
	
	public void TC_05_Editable() {
		
	}
	
	@Test(enabled = false)
	public void TC_05_MultipleSelect() {
		//Open the app under test
		driver.get("http://multiple-select.wenzhixin.net.cn/examples#basic.html");
		
		//Swich to iframe where the dropdownlist is in
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe")));
		
		//Declare xpath variables
		String parentLocator = "//option[text()='January']/parent::select[contains(@class, 'ms-offscreen')]/following-sibling::div/button[@class='ms-choice']";
		String childLocator = "//option[text()='January']/parent::select[contains(@class, 'ms-offscreen')]/following-sibling::div/div[@class='ms-drop bottom']/ul/li";
		String[] expectedList = {"February", "March", "August", "December"};
		selectMultipleItemsInCustomDropdownList(parentLocator, childLocator, expectedList);
		
		//Verify
		WebElement dropdownElement = driver.findElement(By.xpath(parentLocator));
		String selectedItems = dropdownElement.getText();
		List<WebElement> currentSelectedItems = driver.findElements(By.xpath("//li[@class='selected']//input"));
		if(currentSelectedItems.size() <= 3) {
			for(String expected : expectedList) {
				Assert.assertTrue(selectedItems.contains(expected));
			}
		}else {
			Assert.assertTrue(selectedItems.contains(currentSelectedItems.size()+""));
		}
	}
	
	private void selectItemInCustomDropdownList(String parentLocator, String childLocator, String expected) {
		//Click the dropdown list
		driver.findElement(By.xpath(parentLocator)).click();
		sleepInSecond(1);
		
		//wait to all items on dropdown list display in DOM
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childLocator)));
		
		//Save all items of dropdownlist into List
		List<WebElement> allItems = driver.findElements(By.xpath(childLocator));
		
		//if expected item found, then scroll into the item
		for(WebElement item : allItems) {
			if(item.getText().equals(expected)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				item.click();		
				break;
			}
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
	
	private String getElementTextByDom(String cssLocator) {
		return (String) jsExecutor.executeScript("return document.querySelector(\"" + cssLocator + "\").text");
	}
	
	
	private void selectMultipleItemsInCustomDropdownList(String parentLocator, String childLocator, String[]expectedList) {
		//Click the dropdown list
		driver.findElement(By.xpath(parentLocator)).click();
		sleepInSecond(1);
		
		//wait to all items on dropdown list display in DOM
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childLocator)));
		
		//Save all items of dropdownlist into List
		List<WebElement> allItems = driver.findElements(By.xpath(childLocator));
		
		for(WebElement item : allItems) {
			for(String expected : expectedList) {
				if(item.getText().equals(expected)) {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					sleepInSecond(1);
					item.click();
					sleepInSecond(1);
					
					List<WebElement> currentSelectedItems = driver.findElements(By.xpath("//li[@class='selected']//input"));
					if(currentSelectedItems.size() == expectedList.length) {
						break;
					}
				}
			}
		}	
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
