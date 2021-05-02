package skyscannerpack;

import java.awt.AWTException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.junit.Assert;

public class SkyScannerTesting {

	public static void main(String[] args) throws IOException, AWTException, InterruptedException{
		
		//setting path of chrome driver 
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\chromedriver_win32\\chromedriver.exe");    
		WebDriver driver = new ChromeDriver(); 
		Thread.currentThread();
				
		//navigating to SkyScanner
		driver.get("https://www.skyscanner.net");
		driver.manage().window().maximize();
		
		//HTML elements for origin location
		WebElement originName = driver.findElement(By.id("fsc-origin-search"));
		originName.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		Thread.sleep(3000); //not good practice but without these pauses test becomes fragile and it cannot perform properly 
				
		originName.sendKeys("New York");
				
		Thread.sleep(3000);
		
	    Actions act1 = new Actions(driver);
		act1.sendKeys(Keys.TAB).build().perform();
		act1.sendKeys(Keys.RETURN).build().perform();
		
		Thread.sleep(3000);

		
		//HTML elements for destination location
		WebElement destinationName = driver.findElement(By.id("fsc-destination-search"));
		destinationName.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
	    destinationName.sendKeys("Berlin");
	   
	    Thread.sleep(3000);
	        
	    Actions act2 = new Actions(driver);
	    act2.sendKeys(Keys.TAB).build().perform();
	    act2.sendKeys(Keys.RETURN).build().perform();
	    
	    Thread.sleep(3000);

	    //Accessing button for departure date 
	    driver.findElement(By.id("depart-fsc-datepicker-button")).click();
	    
	    Thread.sleep(3000);
	    //Setting date 
	    driver.findElement(By.xpath( "/html/body/div[3]/section/div/div/div[2]/div/table/tbody/tr[3]/td[6]/button")).click();
	                                  
	    //Accessing button for return date 
	      driver.findElement(By.id("return-fsc-datepicker-button")).click();
	    //Setting date 
	      driver.findElement(By.xpath("/html/body/div[3]/section/div/div/div[2]/div/table/tbody/tr[4]/td[6]/button")).click();
	                                   
	    //Create test for default language, country of origin
	      WebElement language = driver.findElement(By.xpath( "/html/body/div[1]/div[1]/header/div/div[1]/nav/ul/li[2]/button/div/span[2]"));
	      String languagevalue = language.getAttribute("innerHTML");
	      String defaultlanguage = "EN";
	      Assert.assertEquals (defaultlanguage,languagevalue);
	      
	      WebElement country = driver.findElement(By.xpath( "//html/body/div[1]/div[1]/header/div/div[1]/nav/ul/li[2]/button/div/img"));
	      String countryvalue = country.getAttribute("title");
	      String defaulcountry = "BA";
	      Assert.assertEquals (defaulcountry,countryvalue);
	      
	  	    
		//Create test for locations 
	    String messageOrigin             = originName.getAttribute("value");
	    String successOrigin             = "New York, NY (Any)";
	    Assert.assertEquals (successOrigin,messageOrigin);
	    
	    String messageDestination           = destinationName.getAttribute("value");
	    String successDestination           = "Berlin Brandenburg (BER)";
	    Assert.assertEquals (successDestination, messageDestination);
	    
	  //Create test for dates
	    WebElement departureDate = driver.findElement(By.id("depart-fsc-datepicker-button"));
	    String messageDeparture            = departureDate.getText();
	    String successDeparture             = "15/05/2021";
	    Assert.assertEquals (successDeparture, messageDeparture);
	    
	    WebElement returnDate = driver.findElement(By.id("return-fsc-datepicker-button"));
	    String messageReturn           = returnDate.getText();
	    String successReturn           = "22/05/2021";
	    Assert.assertEquals (successReturn, messageReturn);
		
	    //Testing search button 
	    WebElement search = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/div/div/div/form/div[3]/button"));
	    search.click();
	    String initialpage = "https://www.skyscanner.net";
	    String newpage = driver.getCurrentUrl();
	    Assert.assertNotEquals (newpage, initialpage);
	    
		
		
		driver.close();
		
		
	}

}
