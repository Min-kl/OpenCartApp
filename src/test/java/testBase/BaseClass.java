package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;



public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	@SuppressWarnings("deprecation")
	@BeforeClass(groups= {"Regression","Sanity","Master"})
	@Parameters({"os","br"})
	public void setUp(String os,String browser) throws IOException{
		
		FileInputStream file=new FileInputStream("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		
		logger =LogManager.getLogger(this.getClass()); //Log4j2 code
		
		if(p.getProperty("Execution_env").equalsIgnoreCase("remote")){
			
			DesiredCapabilities capabilities =new DesiredCapabilities();
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
			}
			else {
				System.out.println("Not matching os");
				return;
			}
			
			switch(browser.toLowerCase()){
				
				case "chrome": capabilities.setBrowserName("chrome");break;
				case "firefox": capabilities.setBrowserName("firefox");break;
				case "edge": capabilities.setBrowserName("MicrosoftEdge");break;
				default: System.out.println("Not a matching browser");return;
			}
			driver=new RemoteWebDriver(new URL("http://192.168.29.156:4444/wd/hub"),capabilities);
			
		}
		
		//Local
		if(p.getProperty("Execution_env").equalsIgnoreCase("local")) {
		switch(browser.toLowerCase()) 
		{
		case "chrome": driver=new ChromeDriver();break;
		case "firefox": driver=new FirefoxDriver();break;
		case "edge": driver=new EdgeDriver();break;
		default: System.out.println("Invalid browser name"); return;
		
		}
		}
		
		driver.manage().deleteAllCookies();
		driver.get(p.getProperty("appURL")); //Reading URL from properties file
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	
	@AfterClass(groups= {"Regression","Sanity","Master"})
	public void tearDown() {
		
		driver.quit();
	}

	@SuppressWarnings("deprecation")
	public String randomString() {
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
		
	}
	
	public String randomNumeric() {
		String generatedNumber=RandomStringUtils.randomNumeric(10);
		return generatedNumber;
		
	}
	
	public String randomAlphaNumeric() {
		String generatedalphanumber=RandomStringUtils.randomAlphanumeric(5);
		return generatedalphanumber;
	}
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}

}
