package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}

  @FindBy(xpath="//input[@id='input-firstname']")
  WebElement txtfirstname;

  @FindBy(xpath="//input[@id='input-lastname']")
  WebElement txtlastname;
  
  @FindBy(xpath="//input[@id='input-email']")
  WebElement email;
  
  @FindBy(xpath="//input[@id='input-telephone']")
  WebElement telephone;
  
  @FindBy(xpath="//input[@id='input-password']")
  WebElement password;
  
  @FindBy(xpath="//input[@id='input-confirm']")
  WebElement cnfrmpass;
  
  @FindBy(xpath="//input[@name='agree']")
  WebElement agreecheck;
  
  @FindBy(xpath="//input[@value='Continue']")
  WebElement contbtn;
  
  @FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
  WebElement msgConfirm;
  
  public void setfirstname(String fname) {
	  txtfirstname.sendKeys(fname);
  }
  
  public void setlastname(String lname) {
	  txtlastname.sendKeys(lname);
  }
  
  public void setemail(String mail) {
	  email.sendKeys(mail);
  }
  
  public void settelephone(String phone) {
	 telephone.sendKeys(phone);
  }
  
  public void setpassword(String pass) {
	  password.sendKeys(pass);
  }
  
  public void setconfirmpass(String pass) {
	  cnfrmpass.sendKeys(pass);
  }
  
  public void checkagree() {
	  agreecheck.click();
  }
  
  public void submit() {
	  contbtn.click();
  }
  
  public String getconfirmmsg() {
	  
	  try {
		  return (msgConfirm.getText());
	  }
	  catch(Exception e) {
		  return (e.getMessage());
	  }
	  
	  
  }
	}

