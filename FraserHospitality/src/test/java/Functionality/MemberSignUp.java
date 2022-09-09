package Functionality;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ObjectRepository.SignUp_Elements;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

public class MemberSignUp {
	public WebDriver driver;

	@BeforeTest
	public void launchbroswer() throws IOException, Exception {
		System.setProperty("webdriver.chrome.driver",
				"D:\\workspace automation\\SeleniumBasicAdvanceProject_Ass\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.frasershospitality.com/en/fraser-world/sign-up/");
		driver.manage().window().maximize();
		PageFactory.initElements(driver, SignUp_Elements.class);
	}

	@Test(priority = 1)
	public void signup() throws Exception {

		driver.manage().deleteAllCookies();
		SignUp_Elements.cookie.click();
		Thread.sleep(5000);
		SignUp_Elements.link.click();
		
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0,650)");

		Select value1 = new Select(driver.findElement(By.id("salutation")));
		value1.selectByValue("Ms.");

		SignUp_Elements.FirstName.sendKeys("prajakta");
		SignUp_Elements.LastName.sendKeys("pelagade");

//		SignUp_Elements.Birth.click();
//		Thread.sleep(5000);
//		JavascriptExecutor js2 = (JavascriptExecutor) driver;
//		js2.executeScript("document.getElementById('dob').value='24-Aug-1997'");
//
//		Thread.sleep(5000);
//		SignUp_Elements.country.click();
//		driver.findElement(By.className("select2-search__field")).sendKeys("India");
		Thread.sleep(5000);
		SignUp_Elements.source.click();
		Select value3 = new Select(driver.findElement(By.id("source")));
		value3.selectByValue("99999");

		SignUp_Elements.Email.sendKeys("prajupelagade24@gmail.com");
		SignUp_Elements.password.sendKeys("Praju@677");
		SignUp_Elements.confirmpassword.sendKeys("Praju@677");
		SignUp_Elements.Refcode.sendKeys("677687");
		// SignUp_Elements.term.click();
		WebElement term = driver.findElement(By.id("marketingConsent"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", term);

		WebElement term1 = driver.findElement(By.id("terms"));
		JavascriptExecutor js3 = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", term1);

//		Thread.sleep(5000);
//		js1.executeScript("window.scrollBy(0,250)");
//		WebElement robot=driver.findElement(By.xpath("/html/body/div[4]/div/div[6]/div/div[4]/div/section/div/div[1]/div[2]/form/div[5]/div[3]/div"));
//		JavascriptExecutor js4 = (JavascriptExecutor) driver;
//		Thread.sleep(5000);
//		js.executeScript("arguments[0].click();", robot);

	}

	@Test(priority = 2)
	public void writedata() throws Exception {
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

		JavascriptExecutor js5 = (JavascriptExecutor) driver;
		js5.executeScript("window.scrollBy(0,1150)");
		System.out.println("print");

	}

	@Test(priority = 3)
	public void readdata() throws Exception {
		driver.get("https://www.frasershospitality.com/en/");

		File src = new File("D:\\workspace automation\\FraserHospitality\\src\\test\\java\\data.xlsx");
		FileInputStream fi = new FileInputStream(src);

		XSSFWorkbook w1 = new XSSFWorkbook(fi);

		XSSFSheet s1 = w1.getSheetAt(0);
		for (int i = 1; i <= s1.getLastRowNum(); i++) {

			XSSFRow r1 = s1.getRow(i);
			String name = r1.getCell(0).getStringCellValue();
			SignUp_Elements.search.sendKeys(name);
			SignUp_Elements.rates.click();
		}
	}

	/*
	 * SignUp_Elements.calender.sendKeys("Tue,Oct 18-Wed,Oct 19");
	 * SignUp_Elements.adult.click(); SignUp_Elements.plus.click();
	 * SignUp_Elements.childplus.click(); SignUp_Elements.applybtn.click();
	 * 
	 * Get WebElement reference of logo
	 */
	@Test(priority = 4 )
	public void screenshot() throws IOException {
		driver.navigate().to("https://www.frasershospitality.com/en/fraser-world/sign-up/");

		BufferedImage expectedimage = ImageIO
				.read(new File("D:\\workspace automation\\FraserHospitality\\logo\\Screenshots\\image.png"));

		WebElement imageelement = driver
				.findElement(By.xpath("//div[@class='aem-Grid aem-Grid--12 aem-Grid--default--12 ']"));

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
	}

	@AfterTest
	public void driverclose() {
		driver.close();
	}
}
