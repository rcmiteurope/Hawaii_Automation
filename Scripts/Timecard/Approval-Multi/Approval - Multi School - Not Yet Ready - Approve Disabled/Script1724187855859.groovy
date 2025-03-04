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

	// click on the first school listed
	WebUI.findWebElement(findTestObject('Object Repository/HTA/a_FirstSchoolListed')).click()
	
	// grab the Not Yet Ready header
	WebElement notYetReadyHeader = WebUI.findWebElement(findTestObject('Object Repository/HTA/div_NotYetReady'))
	
	// grab all Not Yet Ready siblings
	List<WebElement> notYetReadySiblings = notYetReadyHeader.findElements(By.xpath("following-sibling::div"))

	// if there's nothing in Not Yet Ready...
	if (notYetReadySiblings.size() == 0) {
		
		// just click the header
		WebUI.findWebElement(findTestObject('Object Repository/HTA/h1_NotYetReady')).click()
		
	} else {	// continue with test
		
		// grab the Not Yet Ready body
		WebElement notYetReadyBody = notYetReadySiblings[0]

		// if the Not Yet Ready body is closed (class hidden), click to open it
		if (notYetReadyBody.getAttribute('class').contains('hidden')) {notYetReadyHeader.click()}

		// verify Approve button is NOT clickable
		WebUI.verifyElementNotClickable(findTestObject('Object Repository/HTA/button_Approve'))
		
	}

}
		
//WebUI.closeBrowser()
