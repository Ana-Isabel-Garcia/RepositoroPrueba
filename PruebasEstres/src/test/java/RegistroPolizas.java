import static org.junit.Assert.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

public class RegistroPolizas {
	
	private WebDriver driver;
	//datos generales
	By tipo_poliza = By.id("tipo_poliza");
	By concepto = By.id("concepto_gen");
	By usuario = By.id("usuario");
	By nom_usuario = By.id("nombre_us");
	By servicio = By.id("tipo_servicio");
	By nom_serv = By.id("nom_servicio");
	//realizado por
	By nombre = By.id("nombre");
	By paterno = By.id("apellido_pat");
	By materno = By.id("apellido_mat");
	By btn_general_registrar = By.id("registrar");
	
	//alerta Primera parte del registro completada
	
	//id de poliza individual
	By concepto_des = By.id("concepto");
	By tipo = By.id("tipoGradoPerso");
	By monto = By.id("monto");
	By conepto_pdf =  By.id("concepto_pdf");
	By btn_agregar = By.id("btn_agregar");
	
	//registrar polizas individuales
	By btn_registrar = By.id("boton_registro");
	
	
	
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.msedgedriver.driver","./src/test/resources/chromedriver/msedgedriver.exe");
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://marcg.com.mx/Sistema_CISCIG/view/login/Login.html");
		//driver.get("http://localhost/colegio/Proyect-Master/view/login/Login.html");
		driver.findElement(By.id("usuario")).sendKeys("admin@gmail.com");
		driver.findElement(By.id("password")).sendKeys("Leo1234@");
		
//		driver.findElement(By.id("usuario")).sendKeys("TRABAJADOR1@gmail.com");
//		driver.findElement(By.id("password")).sendKeys("QwertWW123$");
		driver.findElement(By.xpath("//div[@class='boton']/input")).click();
		
	}

	@Test
	public void test() throws InterruptedException {
		// acceder al modulo

		Faker faker = new Faker();
		Random rand = new Random();
		List<String> lista_con = new ArrayList<String>();
        lista_con.add("Contratación de servicios educativos");
        lista_con.add("Pago de servicios profesionales");
        lista_con.add("Contratación de servicios de capacitación");
        lista_con.add("Pago de derechos de autor");
        lista_con.add("Pago de servicios de consultoría");
        lista_con.add("Pago de servicios de capacitación");
        lista_con.add("Pago de servicios de asesoramiento");
        
        WebDriverWait waitMenu = new WebDriverWait(driver, Duration.ofSeconds(10));
		waitMenu.until(ExpectedConditions.titleContains("Menú"));
        
        
        
        for(int veces = 0; veces<500; veces++) {
        	

        	
    		driver.findElement(By.partialLinkText("PÓLIZAS")).click();
    		WebDriverWait waitLista = new WebDriverWait(driver, Duration.ofSeconds(10));
    		waitLista.until(
    				ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@href='../../view/administrativo/Reg_Polizas_General.html']"))));
    		
    		driver.findElement(By.xpath("//a[@href='../../view/administrativo/Reg_Polizas_General.html']")).click();
    	

    		WebDriverWait waitReg = new WebDriverWait(driver, Duration.ofSeconds(10));
    				waitReg.until(ExpectedConditions.titleContains("Registro"));
        	
    				
    		//tipo de poliza
    		int n = rand.nextInt(2);
    		
    			
    		WebElement ddL = driver.findElement(tipo_poliza);
    		List<WebElement> opciones = ddL.findElements(By.tagName("option"));
    		opciones.get(n).click();
    		//concepto
    		int frases = rand.nextInt(7);
    		driver.findElement(concepto).sendKeys(lista_con.get(frases));
    		
    		// tipo de usuario
    		int usuario_index = rand.nextInt(2);
    		WebElement usuario_combo = driver.findElement(usuario);
    		List<WebElement> opciones_usuario = usuario_combo.findElements(By.tagName("option"));
    		
    		if(usuario_index == 0) {
    			opciones_usuario.get(0).click();
    		}else {
    			opciones_usuario.get(2).click();
    		}
    		
            WebDriverWait waitCo = new WebDriverWait(driver, Duration.ofSeconds(10));
            waitCo.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(nom_usuario, By.tagName("option")));
    		
            //nombre de usuario
    		
            WebElement nom_u_combo = driver.findElement(nom_usuario);
    		List<WebElement> opciones_nom_u = nom_u_combo.findElements(By.tagName("option"));
    		
    		int index_nom = rand.nextInt(opciones_nom_u.size());
    		opciones_nom_u.get(index_nom).click();
    				
    		// tipo de servicio
    		WebElement servicio_combo = driver.findElement(servicio);
    		List<WebElement> servicio_opciones = servicio_combo.findElements(By.tagName("option"));
    		
    		int index_servicio = rand.nextInt(servicio_opciones.size());
    		servicio_opciones.get(index_servicio).click();
    		
    		 WebDriverWait waitServ = new WebDriverWait(driver, Duration.ofSeconds(20));
    	     waitServ.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(nom_serv, By.tagName("option")));
    			
    		// nombre de servicio
    	     
    	    WebElement nom_combo = driver.findElement(servicio);
    		List<WebElement> nom_opciones = nom_combo.findElements(By.tagName("option"));
    			
    		int index_serv_nom = rand.nextInt(nom_opciones.size());
    		nom_opciones.get(index_serv_nom).click();
    		
    		//nombre del que realizo
    		driver.findElement(nombre).sendKeys(faker.name().firstName());
            driver.findElement(paterno).sendKeys(faker.name().lastName());
            driver.findElement(materno).sendKeys(faker.name().lastName());
            //primer boton
            driver.findElement(btn_general_registrar).click();
            // primera alerta Primera parte del registro completada
            WebDriverWait waitAlerta = new WebDriverWait(driver, Duration.ofSeconds(20));
            waitAlerta.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
            
            WebDriverWait waitRegi = new WebDriverWait(driver, Duration.ofSeconds(10));
    		waitRegi.until(ExpectedConditions.titleContains("Registro"));
    		
    		// segunda parte del registro
    		
    		for(int i = 0; i<10; i++) {
    			String money = faker.numerify("#####.##");
    			driver.findElement(concepto_des).sendKeys("Banco Banamex Cta. XXXX09 PDF de la factura emitida por el instructor.");
    			// tipo
    		     
    		    WebElement tipo_combo = driver.findElement(tipo);
    			List<WebElement> tipo_opciones = tipo_combo.findElements(By.tagName("option"));
    				
    			tipo_opciones.get(0).click();
    			// monto
    			
    			driver.findElement(monto).sendKeys(money);
    			driver.findElement(conepto_pdf).sendKeys("PDF del comprobante de la transferencia");
    			
    			driver.findElement(btn_agregar).click();
    			
    			WebDriverWait waitAlerta2 = new WebDriverWait(driver, Duration.ofSeconds(10));
    	        waitAlerta2.until(ExpectedConditions.alertIsPresent());
    	     
    	        Alert alert2 = driver.switchTo().alert();
    	        alert2.accept();
    	        
    			driver.findElement(concepto_des).sendKeys("Banco Banamex Cta. XXXX09 PDF de la factura emitida por el instructor.");
    			// tipo
    			
    		    WebElement tipo_combo1 = driver.findElement(tipo);
    			List<WebElement> tipo_opciones1 = tipo_combo1.findElements(By.tagName("option"));
    				
    			tipo_opciones1.get(1).click();
    			// monto
    			
    			driver.findElement(monto).sendKeys(money);
    			driver.findElement(conepto_pdf).sendKeys("PDF del comprobante de la transferencia");
    			
    			driver.findElement(btn_agregar).click();
    		
    			WebDriverWait waitAlerta3 = new WebDriverWait(driver, Duration.ofSeconds(20));
    	        waitAlerta3.until(ExpectedConditions.alertIsPresent());
    	     
    	        Alert alert3 = driver.switchTo().alert();
    	        alert3.accept();
    			
    		}
    		
    		//rellenar todos los files
    		List<WebElement> pdfs = driver.findElements(By.xpath("//input[@type='file']"));
    		
    		
    		for (WebElement inputElement : pdfs) {
                inputElement.sendKeys("C:\\xampp\\htdocs\\RepositoroPrueba\\PruebasEstres\\src\\test\\resources\\comprobante\\prueba.pdf");
    			//inputElement.sendKeys("./src/test/resources/comprobante/prueba.pdf");
    			
    		}
    		
    		driver.findElement(btn_registrar).click();
    		WebDriverWait waitAlerta4 = new WebDriverWait(driver, Duration.ofSeconds(10));
            waitAlerta4.until(ExpectedConditions.alertIsPresent());
         
            Alert alert4 = driver.switchTo().alert();
            alert4.accept();
            
            WebDriverWait waitMostrar = new WebDriverWait(driver, Duration.ofSeconds(10));
    		waitMostrar.until(ExpectedConditions.titleContains("Visualización de pólizas"));
    		System.out.println(veces);
    		
        }
       	
		
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

}
