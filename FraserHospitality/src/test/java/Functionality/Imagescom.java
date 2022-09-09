package Functionality;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import ObjectRepo.Elements_f;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

public class Imagescom {
	WebDriver driver;
	
	@Given("Open url application")
	public void open_url_application() {
		WebDriverManager.chromedriver().setup();

		// creating driver object for WebDriver
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get(
				"https://www.frasershospitality.com/en/");
		//Elements_f.cookie.click();
	    
	}

	@When("launch browser")
	public void launch_browser() throws Exception {
		
		driver.get("https://www.frasershospitality.com/en/united-kingdom/london/fraser-suites-kensington-london/accommodation/one-bedroom-deluxe/");;
		BufferedImage expectedimage = ImageIO
				.read(new File("D:\\workspace automation\\Fraser_Hospitality_In\\logo\\Screenshots\\image.png"));
        
		
		//driver.get("https://www.frasershospitality.com/en/fraser-world/sign-up/");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(0,800)");

		WebElement imageelement = driver
				.findElement(By.xpath("/html/body/div[5]/div/div[6]/div/section/div/div[2]/div/div[1]/div[1]/a/img"));
		
		Screenshot logoimagescreenshot = new AShot().coordsProvider(new WebDriverCoordsProvider())
				.takeScreenshot(driver, imageelement);
		ImageIO.write(logoimagescreenshot.getImage(), "png",
				new File("D:\\workspace automation\\Fraser_Hospitality_In\\Images\\image.png"));
		File f = new File("D:\\workspace automation\\Fraser_Hospitality_In\\Images\\image.png");
		BufferedImage actualimage = logoimagescreenshot.getImage();

		ImageDiffer imgdiff = new ImageDiffer();

		ImageDiff diff = imgdiff.makeDiff(expectedimage, actualimage);

		if (diff.hasDiff() == true) {
			System.out.println("images are not same");
		} else {
			System.out.println("images are same");
		} 
		
	    
	}

	@Then("compare success")
	public void compare_success() throws Exception {
		Thread.sleep(3000);
		driver.quit();
	    
	}

}
