package Functionality;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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

public class Comparescreenshots {
	WebDriver driver;

	@Given("Open the url")
	public void open_the_url() {

		// using WebdriverManager instead of giving local drive location
		WebDriverManager.chromedriver().setup();

		// creating driver object for WebDriver
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get(
				"https://www.frasershospitality.com/en/united-kingdom/london/fraser-suites-kensington-london/accommodation/one-bedroom-deluxe/");
		System.out.println("Open the chrome browser and url of the application");

	}

	@When("Positive Image Comparision")
	public void positive_image_comparision() throws IOException {

		PageFactory.initElements(driver, Elements_f.class);

		// handling cookies by accepting all the cookies
		Elements_f.cookie.click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(0,800)");

		WebDriverWait var1 = new WebDriverWait(driver, Duration.ofSeconds(30));
		var1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//a[@href='/content/dam/frasers-hospitality/english/properties/united-kingdom/kensington/fraser-suites-kensington-london/images/gallery-images/rooms/room-type-images/one-bedroom-deluxe/One%20Bed%20Deluxe%20Living.jpg']")));

		System.out.println("Comparing Image");
	}

	@Then("Images are same,Compared successfully")
	public void images_are_same_compared_successfully() throws IOException {

		BufferedImage expectedImage = ImageIO
				.read(new File(System.getProperty("user.dir") + "/screenshot/fraserimage.png"));

		WebElement element = Elements_f.positiveWebelement;

		Screenshot RoomImagesScreenshot = new AShot().coordsProvider(new WebDriverCoordsProvider())
				.takeScreenshot(driver, element);

		ImageIO.write(RoomImagesScreenshot.getImage(), "png",
				new File(System.getProperty("user.dir") + "/screenshot/fraserRoomimage.png"));

		BufferedImage actualImage = RoomImagesScreenshot.getImage();

		// comparing the image
		ImageDiffer imgDiff = new ImageDiffer();

		// used to store compare result after
		ImageDiff diff = imgDiff.makeDiff(expectedImage, actualImage);

		// if there is any difference then print images are not same
		if (diff.hasDiff() == false) {
			System.out.println("Images are not Same");
		} else {
			System.out.println("Images are Same");
		}

	}

	@When("Negative Image Comparision")
	public void negative_image_comparision() throws IOException {
		BufferedImage expectedImage = ImageIO
				.read(new File(System.getProperty("user.dir") + "/screenshot/fraserimage.png"));

		WebElement element = Elements_f.negativeWebelement;

		Screenshot RoomImagesScreenshot = new AShot().coordsProvider(new WebDriverCoordsProvider())
				.takeScreenshot(driver, element);

		ImageIO.write(RoomImagesScreenshot.getImage(), "png",
				new File(System.getProperty("user.dir") + "/screenshot/fraserRoomimage.png"));

		BufferedImage actualImage = RoomImagesScreenshot.getImage();

		// comparing the image
		ImageDiffer imgDiff = new ImageDiffer();

		// used to store compare result after
		ImageDiff diff = imgDiff.makeDiff(expectedImage, actualImage);

		// if there is any difference then print images are not same
		if (diff.hasDiff() == true) {
			System.out.println("Images are not Same");
		} else {
			System.out.println("Images are Same");
		}
	}

	@Then("Images are not same,Compared successfully")
	public void images_are_not_same_compared_successfully() throws IOException, InterruptedException {
		System.out.println("Images Compared successfully");
		Thread.sleep(3000);
		driver.quit();
	}
}