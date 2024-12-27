package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC02_LoginTest extends BaseClass{

	@Test(groups= "Regression")
	public void tc01_logintest() {
		
		logger.info("***Starting TC02_Logintest***");
		try
		{
		HomePage hp=new HomePage(driver);
		
		hp.clickmyaccount();
		hp.clicklogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setemail(p.getProperty("email"));
		lp.setpassword(p.getProperty("password"));
		lp.clicklogin();
		
		MyAccountPage mp=new MyAccountPage(driver);
		boolean targetpage=mp.verifylogin();
		
		Assert.assertTrue(targetpage);
		
		}
		catch(Exception e) {
			Assert.fail();
		}
		
		
	}
}
