import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')
WebUI.setViewPortSize(GlobalVariable.spreadsheetWidth,GlobalVariable.spreadsheetHeight)

// navigate to website (any dow is fine)  
WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

// click the select all checkbox
driver.findElement(By.xpath('//*[@id="select-all-toggle"]')).click()

// grab the action menu dropdown arrow; can't be directly clicked
WebElement weActionMenuHandle = driver.findElement(By.xpath('//*[@id="actionmenu-handle"]'))

// convert action menu dropdown element into a test object 
TestObject toActionMenuHandle = WebUI.convertWebElementToTestObject(weActionMenuHandle)

// make sure the action menu dropdown is enabled when the "select all" checkbox is set
WebUI.verifyElementClickable(toActionMenuHandle)

WebUI.closeBrowser()