package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;



import PageObjects.AccountRegistrationPage;
import PageObjects.HomePage;
import testBase.BaseClass;

public class TC01_AccountRegistrationTest extends BaseClass {

	@Test(groups={"Sanity"})
	public void tc01_accRegister() {
		try {
		logger.info("*** Starting TC01_AccountRegistrationTest ***");
		HomePage hp=new HomePage(driver);
		hp.clickmyaccount();
		logger.info("Clicked on myaccount");
		hp.clickregister();
		logger.info("Clicked on Register");
		
		logger.info("Providing customer details");
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		regpage.setfirstname(randomString().toUpperCase());
		regpage.setlastname(randomString().toUpperCase());
		regpage.setemail(randomString()+"@gmail.com");
		regpage.settelephone(randomNumeric());
		String pass=randomAlphaNumeric();
		regpage.setpassword(pass);
		regpage.setconfirmpass(pass);
		regpage.checkagree();
		regpage.submit();
		
		logger.info("Capturing expected message..");
		String confmsg=regpage.getconfirmmsg();
		if(confmsg.equals("Your Account Has Been Created!")) {
			
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test Failed");
			logger.debug("Debug log");
			Assert.fail();
		}
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		
		catch(Exception e){
			
			Assert.fail();
		}
		logger.info("***Finished TC01_AccountRegistrationTest***");
	}
	
	
	
	
}

