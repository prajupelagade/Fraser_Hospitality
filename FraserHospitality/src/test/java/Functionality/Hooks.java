package Functionality;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	WebDriver driver;
//        
//        @Before
//        public void setup() {
//            System.setProperty("webdriver.chrome.driver", "./src/main/resources/Drivers/chromedriver.exe");
//            driver = new ChromeDriver();
//        }

	@After
	public void tearDown(Scenario scenario) {

		if (scenario.isFailed()) {

			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", "name");
		}
	}

}
