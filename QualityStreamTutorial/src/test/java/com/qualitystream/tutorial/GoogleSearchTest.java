package com.qualitystream.tutorial;
import static org.junit.Assert.assertEquals;

import java.time.Duration;
//import java.util.concurrent.TimeUnit;

//import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class GoogleSearchTest {
	private WebDriver driver;
	
	@Before
	public void setUp(){

		System.setProperty("webdriver.msedgedriver.driver", "./src/test/resources/chromedriver/msedgedriver.exe ");
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
	}
	
	@Test
	public void testGooglePage() throws InterruptedException {
		System.out.println("Si entra a test");
		Thread.sleep(1000);
		WebElement searchBox = driver.findElement(By.id("APjFqb"));
		searchBox.clear();
		System.out.println("Si limpia");
		searchBox.sendKeys("quality-stream Introducción a la Automatización de Pruebas de Software");
		System.out.println("Si manda el texto");
		searchBox.submit();
		System.out.println("Si envía");
		
		WebDriverWait ewait = new WebDriverWait(driver, Duration.ofSeconds(10));
		ewait.until(ExpectedConditions.titleContains("quality-stream"));
		
		System.out.println(driver.getTitle());
		assertEquals("quality-stream Introducción a la Automatización de Pruebas de Software - Buscar con Google", driver.getTitle());
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
}
