package com.w2A.testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.w2A.base.TestBase;
import com.w2A.utilities.TestUtil;

public class AddCustomerTest extends TestBase{
	
@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
public void addCustomerTest(Hashtable <String,String> data) throws Exception
{
    if(!data.get("runmode").equals("Y")) 
    {
    	throw new SkipException("Skipping the testcase as runmode  for the dataset is set to NO");
    }
	click("addCustBtn_CSS");
     type("firstname_CSS",data.get("firstname"));
     type("lastname_CSS",data.get("lastname"));
     type("postcode_CSS",data.get("postcode"));
     click("addBtn_CSS");
     Thread.sleep(2000);
     Alert alert=wait.until(ExpectedConditions.alertIsPresent());
     alert.accept();
     
     
}
}

