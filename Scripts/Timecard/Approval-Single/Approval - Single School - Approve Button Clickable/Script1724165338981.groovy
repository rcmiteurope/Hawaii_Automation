import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
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

// grab the Ready For Approval header
WebElement readyForApprovalHeader = WebUI.findWebElement(findTestObject('Object Repository/HTA/div_ReadyForApproval'))

// grab the Ready For Approval body
WebElement readyForApprovalBody = readyForApprovalHeader.findElement(By.xpath("following-sibling::div"))

// if the Ready For Approval body is closed (class hidden), click to open it
if (readyForApprovalBody.getAttribute('class').contains('hidden')) {readyForApprovalHeader.click()}

// get the Ready For Approval count from the heading's first <h1>
readyForApprovalCount = readyForApprovalHeader.findElement(By.xpath("//h1")).getText().find(/\d+/).toInteger()	// /\d+/ looks for the first number in the string

// for Ready For Approval (#), if # equals zero, throw an error
if (WebUI.verifyEqual(readyForApprovalCount, 0, FailureHandling.OPTIONAL)) {
	
	KeywordUtil.markFailed('There must be something to approve to run this test')
	
} else {	// continue with test

	// grab all the <tr> rows in the body's table
	List<WebElement> rows = readyForApprovalBody.findElements(By.xpath('descendant::tr'))
	
	// verify number of <tr> rows equals the number from the tab's label
	WebUI.verifyEqual(readyForApprovalCount.toInteger(), rows.size())

}

WebUI.closeBrowser()


