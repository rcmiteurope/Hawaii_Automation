import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')
WebUI.setViewPortSize(GlobalVariable.spreadsheetWidth,GlobalVariable.spreadsheetHeight)

//***** WAITING FOR ADDITION OF MISSING ELEMENT ID: date_filter_select 

// navigate to website (any dow is fine)  
WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

WebElement weDateDropdown = driver.findElement(By.xpath('//*[@id=date_filter_select'))

TestObject toDateDropdown = WebUI.convertWebElementToTestObject(weDateDropdown)

WebUI.verifyElementClickable(toDateDropdown)

WebUI.closeBrowser()