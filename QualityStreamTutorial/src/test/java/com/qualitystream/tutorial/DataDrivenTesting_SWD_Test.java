package com.qualitystream.tutorial;

//import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DataDrivenTesting_SWD_Test {

	private WebDriver driver;
	private WriteExcel writeEcxel;
	private ReadExcel readExcel;
	
	//localizadores
	private By searchBox = By.id("search_query_top");
	private By searchButton = By.name("submit_search");
	private By headingCounter =  By.xpath("//span[@class='heading-counter']");

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.msedgedriver.driver", "./src/test/resources/chromedriver/msedgedriver.exe ");
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.automationpractice.pl/index.php");
		
		readExcel = new ReadExcel();
		writeEcxel = new WriteExcel();
	}

	@Test
	public void test() throws IOException {
		String ruta = "C:\\xampp\\htdocs\\colegio\\PruebasColegio\\DataFlows\\Tutoriales\\test.xlsx";
		//leer la primera celda de lao hoja y obtener el texto
		String searchText = readExcel.getCellValue(ruta, "Hoja1", 0, 2);
		
		driver.findElement(searchBox).sendKeys(searchText);
		driver.findElement(searchButton).click();
		
		//obtener el texto de la eqiqueta
		String textResult = driver.findElement(headingCounter).getText();
		System.out.println("Texto obtenido: " + textResult);
		
		readExcel.readExcel(ruta, "Hoja1");
		
		//escribir en el excel
		writeEcxel.writeCellValue(ruta, "Hoja1", 0, 1, textResult);
		
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Ya se salión de la prueba");
		driver.quit();
	}

}
