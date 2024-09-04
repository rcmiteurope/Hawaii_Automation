import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

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

// grab the 'already approved' header
WebElement alreadyApprovedHeader = WebUI.findWebElement(findTestObject('Object Repository/HTA/div_AlreadyApproved'))

// get the number of approved timecards from the 'already approved' header <h1>
numberOfApprovedTimecards = alreadyApprovedHeader.findElement(By.xpath('h1')).getText().find('\\d+').toInteger() // /\d+/ looks for the first number in the string

if (numberOfApprovedTimecards == 0) {
    
	// throw an error - no approved testcards
    KeywordUtil.markFailed('At least one approved timecard is required for this test case - none found.') // grab the Already Approved Body

} else {
	
	// grab the 'already approved' body
    WebElement alreadyApprovedBody = alreadyApprovedHeader.findElements(By.xpath('following-sibling::div'))[0]

	// open the 'already approved' body if closed
    if (alreadyApprovedBody.getAttribute('class').contains('hidden')) {alreadyApprovedHeader.click()}
    
	// grab the first student's initials from the 'already approved' body
    studentInitials = alreadyApprovedBody.findElement(By.xpath('div/p')).getText()

    // grab the day of week from the 'already approved' body 
	dow = alreadyApprovedBody.findElement(By.xpath('table/tbody/tr/td/span')).getText()

	// relaunch the app passing the approved timecard's day of week
    WebUI.navigateToUrl((GlobalVariable.timecard_url + '/login?cd=') + CustomKeywords.'timecard.fromDOW'(dow))

	// login back into the app 
    CustomKeywords.'timecard.login'(GlobalVariable.single_school_pin)

	// click on the student's avatar using the approved timecard's student initials from earlier
    driver.findElement(By.xpath(('//div[@id="studentsListContainer"]/div/p[contains(text(), "' + studentInitials) + '")]')).click()

	// verify there's one sibling - that's the checkmark
	WebUI.verifyEqual(CustomKeywords.'timecard.isApproved'(dow), true)
		
}

WebUI.closeBrowser()

