package ObjectRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUp_Elements 
{
@FindBy(id="CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")
public static WebElement cookie;

@FindBy(linkText = "MEMBER SIGN UP")
public static WebElement link;

 @FindBy(id="salutation")	
 public static WebElement Salutation;
 
 @FindBy(id="firstname")	
 public static WebElement FirstName;
 
 @FindBy(id="lastname")	
 public static WebElement LastName;
 
 @FindBy(id="dob")	
 public static WebElement Birth;
 
 @FindBy(id="select2-residency-container")	
 public static WebElement country;
 
 @FindBy(id="source")	
 public static WebElement source;
 
 @FindBy(id="email")	
 public static WebElement Email;
 
 @FindBy(id="passwordd")	
 public static WebElement password;
 
 @FindBy(id="confirmpass")	
 public static WebElement confirmpassword;
 
 @FindBy(id="refCode")	
 public static WebElement Refcode;
 
@FindBy(id="marketingConsent")
public static WebElement term1;
 
 @FindBy(id="terms")
 public static WebElement term2;
 
 @FindBy(id="recaptcha-checkbox-checkmark")
 public static WebElement robot;
 
 @FindBy(linkText = "Home")
 public static WebElement home;
 
 @FindBy(linkText = "LOCATIONS")
 public static WebElement location;
 
 @FindBy(id = "search-booking")
 public static WebElement search;
 
 @FindBy(className="home-booking-widget_section-top__check_rate")
 public static WebElement rates;
 
 @FindBy(id="home_booking_calendar")
 public static WebElement calender;
 
 @FindBy(className ="home-booking-widget_section-top__pax_selector-input")
 public static WebElement adult;
 
// @FindBy(className="pax-details__row--content__item--plus add_btn")
// public static WebElement plus;
// 
// @FindBy(className="fa fa-plus")
// public static WebElement childplus;
// 
// @FindBy(id="pax-apply-btn")
// public static WebElement applybtn;
 
 @FindBy(id="CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")
 public static WebElement te;
}
