package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_12_Handle_Textbox_TextArea {

	WebDriver driver;

	String email, cName, cDOB, cAddress, cCity, cState, cPin, cMobile, cEmail, cPassword, cID;
	String editEmail, editName, editDOB, editAddress, editCity, editState, editPin, editMobile;
	By txtName = By.name("name");
	By txtGender = By.name("gender");
	By txtDOB = By.name("dob");
	By txtAddress = By.name("addr");
	By txtCity = By.name("city");
	By txtState = By.name("state");
	By txtPin = By.name("pinno");
	By txtMobile = By.name("telephoneno");
	By txtEmail = By.name("emailid");
	By txtPassword = By.name("password");
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		email = "diep.nguyen" + getRandomNum() + "@gmail.com";
		cName = "Kim Nguyen";
		cDOB = "1987-12-18";
		cAddress = "no 1 Nguyen Truong To street";
		cCity = "Hochiminh";
		cState = "Ho Chi Minh";
		cPin = "111222";
		cMobile = "0125369897";
		cEmail = email;
		cPassword = "123456";
		
		driver.get("http://demo.guru99.com/v4");
	}

	@Test
	public void TC_01_Add_New_Customer() {
		// Open the app

		String loginURL = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();

		// Entering your email
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();

		// get userid and password are generated by the email entered before
		String userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		String password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

		// get back to login page
		driver.get(loginURL);

		// login with userID and password
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		
		// verify homepage is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text()," + userID + ")]")).isDisplayed());

		// Select New Customer
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		// Enter customer infos
		driver.findElement(txtName).sendKeys(cName);
		driver.findElement(txtDOB).sendKeys(cDOB);
		driver.findElement(txtAddress).sendKeys(cAddress);
		driver.findElement(txtCity).sendKeys(cCity);
		driver.findElement(txtState).sendKeys(cState);
		driver.findElement(txtPin).sendKeys(cPin);
		driver.findElement(txtMobile).sendKeys(cMobile);
		driver.findElement(txtEmail).sendKeys(email);
		driver.findElement(txtPassword).sendKeys(cPassword);
		driver.findElement(By.name("sub")).click();
		
		//verify create new customer successfully
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).isDisplayed());
	
		cID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), cName);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), cDOB);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), cAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), cCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), cState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), cPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), cMobile);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
	}

	@Test
	public void TC_02_Edit_Customer() {
		//Select Edit Customer
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
	
		//Enter CustomerID need to edit
		driver.findElement(By.name("cusid")).sendKeys(cID);
		driver.findElement(By.name("AccSubmit")).click();
		
		//Verify fields are disabled
		Assert.assertFalse(isElementEnabled(txtName));
		Assert.assertFalse(isElementEnabled(txtGender));
		Assert.assertFalse(isElementEnabled(txtDOB));
			
		//Verify fields are enabled
		Assert.assertTrue(isElementEnabled(txtAddress));
		Assert.assertTrue(isElementEnabled(txtCity));
		Assert.assertTrue(isElementEnabled(txtState));
		Assert.assertTrue(isElementEnabled(txtPin));
		Assert.assertTrue(isElementEnabled(txtMobile));
		Assert.assertTrue(isElementEnabled(txtEmail));
		
		//Verify data = new customer
		Assert.assertEquals(driver.findElement(txtAddress).getText(), cAddress);
		Assert.assertEquals(driver.findElement(txtCity).getAttribute("value"), cCity);
		Assert.assertEquals(driver.findElement(txtState).getAttribute("value"), cState);
		Assert.assertEquals(driver.findElement(txtPin).getAttribute("value"), cPin);
		Assert.assertEquals(driver.findElement(txtMobile).getAttribute("value"), cMobile);
		Assert.assertEquals(driver.findElement(txtEmail).getAttribute("value"), email);
		
		//Edit data
		editEmail = "han.tran" + getRandomNum() + "@yahoo.com";
		editAddress = "no 2 Phan Xich Long street";
		editCity = "Da Nang";
		editState = "Da Nang";
		editPin = "222333";
		editMobile = "0125369898";
		
		driver.findElement(txtAddress).clear();
		driver.findElement(txtAddress).sendKeys(editAddress);
		driver.findElement(txtCity).clear();
		driver.findElement(txtCity).sendKeys(editCity);
		driver.findElement(txtState).clear();
		driver.findElement(txtState).sendKeys(editState);
		driver.findElement(txtPin).clear();
		driver.findElement(txtPin).sendKeys(editPin);
		driver.findElement(txtMobile).clear();
		driver.findElement(txtMobile).sendKeys(editMobile);
		driver.findElement(txtEmail).clear();
		driver.findElement(txtEmail).sendKeys(editEmail);
		
		driver.findElement(By.name("sub")).click();
		
		//verify edit customer successfully
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer details updated Successfully!!!']")).isDisplayed());
	
		//Verify data = edit customer
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editMobile);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editEmail);
	
	}
	
	private int getRandomNum() {
		Random random = new Random();
		return random.nextInt(999999);
	}

	private boolean isElementEnabled(By by) {
		if(driver.findElement(by).isEnabled()){
			return true;
		}
		return false;
	}
	
	
	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}