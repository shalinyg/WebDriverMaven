package com.w2A.testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.w2A.base.TestBase;
import com.w2A.utilities.TestUtil;

public class OpenAccountTest extends TestBase{
	
	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	public void openAccountTest(Hashtable <String,String> data) throws Exception
	{
		click("openAccount_CSS");
		select("customer_XPATH",data.get("customer"));
		select("currency_CSS",data.get("currency"));
		click("process_CSS");
	  //Thread.sleep(2000);
	    Alert alert=wait.until(ExpectedConditions.alertIsPresent());
	    alert.accept();
	}
	
}
