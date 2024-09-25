import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

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

// click the search menu dropdown arrow
driver.findElement(By.xpath('//*[@id=":r6:"]')).click()

// grab the wrapper that holds the search menu options; it's a <div>
WebElement weSearchMenuWrapper = driver.findElement(By.xpath('//*[@id=":r5:"]'))

// grab each search menu buttons
List<WebElement> weSearchMenuButtons = weSearchMenuWrapper.findElements(By.xpath('child::button'))

// grab the list of expected search menu options from global variables 
List<String> expectedSearchMenuOptions = GlobalVariable.searchMenuOptions

// loop through every button
for (int i = 0; i < expectedSearchMenuOptions.size(); i++) {
	
	String expectedText = expectedSearchMenuOptions[i]
		
	if (i >= weSearchMenuButtons.size()) {

		// raise an error
		KeywordUtil.markFailed('********* Expected search menu option ' + expectedText + ' is missing. ************')

	} else {
	
		// convert the search menu button to a test object
		TestObject toSearchMenuButton = WebUI.convertWebElementToTestObject(weSearchMenuButtons[i])
		
		// grab the search menu button's text value
		String buttonText = WebUI.getText(toSearchMenuButton)
		
		// compare the search menu button's text value to the expected search menu option - both the text and the sequence much match
		result = WebUI.verifyMatch(buttonText, expectedText, false)
		
		// raise an error if verify does not match
		if (result == false) { KeywordUtil.markFailed('********* Expected search menu option ' + expectedText + ' is missing. ************') }
	}
		
}

WebUI.closeBrowser()

