import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Cookie as Cookie

WebUI.openBrowser('')
WebUI.setViewPortSize(GlobalVariable.spreadsheetWidth,GlobalVariable.spreadsheetHeight)

// navigate to website (any dow is fine)
WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()
 
Cookie authCookie = new Cookie('sc_auth_token', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6IkVyaWNhLkJvcnJvbWVvQHJjbXQuY29tIiwidXNlcklEIjo4LCJpYXQiOjE3MzE5ODYxMDEsImV4cCI6MTczNDU3ODEwMX0.AUWF2TrOJtXoWXnwJaA3MHQJ0iUgTpDUw2YrdjazB_Q')

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', 'Erica.Borromeo%40rcmt.com'))

driver.manage().addCookie(new Cookie('user_name', 'Borromeo%2C%20Erica'))
WebUI.refresh()


// grab the list of islands from global variables
List<String> islands = GlobalVariable.regionMenuOptions

// get list of locations
List<WebElement> weLocations = driver.findElements(By.xpath('//*[@id="locations"]/descendant::li'))

// compare list from global variables to list from website
for (weLocation in weLocations) {
	
	TestObject toLocation = WebUI.convertWebElementToTestObject(weLocation)
 
	WebUI.verifyElementClickable(toLocation, FailureHandling.CONTINUE_ON_FAILURE)
	
}

WebUI.closeBrowser()