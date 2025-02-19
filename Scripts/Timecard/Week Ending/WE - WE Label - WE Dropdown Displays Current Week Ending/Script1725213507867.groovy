import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')
WebUI.setViewPortSize(GlobalVariable.mobileWidth,GlobalVariable.mobileHeight)

//WebUI.authenticate(GlobalVariable.timecard_url, 'hawaii', 'hawaiircm', 0)

// actual dow doesn't matter  
WebUI.navigateToUrl((GlobalVariable.timecard_url + '/login?cd=') + GlobalVariable.monday)

CustomKeywords.'timecard.login'(GlobalVariable.single_school_pin)

WebDriver driver = DriverFactory.getWebDriver()

// grab the week-ending dropdown
WebElement weWeekEnding = driver.findElement(By.xpath('//*[@id="calendarWeekEnding"]'))
TestObject toWeekEnding = WebUI.convertWebElementToTestObject(weWeekEnding)

// grab the week ending value from the dropdown
expectedWeekEnding = WebUI.getAttribute(toWeekEnding, "value")

// grab the 'week ending' label
driver.findElement(By.xpath('//*[@id="root"]/div/main/main/nav/ol/li/a')).click()

// calcuate the current week ending
date = new Date().clearTime()
Calendar calendar = Calendar.getInstance();
calendar.setTime(date);
day = calendar.get(Calendar.DAY_OF_WEEK);
currentWeekEnding = date.plus(7-day)

// verify both dates are the same 
WebUI.verifyMatch(currentWeekEnding, expectedWeekEnding, false)

WebUI.closeBrowser()