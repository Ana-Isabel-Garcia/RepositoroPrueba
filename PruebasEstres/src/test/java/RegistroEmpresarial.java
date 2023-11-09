import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Alert;

import com.github.javafaker.Faker;

public class RegistroEmpresarial {

	private WebDriver driver;
	// declaramos los elementos que vamos a ustilizar
	By rfc = By.id("rfc");
	By nombre = By.id("nombre");
	By correo = By.id("correo_m");
	By pass = By.id("password");
	By pass_conf = By.id("password_confirmacion");
	By razon = By.id("razon");
	// domicilio fiscal
	By codigo_postal = By.id("codigo_postal");
	By calle = By.id("calle");
    By colonia = By.id("busqueda_colonia");
    By ciudad = By.id("ciudad");
	
	//Horario laboral
	By lunes = By.xpath("//input[@value='2']");
	By martes = By.xpath("//input[@value='3']");
	By miercoles = By.xpath("//input[@value='4']");
	By jueves = By.xpath("//input[@value='5']");
	By viernes = By.xpath("//input[@value='6']");
	By sabado = By.xpath("//input[@value='7']");
	By domingo = By.xpath("//input[@value='1']");
	
    By hora_inicio = By.id("inicio");
    By hora_final = By.id("fin");
    
    //Area de recursos humanos
    By check_rh = By.id("rh_ck");
    By nombre_rh = By.id("rh_nombre");
    By paterno_rh = By.id("rh_paterno");
    By materno_rh = By.id("rh_materno");
    By tele_rh = By.id("rh_tele");
    By ext_rh = By.id("rh_exten");
    By correo_rh = By.id("rh_correo");
    // area de ti
    By check_ti = By.id("ti_ck");
    By nombre_ti = By.id("ti_nombre");
    By paterno_ti = By.id("ti_paterno");
    By materno_ti = By.id("ti_materno");
    By tele_ti = By.id("ti_tele");
    By ext_ti = By.id("ti_exten");
    By correo_ti = By.id("ti_correo");
    //area de capacitacion
    By check_ac = By.id("ac_ck");
    By nombre_ac = By.id("ac_nombre");
    By paterno_ac = By.id("ac_paterno");
    By materno_ac = By.id("ac_materno");
    By tele_ac = By.id("ac_tele");
    By ext_ac = By.id("ac_exten");
    By correo_ac = By.id("ac_correo");
    // acuerdo
    By acuerdo = By.id("acuerdo1");
    //aviso de privacidad
    By aviso = By.id("avisos1");
    
