package com.w2A.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.w2A.base.TestBase;

public class BankManagerLoginTest extends TestBase{
	
	@Test
	public void bankManagerLoginTest() throws Exception
	{
		log.debug("Inside LoginTest");
		click("bmlbtn_CSS");
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn_CSS"))),"Login not successfull");
		//Thread.sleep(3000);
		log.debug("LoginTest completed successfully");
		Reporter.log("Login completed successfully");
		
	}

}
