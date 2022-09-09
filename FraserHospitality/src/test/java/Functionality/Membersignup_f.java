package Functionality;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import ObjectRepo.Elements_f;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LogoText_page;

public class Membersignup_f {
	static WebDriver driver;

	static JavascriptExecutor js;

	@Given("user launch browser")
	public void user_launch_browser() {

		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver_win32 (3)\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://www.frasershospitality.com/en/fraser-world/sign-up/");

		PageFactory.initElements(driver, Elements_f.class);

	}

	@When("User opens the URL\"https:\\/\\/www.frasershospitality.com\\/en\\/fraser-world\\/sign-up\\/\"")
	public void user_opens_the_url_https_www_frasershospitality_com_en_fraser_world_sign_up() throws Exception {
		driver.manage().deleteAllCookies();
        
		Elements_f.cookie.click();
		
		Thread.sleep(5000);

		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,650)");

		Select value1 = new Select(driver.findElement(By.id("salutation")));
		value1.selectByValue("Ms.");

		Elements_f.FirstName.sendKeys("prajakta");

		Elements_f.LastName.sendKeys("pelagade");
		Thread.sleep(5000);

		Elements_f.Birth.click();

		Robot r1 = new Robot();
		r1.keyPress(KeyEvent.VK_7);
		r1.keyRelease(KeyEvent.VK_7);

		r1.keyPress(KeyEvent.VK_A);
		r1.keyPress(KeyEvent.VK_U);
		r1.keyPress(KeyEvent.VK_G);

		r1.keyRelease(KeyEvent.VK_A);
		r1.keyRelease(KeyEvent.VK_U);
		r1.keyRelease(KeyEvent.VK_G);

		r1.keyPress(KeyEvent.VK_RIGHT);
		r1.keyRelease(KeyEvent.VK_RIGHT);

		r1.keyPress(KeyEvent.VK_2);
		r1.keyRelease(KeyEvent.VK_2);
		r1.keyPress(KeyEvent.VK_0);
		r1.keyRelease(KeyEvent.VK_0);
		r1.keyPress(KeyEvent.VK_0);
		r1.keyRelease(KeyEvent.VK_0);
		r1.keyPress(KeyEvent.VK_2);
		r1.keyRelease(KeyEvent.VK_2);

		Elements_f.country.click();
		driver.findElement(By.className("select2-search__field")).sendKeys("India");
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_DOWN);

		r.keyRelease(KeyEvent.VK_DOWN);

		r.keyPress(KeyEvent.VK_ENTER);

		r.keyRelease(KeyEvent.VK_ENTER);

		Elements_f.source.click();
		Select value3 = new Select(driver.findElement(By.id("source")));
		value3.selectByValue("99999");

		Elements_f.Email.sendKeys("prajupelagade24@gmail.com");
		Elements_f.password.sendKeys("Praju@677");
		Elements_f.confirmpassword.sendKeys("Praju@677");

		Elements_f.Refcode.sendKeys("677687");

		WebElement term1 = driver.findElement(By.id("marketingConsent"));
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", term1);

		boolean stateterm1 = driver.findElement(By.id("marketingConsent")).isSelected();
		if (stateterm1 == true) {
			System.out.println("is selected");

		} else {
			System.out.println("not selected");
		}

		WebElement term2 = driver.findElement(By.id("terms"));
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", term2);

		driver.findElement(By.className("g-recaptcha")).click();
	}

	@Then("page title should be as Fraser world Membership sign up")
	public void page_title_should_be_as_fraser_world_membership_sign_up() throws Exception {
		Thread.sleep(3000);
		System.out.println("Member sign up success");
		driver.quit();

	}

	@After
	public void embedScreenshotOnFail(Scenario s) {
		if (s.isFailed()) {
			// Take a screenshot...
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			s.attach(screenshot, "image/png", s.getName()); // ... and embed it in the report.
		}

	}
}