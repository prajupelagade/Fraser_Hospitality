package Functionality;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import ObjectRepo.Elements_f;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Readdata {
	WebDriver driver;

	@Given("Open the chrome browser and url of the application")
	public void open_the_chrome_browser_and_url_of_the_application() {

		WebDriverManager.chromedriver().setup();
		
		driver = new ChromeDriver();
		driver.get("https://www.frasershospitality.com/en/");
		System.out.println("given donee");
	}

	@When("Enter the country")
	public void enter_the_country_and_city() throws IOException {
		driver.manage().window().maximize();

		PageFactory.initElements(driver, Elements_f.class);

		// handling cookies by accepting all the cookies

		Elements_f.cookie.click();

		// Taking the country name from excel
		FileInputStream fi = new FileInputStream("src//test//java//DataDriven//Countryname.xlsx");

		XSSFWorkbook w1 = new XSSFWorkbook(fi);

		XSSFSheet s1 = w1.getSheetAt(0);
		for (int i = 1; i <= s1.getLastRowNum(); i++) {

			XSSFRow r1 = s1.getRow(i);
			String Country = r1.getCell(0).getStringCellValue();

			Elements_f.Countryname.sendKeys(Country);
		}

		// clicking the check rates button
		Elements_f.Checkrates.click();
		driver.navigate().to(
				"https://www.frasershospitality.com/en/search-results/?country=&countryId=&city=&cityId=&promo=&adult=1&children=0&childrenAge=&depart=2022-08-24&arrive=2022-08-23");

		// Taking the country and city name from excel
		FileInputStream FiCc = new FileInputStream("src//test//java//DataDriven//CountryCity.xlsx");

		XSSFWorkbook w2 = new XSSFWorkbook(FiCc);

		XSSFSheet s2 = w2.getSheetAt(0);

		for (int i = 1; i <= s2.getLastRowNum(); i++) {

			XSSFRow row = s2.getRow(i);

			String Country = row.getCell(0).getStringCellValue();

			String City = row.getCell(1).getStringCellValue();

			// selecting the country from drop down
			Select country = new Select(Elements_f.statuscountry);
			country.selectByVisibleText(Country);

			// telling to wait for 20 seconds until the webelement located
			WebDriverWait var1 = new WebDriverWait(driver, Duration.ofSeconds(30));
			var1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//*[@id=\"container-01eabec55e\"]/div/section/section[2]/div/div[2]/div[1]/div[1]/select[2]")));

			WebDriverWait var2 = new WebDriverWait(driver, Duration.ofSeconds(30));
			var2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//*[@id=\"container-01eabec55e\"]/div/section/section[2]/div/div[2]/div[1]/div[1]/select[2]/option[2]")));
			// selecting the city from drop down
			Select city = new Select(Elements_f.statuscity);
			city.selectByVisibleText(City);

		}

		System.out.println("when done");

	}

	@Then("success")
	public void success() {
		System.out.println("Then done");
		driver.quit();
		
	}

}
