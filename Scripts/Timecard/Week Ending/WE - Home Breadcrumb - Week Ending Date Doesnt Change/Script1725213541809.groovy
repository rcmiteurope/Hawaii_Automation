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

WebElement weWeekEnding = driver.findElement(By.xpath('//*[@id="calendarWeekEnding"]'))
TestObject toWeekEnding = WebUI.convertWebElementToTestObject(weWeekEnding)

// grab the currently selected week-ending date
expectedWeekEnding = WebUI.getAttribute(toWeekEnding, "value")

// click the "home" button
driver.findElement(By.xpath('//*[@id="root"]/div/main/main/nav/ol/li/a')).click()

// grab the newly selected week-ending date
selectedWeekEnding = WebUI.getAttribute(toWeekEnding, "value")

// verify both dates are the same
WebUI.verifyMatch(selectedWeekEnding, expectedWeekEnding, false)

WebUI.closeBrowser()