    By btn_enviar = By.id("boton_registrar");
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.msedgedriver.driver","./src/test/resources/chromedriver/msedgedriver.exe");
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://marcg.com.mx/Sistema_CISCIG/view/registro/Reg_Empresa.html");
		
	}
	@Test
	public void testPage() throws InterruptedException{
		
		WebDriverWait waitPagina = new WebDriverWait(driver, Duration.ofSeconds(10));
		waitPagina.until(ExpectedConditions.titleContains("Regístrate"));
		
		Faker faker = new Faker();
		
		for(int i=0; i<200; i++) {
			
			
			
	        String rfc_dato = generarRFC();
	        
	        //primera parte
	        driver.findElement(rfc).sendKeys(rfc_dato);
	       
	        driver.findElement(nombre).sendKeys(faker.company().name());
	        driver.findElement(correo).sendKeys(faker.internet().emailAddress());
	        driver.findElement(pass).sendKeys("QwertWW123$125445");
	        driver.findElement(pass_conf).sendKeys("QwertWW123$125445");
	        driver.findElement(razon).sendKeys("Sociedad de Responsabilidad Limitada");
	        
	        driver.findElement(codigo_postal).sendKeys("99700");
	        

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.textToBePresentInElementValue(ciudad, ""));
	        //Thread.sleep(4000);
			
	        driver.findElement(calle).sendKeys(faker.address().streetName() +" #"+ faker.address().streetAddressNumber());
	        seleccionar(colonia);
	        
	        driver.findElement(lunes).click();
	        driver.findElement(martes).click();
	        driver.findElement(miercoles).click();
	        driver.findElement(jueves).click();
	        driver.findElement(viernes).click();
	        driver.findElement(sabado).click();
	        driver.findElement(domingo).click();
	        
	       
	        driver.findElement(hora_inicio).sendKeys("0800AM");
	        driver.findElement(hora_final).sendKeys("0600PM");
	        
	        driver.findElement(check_rh).click();
	        driver.findElement(nombre_rh).sendKeys(faker.name().firstName());
	        driver.findElement(paterno_rh).sendKeys(faker.name().lastName());
	        driver.findElement(materno_rh).sendKeys(faker.name().lastName());
	        driver.findElement(tele_rh).sendKeys(faker.phoneNumber().cellPhone());
	        driver.findElement(ext_rh).sendKeys(faker.phoneNumber().extension());
	        driver.findElement(correo_rh).sendKeys(faker.internet().emailAddress());
	        
	        driver.findElement(check_ti).click();
	        driver.findElement(nombre_ti).sendKeys(faker.name().firstName());
	        driver.findElement(paterno_ti).sendKeys(faker.name().lastName());
	        driver.findElement(materno_ti).sendKeys(faker.name().lastName());
	        driver.findElement(tele_ti).sendKeys(faker.phoneNumber().cellPhone());
	        driver.findElement(ext_ti).sendKeys(faker.phoneNumber().extension());
	        driver.findElement(correo_ti).sendKeys(faker.internet().emailAddress());
	        
	        driver.findElement(check_ac).click();
	        driver.findElement(nombre_ac).sendKeys(faker.name().firstName());
	        driver.findElement(paterno_ac).sendKeys(faker.name().lastName());
	        driver.findElement(materno_ac).sendKeys(faker.name().lastName());
	        driver.findElement(tele_ac).sendKeys(faker.phoneNumber().cellPhone());
	        driver.findElement(ext_ac).sendKeys(faker.phoneNumber().extension());
	        driver.findElement(correo_ac).sendKeys(faker.internet().emailAddress());
	        
	        driver.findElement(acuerdo).click();
	        driver.findElement(aviso).click();
	        
	        
	        //
	        driver.findElement(btn_enviar).click();
	        WebDriverWait waitAlerta = new WebDriverWait(driver, Duration.ofSeconds(10));
	        waitAlerta.until(ExpectedConditions.alertIsPresent());
	        //Thread.sleep(3000);
	        Alert alert = driver.switchTo().alert();
	        if(alert.getText() == "Esta empresa ya ha sido registrada anteriormente.") {
	        	alert.accept();
	        	rfc_dato = generarRFC();
	        	driver.findElement(rfc).clear();
	        	driver.findElement(rfc).sendKeys(rfc_dato); 
	        	driver.findElement(btn_enviar).click();
	        	
	        }else if(alert.getText() == "El correo electrónico ya ha sido registrado anteriormente.") {
	        	alert.accept();
	        	driver.findElement(correo).clear();;
	        	driver.findElement(correo).sendKeys(faker.internet().emailAddress());
	        	driver.findElement(btn_enviar).click();
	        	
	        }else {
	        	alert.accept();
	        }
	        
			
		}
		
		
        
				
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	public void seleccionar(By locator) {	
		WebElement ddL = driver.findElement(locator);
		List<WebElement> opciones = ddL.findElements(By.tagName("option"));
		//System.out.println(opciones.size());
		//System.out.println(opciones.get(0).getText());
		opciones.get(opciones.size()-1).click();
	}
	
	public String generarRFC() {
		Faker fake = new Faker();
		String rfc = fake.letterify("???").toUpperCase() + fake.numerify("######");
		
		Random rand = new Random();
        int n = rand.nextInt(2);
        if(n == 0) {
        	rfc = rfc + fake.letterify("???").toUpperCase();
        	
        }else {
        	rfc = rfc + fake.numerify("###");
        }
		
		return rfc;
	}

}
