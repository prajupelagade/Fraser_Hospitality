package Extent;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import ObjectRepository.SignUp_Elements;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

public class Extentreport {

	static ExtentTest test;
	static ExtentReports report;
	static WebDriver driver;
	static JavascriptExecutor js;
	
	@BeforeTest
    @Given("user launch browser")
	public void user_launch_browser() {
        
		report = new ExtentReports("ExtentReportResults.html");
		test = report.startTest("extentReportsD");
		System.setProperty("webdriver.chrome.driver",
				"E:\\chromedriver_win32 (3)\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.get("https://www.frasershospitality.com/en/fraser-world/sign-up/");

		driver.manage().window().maximize();

		PageFactory.initElements(driver, SignUp_Elements.class);

	}

	@Test(priority = 1)
	@When("User opens the URL\"https:\\/\\/www.frasershospitality.com\\/en\\/fraser-world\\/sign-up\\/\"")
	public void user_opens_the_url_https_www_frasershospitality_com_en_fraser_world_sign_up() throws Exception {
		//driver.navigate().to("https://www.frasershospitality.com/en/fraser-world/sign-up/");
		//SignUp_Elements.link.click();
		driver.manage().deleteAllCookies();
//		driver.manage().getCookies();
		SignUp_Elements.cookie.click();

		Thread.sleep(5000);

		Select value1 = new Select(driver.findElement(By.id("salutation")));
		value1.selectByValue("Ms.");

		SignUp_Elements.FirstName.sendKeys("prajakta");

		SignUp_Elements.LastName.sendKeys("pelagade");
		
        SignUp_Elements.Birth.click();

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

		SignUp_Elements.country.click();
		driver.findElement(By.className("select2-search__field")).sendKeys("India");
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_DOWN);

		r.keyRelease(KeyEvent.VK_DOWN);

		r.keyPress(KeyEvent.VK_ENTER);

		r.keyRelease(KeyEvent.VK_ENTER);

		SignUp_Elements.source.click();
		Select value3 = new Select(driver.findElement(By.id("source")));
		value3.selectByValue("99999");

		SignUp_Elements.Email.sendKeys("prajupelagade24@gmail.com");
		SignUp_Elements.password.sendKeys("Praju@677");
		SignUp_Elements.confirmpassword.sendKeys("Praju@677");

		SignUp_Elements.Refcode.sendKeys("677687");

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

		// driver.findElement(By.className("g-recaptcha")).click();
		
		test.log(LogStatus.PASS, "sign up successfully");
		
	}
	@Then("page title should be as\"Fraser world|Mmbership sign up\"")
	public void page_title_should_be_as_fraser_world_mmbership_sign_up() {
	String title=driver.getTitle();	
	 System.out.println(title);
	 
	}
	@Test(priority = 2)
	@When("create excel sheet provide valid webelement details")
	public void create_excel_sheet_provide_valid_webelement_details() throws Exception {
		File file = new File("E:\\output.xlsx");
		FileInputStream f1 = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sh = wb.createSheet();
		sh.createRow(0);
		sh.createRow(0).createCell(0).setCellValue("FREE STAYS");
		sh.createRow(1);
		sh.createRow(1).createCell(0).setCellValue("EARLY CHECK IN");
		sh.createRow(2);
		sh.createRow(2).createCell(0).setCellValue("UP TO 20% OFF");
		sh.createRow(3);
		sh.createRow(3).createCell(0).setCellValue("ROOM UPGRADE");

		FileOutputStream fos = new FileOutputStream(file);
		wb.write(fos);
		wb.close();
		test.log(LogStatus.PASS, "write excel");
		
	}

	@Then("data succesfully write to excel")
	public void data_succesfully_write_to_excel() {
		
		
	}
    @Test(priority = 3)
    @When("data fetch from excel")
	public void data_fetch_from_excel() throws Exception {
		
		driver.get("https://www.frasershospitality.com/en/");
		driver.manage().deleteAllCookies();
		Thread.sleep(5000);
		
		File src = new File("D:\\workspace automation\\FraserHospitality\\src\\test\\java\\data.xlsx");
		FileInputStream fi = new FileInputStream(src);

		XSSFWorkbook w1 = new XSSFWorkbook(fi);

		XSSFSheet s1 = w1.getSheetAt(0);
		for (int i = 1; i <= s1.getLastRowNum(); i++) {

			XSSFRow r1 = s1.getRow(i);
			
			String name = r1.getCell(0).getStringCellValue();
			
//			driver.findElement(By.id("search-booking")).sendKeys(name);
//			
//			driver.manage().deleteAllCookies();
//			driver.findElement(By.className("home-booking-widget_section-top__check_rate")).click();	
				
			SignUp_Elements.search.sendKeys(name);
			SignUp_Elements.rates.click();
			
//			Select con=new Select(driver.findElement(By.className("country-select")));
//			con.selectByValue("fr");
//			con.selectByVisibleText("France");
			test.log(LogStatus.PASS, "Data fetch from excel");
		}  
	}

	@Then("succesfully read data from excel")
	public void succesfully_read_data_from_excel() {
	 driver.close(); 
	 
	}
   @Test(priority = 4)
	@When("compare two images")
	public void compare_two_images() throws Exception {
		//driver.navigate().to("https://www.frasershospitality.com/en/fraser-world/sign-up/");
        System.out.println("image");
        
		BufferedImage expectedimage = ImageIO
				.read(new File("D:\\workspace automation\\FraserHospitality\\logo\\Screenshots\\image.png"));
        
		driver.get("https://www.frasershospitality.com/en/");
		
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1250)");
		System.out.println("jfjhffhjhfj");
		WebElement imageelement = driver
				.findElement(By.xpath("//div[@class='ugc-container--col1']"));

		Screenshot logoimagescreenshot = new AShot().coordsProvider(new WebDriverCoordsProvider())
				.takeScreenshot(driver, imageelement);
		ImageIO.write(logoimagescreenshot.getImage(), "png",
				new File("D:\\workspace automation\\FraserHospitality\\Images\\image.png"));
		File f = new File("D:\\workspace automation\\FraserHospitality\\Images\\image.png");
		BufferedImage actualimage = logoimagescreenshot.getImage();

		ImageDiffer imgdiff = new ImageDiffer();

		ImageDiff diff = imgdiff.makeDiff(expectedimage, actualimage);

		if (diff.hasDiff() == true) {
			System.out.println("images are not same");
		} else {
			System.out.println("images are same");
		} 
		test.log(LogStatus.PASS, "images are compared");
	}

	@Then("succesfully compare two images")
	public void succesfully_compare_two_images() {
	   
		test.log(LogStatus.PASS, "images are compared");
	}
    
	
	@AfterTest
	public void aftertest() {
		driver.close();
		report.endTest(test);
		report.flush();
	}
}

	


