import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

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

// login as providers with students from different schools
CustomKeywords.'timecard.login'(GlobalVariable.multi_school_pin)

WebDriver driver = DriverFactory.getWebDriver()

// click the nurses avatar
WebUI.click(findTestObject('Object Repository/HTA/img_NurseAvatarSmall'))

// click timecard approval
WebUI.click(findTestObject('Object Repository/HTA/a_TimecardApproval'))

// if Select School is not displayed...
if (WebUI.verifyTextNotPresent("Select School", false, FailureHandling.OPTIONAL)) {
	
	// throw an error - Check that PIN xxxxxx has students from different schools
	KeywordUtil.markFailed('Check that PIN ' + GlobalVariable.multi_school_pin + ' has students from different schools')
	
} else {

	// grab the first school listed
	WebElement schoolNameElement = WebUI.findWebElement(findTestObject('Object Repository/HTA/a_FirstSchoolListed'))	// here
	
	// grab the name of the school
	schoolName = schoolNameElement.getText()
	
	// click on the school
	schoolNameElement.click()

	// make sure schoolName is on the page
	if (!WebUI.verifyTextPresent(schoolName, false, FailureHandling.OPTIONAL)) {
		// throw an error - Clicked on xxxxx but wrong approval page was displayed
		KeywordUtil.markFailed('Clicked on ' + schoolName + ' but wrong approval page was displayed.')
	}
	
	
}

WebUI.closeBrowser()