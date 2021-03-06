package ua.gov.uz.webdriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.opera.core.systems.OperaDriver;

/**
 * @author maksym.mazurkevych
 * 
 */
public class BaseTest extends BrowserConf {


	private boolean isInitalized;
	public static Connection conn;
	public static WebDriver dr = null;

	public static Properties CONFIG = null;
	public static String path = System.getProperty("user.dir")
			+ "\\target\\screenshots\\";

	protected void initializeProperties() throws Exception {

		if (!isInitalized) {
			CONFIG = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir")
							+ "//src//main//resources//config.properties");
			CONFIG.load(ip);
			System.out.println("reading properties from file");
			// OR = new Properties();
			// FileInputStream ip2 = new FileInputStream(
			// System.getProperty("user.dir")+
			// "//src//ua//gov//uz//resources//OR.properties");
			// OR.load(ip2);
			isInitalized = true;
		}
	}
//
//	@BeforeMethod
//	@Parameters({ "browser" })
//	public static WebDriver setUp(@Optional("firefox") String browser) {
//
//		if (!isBrowserOpened) {
//
//			if (browser.equalsIgnoreCase("firefox")) {
//				dr = new FirefoxDriver();
//				System.out.println("Opening browser");
//				isBrowserOpened = true;
//			} else if (browser.equalsIgnoreCase("ie")) {
//
//				System.setProperty("webdriver.ie.driver",
//						System.getProperty("user.dir")
//								+ "\\libs\\IEDriverServer.exe");
//				dr = new InternetExplorerDriver();
//			}
//
//			else if (browser.equalsIgnoreCase("opera")) {
//
//				dr = new OperaDriver();
//			}
//
//			else if (browser.equalsIgnoreCase("chrome")) {
//				System.setProperty("webdriver.chrome.driver",
//						System.getProperty("user.dir")
//								+ "\\libs\\chromedriver.exe");
//				dr = new ChromeDriver();
//				System.out.println("Opening browser");
//				isBrowserOpened = true;
//			}
//
//			isBrowserOpened = true;
//			driver = new EventFiringWebDriver(dr);
//			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//			driver.manage().window().maximize();
//
//		}
//		return driver;
//	}

	@AfterMethod
	public void tearDown() {
		if (isBrowserOpened) {
			System.out.println("Closing browser");
			driver.quit();
			isBrowserOpened = false;
		}

	}

	public static void CaptureScreenshot(String fileName) throws IOException {

		System.out.println("pictures place " + path);

		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + File.separator + "target" + File.separator + "surefire-reports" + File.separator + "html" + File.separator + fileName + ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
