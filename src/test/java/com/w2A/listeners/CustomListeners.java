package com.w2A.listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;
import com.w2A.base.TestBase;
import com.w2A.utilities.MonitoringMail;
import com.w2A.utilities.TestConfig;
import com.w2A.utilities.TestUtil;

public class CustomListeners extends TestBase implements ITestListener,ISuiteListener{

	public static String messageBody;
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub
		System.setProperty("org.uncommons.reportng.escape-output","false");
		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(LogStatus.FAIL,arg0.getName().toUpperCase()+" Failed with Exception"+arg0.getThrowable());
		test.log(LogStatus.FAIL,test.addScreenCapture(TestUtil.screenshotName));
		Reporter.log("Capturing Screenshot");
		Reporter.log("<a target=\"_blank\"href="+TestUtil.screenshotName+">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\"href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+" height=200 width=100></img></a>");
		report.endTest(test);
		report.flush();
	}

	public void onTestSkipped(ITestResult arg0) {
		
		test.log(LogStatus.SKIP,arg0.getName().toUpperCase()+" Skipped the test as runmode is set to NO");
		report.endTest(test);
		report.flush();
		
	}

	public void onTestStart(ITestResult arg0) {
		
     test=report.startTest(arg0.getName().toUpperCase());	
     //runmode-Y
     if(!TestUtil.isTestRunnable(arg0.getName(), excel))
     {
    	 throw new SkipException("Skipping the test "+ arg0.getName().toUpperCase()+"as the runmode is NO");
     }
     
	}

	public void onTestSuccess(ITestResult arg0) {

		test.log(LogStatus.PASS,arg0.getName().toUpperCase()+" PASS");
		report.endTest(test);
		report.flush();
	}

	public void onFinish(ISuite arg0) {

		MonitoringMail mail=new MonitoringMail();
		try {
			messageBody="http://"+InetAddress.getLocalHost().getHostAddress()+":8080/job/DataDrivenLiveProject/Extent_Report/";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mail.sendMail(TestConfig.server, TestConfig.from,TestConfig.to,TestConfig.subject, messageBody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onStart(ISuite arg0) {
		// TODO Auto-generated method stub
		
	}

}
