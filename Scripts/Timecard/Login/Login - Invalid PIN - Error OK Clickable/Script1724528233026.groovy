import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebDriver

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable


WebUI.openBrowser('')
WebUI.setViewPortSize(GlobalVariable.mobileWidth,GlobalVariable.mobileHeight)

//WebUI.authenticate(GlobalVariable.timecard_url, 'hawaii', 'hawaiircm', 0)

WebUI.navigateToUrl(GlobalVariable.timecard_url)

CustomKeywords.'timecard.login'(GlobalVariable.invalid_pin_6digit)

//// verify error message - Incorrect PIN
//WebUI.verifyTextPresent("Incorrect PIN", false)

// verify OK button is clickable
WebUI.verifyElementClickable(findTestObject('Object Repository/HTA/button_OK_Popup'))

WebUI.closeBrowser()

