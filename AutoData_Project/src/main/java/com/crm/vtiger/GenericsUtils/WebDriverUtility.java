package com.crm.vtiger.GenericsUtils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.plaf.FileChooserUI;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * this class contains webdriver specific generic methods
 * 
 * 
 */
public class WebDriverUtility {
	/*
	 * this method wait for 20 sec for page loading
	 * @para driver
	 */
	public void waitUntilPageLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	/*
	 * this method wait for the element to be visible 
	 * @pram driver
	 * @param element
	 */
	public void waitForElementVisibility(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	/*
	 * This metod wait for the element to be clicked 
	 * @ param element
	 * @throws throwable
	 */
	public void waitAndClick(WebElement element) throws InterruptedException
	{
		int count =0;
		while(count<40)
		try{
			element.click();
			break;
		}
		catch(Throwable e)
		{
			Thread.sleep(5000);
			count++;
		}
	}
	/*
	 * This methods enables user to handle dropdown using visible text
	 *@param element 
	 * @param option
	 */
	public void SelectOption(WebElement element,String option)
	{
		Select select=new Select(element);
		select.selectByVisibleText(option);
	}
	/*
	 * This methods enables user to handle dropdown using visible index
	 *@param element 
	 * @param index
	 */
	public void SelectOption(WebElement element,int option)
	{
		Select select=new Select(element);
		select.selectByIndex(option);
	}
	/**
	* This method used for scrolling action in a webpage
	* @param driver
	* @param element
	*/
	public void scrollToWebElement(WebDriver driver, WebElement element) {
	JavascriptExecutor js=(JavascriptExecutor)driver;
	int y= element.getLocation().getY();
	js.executeScript("window.scrollBy(0,"+y+")", element);
	}
	/*
	 * this method will perform mouse over action
	 * @param driver
	 * @param element
	 */
	public void mouseOver(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	/*
	 * this method perform right click action
	 * @param driver
	 * @param element
	 */
	public void rightClick(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.contextClick(element).perform();
	}
	
	
	/*
	 * this method helps to switch from one window to another
	 * @param driver
	 * @param partialWinTitle
	 */
	public  void switchToWindow(WebDriver driver,String partialWinTitle)
	{
		Set<String> window = driver.getWindowHandles();
Iterator<String> it = window.iterator();
while(it.hasNext())
{
	String winID=it.next();
	String title=	driver.switchTo().window(winID).getTitle();
     if(title.contains(partialWinTitle))
     {
    	 break;
     }
}
	}
	/*
	 * Accept alert
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	/*
	 * cancel alert
	 * @param
	 */
	public void cancelAlert(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	/*
	 * This method is used for scrolling action in a webpage
	 * @param driver
	 * @param element
	 */
	public void scrollToElement(WebDriver driver,WebElement element)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		int y = element.getLocation().getY();
		js.executeScript("window.scrollBy(0,100)",element);
	}
	public void switchFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	public void switchFrame(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	public void switchFrame(WebDriver driver,String idOrName)
	{
		driver.switchTo().frame(idOrName);
	}
	public void takeScreenshot(WebDriver driver,String screenshotName)
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
	 File src=ts.getScreenshotAs(OutputType.FILE);
	 File dest=new File("./screenshot/"+screenshotName+".PNG");
	Files.copy(src, dest);
	}
	public void pressEnterkey(KeyEvent event) throws Throwable 
	{
		Robot rc=new Robot();
		rc.keyPress(KeyEvent.VK_ENTER);
		rc.keyRelease(KeyEvent.VK_ENTER);
		}
	
	
		
	
		
	
}
