import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

// set the screen size
WebUI.setViewPortSize(GlobalVariable.spreadsheetWidth, GlobalVariable.spreadsheetHeight)

// navigate to website  
WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

// click the select all checkbox
driver.findElement(By.xpath('//*[@id="select-all-toggle"]')).click()

// grab the action menu dropdown arrow; can't be directly clicked
WebElement weActionMenuHandle = driver.findElement(By.xpath('//*[@id="actionmenu-handle"]'))

// convert action menu dropdown element into a test object 
TestObject toActionMenuHandle = WebUI.convertWebElementToTestObject(weActionMenuHandle)

// click the action menu dropdown arrow to display the menu options
WebUI.click(toActionMenuHandle)

// grab the wrapper that holds the menu options
WebElement weActionMenuListWrapper = driver.findElement(By.xpath('//*[@id="actionmenu-list-wrapper"]'))

// grab a list of each action menu buttons
List<WebElement> weActionMenuButtons = weActionMenuListWrapper.findElements(By.xpath('child::button'))

// grab the list of expected menu button labels from global variables 
List<String> expectedActionMenuOptions = GlobalVariable.actionMenuOptions

// loop through every button
for (int i = 0; i < expectedActionMenuOptions.size(); i++) {
	
	String expectedText = expectedActionMenuOptions[i]
	
	if (i >= weActionMenuButtons.size()) {

		// raise an error
		KeywordUtil.markFailed('********* Expected active menu option ' + expectedText + ' is missing. ************')

	} else {
	
		// convert the button to a test object
		TestObject toActionMenuButton = WebUI.convertWebElementToTestObject(weActionMenuButtons[i])
		
		// grab the button's text value
		String buttonText = WebUI.getText(toActionMenuButton)
		
		try {
			// compare it to the global variable list - both the text and the sequence much match
			WebUI.verifyMatch(buttonText, expectedText, false, )

		} catch (com.kms.katalon.core.exception.StepFailedException e) {
	
			// raise an error if verify does not match
			KeywordUtil.markFailed('********* Expected search menu option ' + expectedText + ' is missing. ************')
		}

	}

}

WebUI.closeBrowser()

