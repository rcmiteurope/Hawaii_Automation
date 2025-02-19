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

// actual dow doesn't matter  
WebUI.navigateToUrl((GlobalVariable.timecard_url + '/login?cd=') + GlobalVariable.monday)

CustomKeywords.'timecard.login'(GlobalVariable.single_school_pin)

WebDriver driver = DriverFactory.getWebDriver()

// click the nurses avatar
WebUI.click(findTestObject('Object Repository/HTA/img_NurseAvatarSmall'))

// click timecard approval
WebUI.click(findTestObject('Object Repository/HTA/a_TimecardApproval'))

// grab the Already Approved header
WebElement alreadyApprovedHeader = WebUI.findWebElement(findTestObject('Object Repository/HTA/div_AlreadyApproved'))

// grab all Already Approved siblings
List<WebElement> alreadyApprovedSiblings = alreadyApprovedHeader.findElements(By.xpath("following-sibling::div"))

// get the Already Approved count from the heading's first <h1>
alreadyApprovedCount = alreadyApprovedHeader.findElement(By.xpath('h1')).getText().find(/\d+/).toInteger()	// /\d+/ looks for the first number in the string

if (alreadyApprovedCount == 0) {
	
	// verify there's no body 
	WebUI.verifyEqual(alreadyApprovedSiblings.size(), 0)

} else {
	
	// grab the Already Approved body
	WebElement alreadyApprovedBody = alreadyApprovedSiblings[0]
	
	// if the Already Approved body is closed (class hidden), click to open it
	if (alreadyApprovedBody.getAttribute('class').contains('hidden')) {alreadyApprovedHeader.click()}
	
	// grab all the <tr> rows in the body's table
	List<WebElement> rows = alreadyApprovedBody.findElements(By.xpath('descendant::tr'))
	
	// verify number of <tr> rows equals the number from the tab's label
	WebUI.verifyEqual(alreadyApprovedCount.toInteger(), rows.size())

}

WebUI.closeBrowser()