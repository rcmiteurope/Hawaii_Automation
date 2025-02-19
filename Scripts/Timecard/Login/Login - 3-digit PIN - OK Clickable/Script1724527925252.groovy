import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebDriver

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable


WebUI.openBrowser('')
WebUI.setViewPortSize(GlobalVariable.mobileWidth,GlobalVariable.mobileHeight)

//WebUI.authenticate(GlobalVariable.timecard_url, 'hawaii', 'hawaiircm', 0)

WebUI.navigateToUrl(GlobalVariable.timecard_url)

// attempt to login with a 3-digit PIN
CustomKeywords.'timecard.login'(GlobalVariable.invalid_pin_3digit)

// verify OK button is clickable
WebUI.verifyElementClickable(findTestObject('Object Repository/HTA/button_OK_Popup'))

WebUI.closeBrowser()

