import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.Cookie
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Cookie as Cookie
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', GlobalVariable.sc_auth_token)

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name))

WebUI.refresh()

// Check Horizontal Toggle
WebUI.check(new TestObject("dynamicObj").addProperty("xpath", ConditionType.EQUALS, "//*[@id='root']/main/div[2]/div[1]/div[1]/div/div"))
 

// click the select all checkbox
driver.findElement(By.xpath('//*[@id="select-all-toggle"]')).click()

// change date filter dropdown from Today to Tomorrow
// grab the date filter dropdown element
WebElement weDateFilterSelect = driver.findElement(By.xpath('//*[@id="date_filter_select"]'))
TestObject toDateFilterSelect = WebUI.convertWebElementToTestObject(weDateFilterSelect)
// select Tomorrow
WebUI.selectOptionByLabel(toDateFilterSelect, 'Tomorrow', false)


// check that the master action checkbox is cleared
// grab the master action checkbox
WebElement weMasterActionCheckbox = driver.findElement(By.xpath('//*[@id="select-all-toggle"]'))
// convert master action checkbox into a test object
TestObject toMasterActionCheckbox = WebUI.convertWebElementToTestObject(weMasterActionCheckbox)
// make sure the master action checkbox is clear
WebUI.verifyElementNotChecked(toMasterActionCheckbox, 0)




// check that all row checkboxes are cleared





//// loop through every button
//for (int i = 0; i < expectedActionMenuOptions.size(); i++) {
//	
//	String expectedText = expectedActionMenuOptions[i]
//	
//	if (i >= weActionMenuButtons.size()) {
//
//		// raise an error
//		KeywordUtil.markFailed('********* Expected active menu option ' + expectedText + ' is missing. ************')
//
//	} else {
//	
//		// convert the button to a test object
//		TestObject toActionMenuButton = WebUI.convertWebElementToTestObject(weActionMenuButtons[i])
//		
//		// grab the button's text value
//		String buttonText = WebUI.getText(toActionMenuButton)
//		
//		try {
//			// compare it to the global variable list - both the text and the sequence much match
//			WebUI.verifyMatch(buttonText, expectedText, false, )
//
//		} catch (com.kms.katalon.core.exception.StepFailedException e) {
//	
//			// raise an error if verify does not match
//			KeywordUtil.markFailed('********* Expected search menu option ' + expectedText + ' is missing. ************')
//		}
//
//	}
//
//}

WebUI.closeBrowser()

