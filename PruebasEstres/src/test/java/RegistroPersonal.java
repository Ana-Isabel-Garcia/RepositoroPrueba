import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static org.junit.Assert.fail;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import AccesoDatos.ReadFile;
import AccesoDatos.WriteFile;

public class RegistroPersonal {
	
	private WebDriver driver;
	//private WriteFile write;
	private ReadFile read;
	String ruta = "C:\\xampp\\htdocs\\colegio\\PruebasColegio\\DataFlows\\Estres\\RegPersonal.Xlsx";
	
	//localizadores
	
	//seccion de datos personales
	By cajaNombre = By.id("nomPerso");
	By cajaPaterno = By.id("apePPerso");
	By cajaMaterno = By.id("apeMPerso");
	By cajaNacimiento = By.id("fechaNacPerso");
	By cajaTelFijo = By.id("telFPerso");
	By cajaTelMovil = By.id("telMPerso");
	By cajaCorreo = By.id("correoPerso");
	By cajaContra = By.id("contraPerso");
	By cajaConfirm = By.id("confiContraPerso");
	By checkCedula = By.id("ceduPerso1");
	By cajaCedula = By.id("cedulaPerso");
	
	//seccion de direccion
	By cajaCodigo = By.id("cpPerso");
	By cajaCalle = By.id("callePerso");
	By cajaColonia = By.id("coloniaPerso");
	
	//seccion educacion
	By cajaGrado = By.id("tipoGradoPerso");
	By checkPasantia = By.id("pasantia1");
	
	//seccion certificacion
	By checkCertificacion = By.id("checkboxcertificacion");
	By cajaNombreCert = By.id("nomCert");
	By cajaOrgCert = By.id("orgCert");
	By cajaEmision = By.id("fechaICert");
	By cajaVigencia = By.id("fechaFCert");
	
	//seccion datos laborales
	By checkLaborales = By.id("checkboxlaboral");
	By cajaEmpresa = By.id("nomEmpPerso");
	By cajaPuesto = By.id("puestoEmpPerso");
	By cajaCorreoL = By.id("correoEmpPerso");
	By cajaTelEmp = By.id("telFEmpPerso");
	By cajaExtension = By.id("ExtTelFEmp");
	By cajaFuncion = By.id("funcionEmpPerso");
	
	//seccion final
	By checkDelitos = By.id("antecedente2");
	By checkVeridico = By.id("veridico1");	
	By checkPrivacidad = By.id("avisos1");	
	
	By botonRegistrar = By.id("boton_registrar");
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.msedgedriver.driver", "./src/test/resources/chromedriver/msedgedriver.exe ");
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://marcg.com.mx/Sistema_CISCIG/view/login/Login.html");

		read = new ReadFile();
		//write = new WriteFile();
		
		//acceder al modulo
		driver.findElement(By.partialLinkText("Regístrate")).click();
		
		WebDriverWait espera = new WebDriverWait(driver, Duration.ofSeconds(10));
		espera.until(ExpectedConditions.titleContains("Regístrate"));
		
		driver.findElement(By.xpath("//nav/ul/li/a[1]")).click();
	}

	@Test
	public void tearDown() throws Exception {
		
		//fase de llenado de datos

		for (int i = 1; i <= read.getFilas(ruta, "Hoja1"); i++) {
			//espera a que se cargue la pagina
			WebDriverWait espera = new WebDriverWait(driver, Duration.ofSeconds(10));
			espera.until(ExpectedConditions.presenceOfElementLocated(cajaNombre));
			System.out.println("entró al módulo");
			
			int columnas = read.getColumnas(ruta, "Hoja1", i);
			String[] datos = new String[columnas+1];

			// ciclo de obtención de datos
			for (int j = 0; j < columnas; j++) {
				datos[j] = read.getCellValue(ruta, "Hoja1", i, j);
			}
			
			//datos generales
			driver.findElement(cajaNombre).sendKeys(datos[0]);
			driver.findElement(cajaPaterno).sendKeys(datos[1]);
			driver.findElement(cajaMaterno).sendKeys(datos[2]);
			driver.findElement(cajaNacimiento).sendKeys(datos[3]);
			driver.findElement(cajaTelFijo).sendKeys(datos[4]);
			driver.findElement(cajaTelMovil).sendKeys(datos[5]);
			driver.findElement(cajaCorreo).sendKeys(datos[6]);
			driver.findElement(cajaContra).sendKeys(datos[7]);
			driver.findElement(cajaConfirm).sendKeys(datos[8]);
			
			//cedula
			driver.findElement(checkCedula).click();
			driver.findElement(cajaCedula).sendKeys(datos[9]);
			
			//direccion
			driver.findElement(cajaCodigo).sendKeys(datos[10]);
			driver.findElement(cajaCalle).sendKeys(datos[11]);
			
			
			/*WebElement listaColonias = driver.findElement(cajaColonia);
			espera.until(ExpectedConditions.stalenessOf(listaColonias));*/
			Thread.sleep(100);
			seleccionar(cajaColonia);
			
			//educacion
			seleccionar(cajaGrado);
			driver.findElement(checkPasantia).click();
			
			//certificación
			try {
			driver.findElement(checkCertificacion).click();
			espera.until(ExpectedConditions.elementToBeClickable(cajaNombreCert));
			driver.findElement(cajaNombreCert).sendKeys(datos[12]);
			driver.findElement(cajaOrgCert).sendKeys(datos[13]);
			driver.findElement(cajaEmision).sendKeys(datos[14]);
			driver.findElement(cajaVigencia).sendKeys(datos[15]);
			}
			
			catch(TimeoutException e){
				System.out.println("no hay certificacion");
			}
			//datos laborales
			
			try {
			driver.findElement(checkLaborales).click();
			espera.until(ExpectedConditions.elementToBeClickable(cajaEmpresa));
			driver.findElement(cajaEmpresa).sendKeys(datos[16]);
			driver.findElement(cajaPuesto).sendKeys(datos[17]);
			driver.findElement(cajaCorreoL).sendKeys(datos[18]);
			driver.findElement(cajaTelEmp).sendKeys(datos[19]);
			driver.findElement(cajaExtension).sendKeys(datos[20]);
			driver.findElement(cajaFuncion).sendKeys(datos[21]);
			}
			
			catch(TimeoutException e){
				System.out.println("no hay datos laborales");
			}
			
			//checks finales
			driver.findElement(checkDelitos).click();
			driver.findElement(checkVeridico).click();
			driver.findElement(checkPrivacidad).click();
			
			try {
				driver.findElement(botonRegistrar).click();
				WebDriverWait waitAlert = new WebDriverWait(driver, Duration.ofSeconds(5));
				waitAlert.until(ExpectedConditions.alertIsPresent());
				cerrarAlerta(i);
			}

			catch (TimeoutException e) {
				System.out.println("Registro fallido");
			}
			
		}
	}

	@After
	public void test() {
		System.out.println("prueba terminada");
		//driver.quit();
		
	}
	
	public void seleccionar(By locator) {	
		WebElement ddL = driver.findElement(locator);
		List<WebElement> opciones = ddL.findElements(By.tagName("option"));
		//System.out.println(opciones.size());
		//System.out.println(opciones.get(0).getText());
		opciones.get(opciones.size()-1).click();
	}

	public void cerrarAlerta(int fila) throws IOException {
		Alert alerta = driver.switchTo().alert();
		System.out.println("Alerta: " + alerta.getText());
		alerta.accept();
	}
}
