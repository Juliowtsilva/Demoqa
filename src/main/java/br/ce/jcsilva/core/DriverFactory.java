package br.ce.jcsilva.core;

import java.io.File;
import java.util.Arrays;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	private static WebDriver driver;

	private DriverFactory() {}

		public static WebDriver getDriver() {
			
			if(driver == null) {

				switch (Propriedades.browser) {
				case FIREFOX: driver = new FirefoxDriver(); break;
				case CHROME: ChromeOptions options = new ChromeOptions();
				
				options.addArguments("--disable-notifications", 
									 "--disable-popup-blocking", 
									 "--remote-allow-origins=*",
									 "--disable-extensions",
									 "--disable-infobars"
									// "--blink-settings=imagesEnabled=false"
									 );
				options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
				
				 //File adBlock = new File("Extensions/AdBlock.crx");
				 //if (adBlock.exists()) {
                   //  options.addExtensions(adBlock);
                 //}
				 
				 System.setProperty("webdriver.chrome.driver", "C:\\Selenium Webdriver\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
					
					driver = new ChromeDriver(options); break;
				 default:
	                    throw new IllegalArgumentException("Unsupported browser: " + Propriedades.browser);
				}
				
				//driver = new ChromeDriver();*/

				driver.manage().window().maximize();
				//driver.manage().window().setSize(new Dimension(1366, 768));	 //1200, 765
				
								}
			return driver;

		}

		public static void killDriver() {
			if(driver != null) {
				driver.quit();
				driver = null;
			                   }
		}


}
