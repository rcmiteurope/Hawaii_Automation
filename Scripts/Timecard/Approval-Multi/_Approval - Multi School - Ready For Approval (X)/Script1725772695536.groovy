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

CustomKeywords.'timecard.login'(GlobalVariable.multi_school_pin)

WebDriver driver = DriverFactory.getWebDriver()

// click the nurses avatar
WebUI.click(findTestObject('Object Repository/HTA/img_NurseAvatarSmall'))

// click timecard approval
WebUI.click(findTestObject('Object Repository/HTA/a_TimecardApproval'))

// click the first school listed
WebUI.findWebElement(findTestObject('Object Repository/HTA/a_FirstSchoolListed')).click()

// grab the Ready For Approval header
WebElement readyForApprovalHeader = WebUI.findWebElement(findTestObject('Object Repository/HTA/div_ReadyForApproval'))

// grab all Ready For Approval siblings
List<WebElement> readyForApprovalSiblings = readyForApprovalHeader.findElements(By.xpath("following-sibling::div"))

// get the Ready For Approval count from the heading's first <h1>
readyForApprovalCount = readyForApprovalHeader.findElement(By.xpath('h1')).getText().find(/\d+/).toInteger()	// /\d+/ looks for the first number in the string

readyForApprovalPhysicalCount = 0

if (readyForApprovalCount == 0) {
	
	// throw the error
	KeywordUtil.markFailed('********* Can\'t perform this test because there are no students listed as Ready for Approval ************')

} else {
	
	// if the Ready for Approval body is closed (class hidden), click to open it
	if (readyForApprovalSiblings[0].getAttribute('class').contains('hidden')) {readyForApprovalHeader.click()}

	for (readyForApprovalSibling in readyForApprovalSiblings) {

		// grab all the <tr> rows in the body's table
		List<WebElement> rows = readyForApprovalSibling.findElements(By.xpath('descendant::tr'))
		
		// count <tr>s
		readyForApprovalPhysicalCount += rows.size()
	
	}
		
}

// verify number of <tr> rows equals the number from the tab's label
WebUI.verifyEqual(readyForApprovalCount.toInteger(), readyForApprovalPhysicalCount)

WebUI.closeBrowser()