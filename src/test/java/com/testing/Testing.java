package com.testing;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Testing {
	WebDriver wd;
	
	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\hp\\eclipse-workspace-phase5\\chromedriver_win32\\chromedriver.exe");
		wd = new ChromeDriver();
	}
	
  @Test
  public void AutomateWebsite() {
	  
//	  :- Navigate to the Flipkart homepage (https://www.flipkart.com/) 
	  		wd.get("https://www.flipkart.com/");
	  		wd.manage().window().maximize();
	  		wd.manage().timeouts().implicitlyWait(Duration.ofMinutes(2));

//	  :- Determine a page load time with a performance test
	  		JavascriptExecutor js = (JavascriptExecutor) wd;
	  		Long loadtime = (Long)((JavascriptExecutor)wd).executeScript(
	  				"return performance.timing.loadEventEnd - performance.timing.navigationStart;");
	  		System.out.println("Load Time is :"+loadtime);
    
//    :- Search for a product, say, “iPhone 13” under the “Mobile” category 
	  		WebElement textFieldRef = wd.findElement(By.name("q"));
	  		textFieldRef.sendKeys("i phones");
	  		wd.findElement(By.name("q")).click();
	  		try {
	  			Thread.sleep(5000);
	  		}
	  		catch(Exception e) {
	  		}
//	  		wd.navigate().back();
	  
//	  :- Check if the images are loaded and visible till the screen height only 
	  	
	  		WebElement i = wd.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div[2]/div/div[1]/div[2]/div/a/div/img[1]"));
	  		Boolean p = (Boolean) ((JavascriptExecutor)wd)
	  				.executeScript("return arguments[0].complete "
	  				+ "&& typeof arguments[0].naturalWidth != \"undefined\" "
	  				+ "&& arguments[0].naturalWidth > 0", i);
	  		if (p) {
	  			System.out.println("Image loaded successfully");
	  		} else {
	         System.out.println("Image not loaded successfully");
	  		}
	  
//	  :- Check if the page has a scroll feature 
	  		js.executeScript("scrollBy(0, 2500)");
	  
	  
//	  :- Check the frequency at which the content will be refreshed while scrolling
	  		try {
	  			long lastHeight=((Number)js.executeScript("return document.body.scrollHeight")).longValue();
	  			while (true) {
	  				((JavascriptExecutor) wd).executeScript("window.scrollTo(0, document.body.scrollHeight);");
	  				Thread.sleep(2000);

	  				long newHeight = ((Number)js.executeScript("return document.body.scrollHeight")).longValue();
	  				if (newHeight == lastHeight) {
	  					break;
	  				}
	  				lastHeight = newHeight;
	  			}
	  		} catch (InterruptedException e) {
	  			e.printStackTrace();
	  		}
	  
	  
//	 :-  Verify that it navigates to the bottom of the page
	  		js.executeScript("window.scrollBy(0,1000)");
	

	

  }
  
  

  @AfterMethod
  public void afterMethod() {
	  wd.close();		
  }
}
