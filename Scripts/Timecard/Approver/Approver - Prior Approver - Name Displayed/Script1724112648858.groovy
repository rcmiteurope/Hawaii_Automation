import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebDriver

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')
WebUI.setViewPortSize(GlobalVariable.mobileWidth,GlobalVariable.mobileHeight)

//WebUI.authenticate(GlobalVariable.timecard_url, 'hawaii', 'hawaiircm', 0)

WebUI.navigateToUrl(GlobalVariable.timecard_url)

CustomKeywords.'timecard.login'(GlobalVariable.single_school_pin)

WebDriver driver = DriverFactory.getWebDriver()

// click the nurses avatar
WebUI.click(findTestObject('Object Repository/HTA/img_NurseAvatarSmall'))

// click timecard approval
WebUI.click(findTestObject('Object Repository/HTA/a_TimecardApproval'))

// get the Ready For Approval count from the tab
readyForApprovalCount = WebUI.findWebElement(findTestObject('Object Repository/HTA/h1_ReadyForApproval')).getText().find(/\d+/).toInteger()	// /\d+/ looks for the first number in the string

// get the Already Approved count from the tab
alreadyApprovedCount = WebUI.findWebElement(findTestObject('Object Repository/HTA/h1_AlreadyApproved')).getText().find(/\d+/).toInteger()	// /\d+/ looks for the first number in the string

// for Ready For Approval (#), if # equals zero, throw an error
if (WebUI.verifyEqual(readyForApprovalCount, 0, FailureHandling.OPTIONAL)) {
	
	KeywordUtil.markFailed('There must be something to approve to run this test')
	
// for Already Approved (#), if # equals zero, throw an error
} else if (WebUI.verifyEqual(alreadyApprovedCount, 0, FailureHandling.OPTIONAL)) {
	
	KeywordUtil.markFailed('There must be a prior approval to run this test')
	
} else {	// continue with test

	// if Approve button isn't clickable, open the Ready For Approval tab
	if (WebUI.verifyElementNotClickable(findTestObject('Object Repository/HTA/button_Approve'), FailureHandling.OPTIONAL)) {
				
		// click Ready For Approval 
		WebUI.click(findTestObject('Object Repository/HTA/div_ReadyForApproval'))
	}
	
	// click Approve
	WebUI.click(findTestObject('Object Repository/HTA/button_Approve'))
	
	// click Approver's Name
	WebUI.click(findTestObject('Object Repository/HTA/p_ApproversName'))
	
	// verify textPresent "Michael Rubin"
	WebUI.verifyTextPresent(GlobalVariable.approvers_name, false)
	
}
	
WebUI.closeBrowser()
