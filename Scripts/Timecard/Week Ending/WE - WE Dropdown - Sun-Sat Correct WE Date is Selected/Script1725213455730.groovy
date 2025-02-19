import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')
WebUI.setViewPortSize(GlobalVariable.mobileWidth,GlobalVariable.mobileHeight)

//WebUI.authenticate(GlobalVariable.timecard_url, 'hawaii', 'hawaiircm', 0)

WebDriver driver = DriverFactory.getWebDriver()


for (int dow=0; dow<=6; dow++) {
	
	String dayOfWeek = CustomKeywords.'timecard.dowToString'(dow)	// sunday .. saturday
	println dayOfWeek

	String dowFromGlobalVariable = CustomKeywords.'timecard.fromDOW'(dayOfWeek)
	println dowFromGlobalVariable

	WebUI.navigateToUrl((GlobalVariable.timecard_url + '/login?cd=') + dowFromGlobalVariable)

	CustomKeywords.'timecard.login'(GlobalVariable.single_school_pin)	

	Date bubbleDate = Date.parse('MMMMddyyyy', dowFromGlobalVariable)
	println bubbleDate.format('M/d/yyyy')
	
	WebElement weWeekEnding = driver.findElement(By.xpath('//*[@id="calendarWeekEnding"]'))
	TestObject toWeekEnding = WebUI.convertWebElementToTestObject(weWeekEnding)
	 
	// grab the currently displayed week-ending date
	displayedWeekEndingDate = WebUI.getAttribute(toWeekEnding, "value")
	
	// initialize variable
	String expectedWeekEndingDate
	 
	// calculate the expected week ending date
	if (dow == 6) {	
		
		// on satudary, the expected week ending date is the following saturday
		expectedWeekEndingDate = bubbleDate.plus(7).format("E MMM dd yyyy")
		
	} else {
		// on sunday - friday, the expected week ending date is the upcoming saturday
		expectedWeekEndingDate = bubbleDate.plus(6-dow).format("E MMM dd yyyy")	// it's 6 because monday=1	
	}
	
	println expectedWeekEndingDate
	// verify the expected week ending date matches the displayed week ending date
	WebUI.verifyMatch(expectedWeekEndingDate, displayedWeekEndingDate, false)

}
	 
WebUI.closeBrowser()
