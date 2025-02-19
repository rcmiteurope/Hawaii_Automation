import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')
WebUI.setViewPortSize(GlobalVariable.mobileWidth,GlobalVariable.mobileHeight)

//WebUI.authenticate(GlobalVariable.timecard_url, 'hawaii', 'hawaiircm', 0)

WebUI.navigateToUrl(GlobalVariable.timecard_url)

WebDriver driver = DriverFactory.getWebDriver()

// click 'Forgot PIN'
WebUI.click(findTestObject('Object Repository/HTA/a_Forgot PIN'))

// verify 'Forgot PIN' page is displayed
WebUI.verifyElementVisible(findTestObject('Object Repository/HTA/h1_ForgotPIN'))

// find the email address textbox 
WebElement email_address = WebUI.findWebElement(findTestObject('Object Repository/HTA/input_ForgotPinEmailAddress'))

// set email address textbox to 'unknown@rcmt.com' 
email_address.sendKeys('michael.rubin@rcmt.com')

// click 'Send Email'
WebUI.click(findTestObject('Object Repository/HTA/button_Send Email'))
	
// verify OK button is clickable
WebUI.verifyElementClickable(findTestObject('Object Repository/HTA/button_OK_Popup'))

WebUI.closeBrowser()