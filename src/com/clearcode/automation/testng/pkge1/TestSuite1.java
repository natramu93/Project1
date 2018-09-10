package com.clearcode.automation.testng.pkge1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestSuite1 {
	WebDriver driver;
	@Test(priority=1, groups= {"smoke"})
	public void register() {
		Reporter.log("Register Module");
		driver.findElement(By.xpath("//span[text()='Register']")).click();
		driver.findElement(By.id("firstname")).sendKeys("Natarajan");
		Reporter.log("Username \"Natarajan\" updated");
		driver.findElement(By.id("lastname")).sendKeys("Ramanathan");
	}
	@BeforeMethod
	public void bm() {
		System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://magento.com");
		driver.findElement(By.className("fa-user")).click();
	}
	@Test(priority=2, groups= {"sanity"}, dataProvider="logindata")
	public void login(String user, String password) {
		driver.findElement(By.id("email")).sendKeys(user);
		driver.findElement(By.id("pass")).sendKeys(password);
		driver.findElement(By.id("send2")).click();
		//Assert.assertEquals("", );
		driver.findElement(By.linkText("Log Out")).click();
	}
	@AfterMethod
	public void am() {
		driver.quit();
	}
	@DataProvider
	public Object[][] logindata(){
		return new Object[][] {{"natarajan.ramanathan93@gmail.com","Welcome123"},
								{"abc","password"},
								{"xyz","welcome"}};
	}
	
}
