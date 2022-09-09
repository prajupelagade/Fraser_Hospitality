package Functionality;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import ObjectRepo.Elements_f;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LogoText_page;

public class Writedata {
	public WebDriver driver;
	public JavascriptExecutor js;

	@Given("Open application")
	public void Open_application() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.frasershospitality.com/en/fraser-world/");
		driver.manage().window().maximize();
		System.out.println("given done");
	}

	@When("Get the Image text and Write  Data in to Excel File")
	public void write_data_in_to_excel_success() throws IOException {
		PageFactory.initElements(driver, Elements_f.class);

		Elements_f.cookie.click();

		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1150)");
		System.out.println("start - write_data_in_to_excel_success");

		// highlight webelement
		WebElement hightext = Elements_f.hightext;

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].style.border='2px solid red'", hightext);

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet("ExcelData ");
		XSSFRow row;

		// presenceOfElementLocated condition

		int elementsize = driver.findElements(By.cssSelector("div[class='services__title']")).size();

		Map<String, Object[]> ExcelData = new TreeMap<String, Object[]>();

		for (int i = 1; i <= elementsize; i++) {
			WebElement logoval = Elements_f.logoval;
			ExcelData.put("SNO", new Object[] { "SNO", "LogoText" });
			ExcelData.put("key" + i, new Object[] { Integer.toString(i), logoval.getText() });
		}

		Set<String> keyid = ExcelData.keySet();
		int rowid = 0;

		// writing the data into the sheets...

		for (String key : keyid) {

			row = spreadsheet.createRow(rowid++);
			Object[] objectArr = ExcelData.get(key);
			int cellid = 0;

			for (Object obj : objectArr) {
				Cell cell = row.createCell(cellid++);
				cell.setCellValue((String) obj);
			}
		}

		FileOutputStream out = new FileOutputStream(
				new File(System.getProperty("user.dir") + "\\data\\writexample.xlsx"));
		workbook.write(out);
		out.close();

		System.out.println("after execution - write_data_in_to_excel_success");
	}

	@Then("Title should be \"Fraser World | Earn Hotel Rewards | Become a Member Today\"")
    public void get_the_logo_text() throws IOException {



       System.out.println("start - after get Title  and Page class call");



       // geturl title



       FileInputStream fi = new FileInputStream("src//test//java//Configuration//Config.properties");
        Properties prop = new Properties();
        prop.load(fi);
        String title = prop.getProperty("Title");



       String getTitleText = driver.getTitle();
        Assert.assertEquals(getTitleText, title);
        System.out.println("end - after get Title  and Page class call");
        driver.quit();

	}

   }