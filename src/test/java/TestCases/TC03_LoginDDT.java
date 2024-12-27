package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.Dataproviders;

public class TC03_LoginDDT extends BaseClass {

@Test(dataProvider="LoginData", dataProviderClass=Dataproviders.class, groups="Master")
public void tc03_loginddt(String email,String password, String res) {
		
		logger.info("***Starting TC03_LoginDDT***");
		try
		{
		HomePage hp=new HomePage(driver);
		
		hp.clickmyaccount();
		hp.clicklogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setemail(email);
		lp.setpassword(password);
		lp.clicklogin();
		
		MyAccountPage mp=new MyAccountPage(driver);
		boolean targetpage=mp.verifylogin();
		
		//Data is valid=login success=test passed
		           //   =login failed=test failed
		
		//Data is invalid=login failed=test passed
		       //         =login passsed=test failed
		
		if(res.equalsIgnoreCase("Valid")) {
			if(targetpage==true) {
			mp.clicklogout();
			Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(false);
			}
			
		}
		
		if(res.equalsIgnoreCase("Invalid"))
		{
			if(targetpage==true) {
			mp.clicklogout();
			Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
			
		}
		}
		catch(Exception e) {
			Assert.fail();
		}
		
		
	}
}
