import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebDriver

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

WebUI.openBrowser('')
WebUI.setViewPortSize(GlobalVariable.mobileWidth,GlobalVariable.mobileHeight)

//WebUI.authenticate(GlobalVariable.timecard_url, 'hawaii', 'hawaiircm', 0)

WebUI.navigateToUrl(GlobalVariable.timecard_url)

// login with an invalid 6-digit PIN
CustomKeywords.'timecard.login'(GlobalVariable.invalid_pin_6digit)

// click OK button on error message
WebUI.findWebElement(findTestObject('Object Repository/HTA/button_OK_Popup')).click()

// attempt to login with valid PIN
CustomKeywords.'timecard.login'(GlobalVariable.multi_school_pin)

// verify successful login
WebUI.verifyTextPresent("Home", false)

WebUI.closeBrowser()
