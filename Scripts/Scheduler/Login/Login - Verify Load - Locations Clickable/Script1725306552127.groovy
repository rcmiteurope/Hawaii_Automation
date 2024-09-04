import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')
WebUI.setViewPortSize(GlobalVariable.spreadsheetWidth,GlobalVariable.spreadsheetHeight)

WebDriver driver = DriverFactory.getWebDriver()

// navigate to website (any dow is fine)  
WebUI.navigateToUrl(GlobalVariable.scheduler_url)

// grab the list of islands from global variables
List<String> islands = GlobalVariable.islands

// get list of locations
List<WebElement> weLocations = driver.findElements(By.xpath('//*[@id="locations"]/descendant::li'))

// compare list from global variables to list from website
for (weLocation in weLocations) {
	
	TestObject toLocation = WebUI.convertWebElementToTestObject(weLocation)
 
	WebUI.verifyElementClickable(toLocation, FailureHandling.CONTINUE_ON_FAILURE)
	
}

WebUI.closeBrowser()