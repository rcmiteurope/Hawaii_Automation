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
WebUI.navigateToUrl(GlobalVariable.timecard_url + "/login?cd=" + GlobalVariable.monday)

CustomKeywords.'timecard.login'(GlobalVariable.single_school_pin)

WebDriver driver = DriverFactory.getWebDriver()

def boolean found = false

outerloop:
// loop through every day of the week; we'll be looking for student whose time can be edited (time not already approved)
for (int dow = 1; dow <= 5; dow++) {
	
	def dayOfWeek = CustomKeywords.'timecard.dowToString'(dow)
	
	// click to open the dow's home page
	driver.findElement(By.xpath(('//*[@id="daysContainer"]/div[' + dow + ']/button'))).click()
	
	// get list of students
	int numberOfStudents = driver.findElements(By.xpath('//div[@id="studentsListContainer"]/child::div')).size()
	
	// go back to the home page so we can navigate to the next dow
	if (numberOfStudents != 0) {
		
		// loop through all students
		for (int i = 0; i < numberOfStudents; i++) {
			
			// click each student on the home screen one-by-one
			WebElement student = driver.findElements(By.xpath('//div[@id="studentsListContainer"]/child::div'))[i].click()
		
			// if is *not* approved 
			if (CustomKeywords.'timecard.isNotApproved'(dayOfWeek)) {
				
				CustomKeywords.'timecard.setToPresent'()
				CustomKeywords.'timecard.setPresentTimes'("00:00","00:00")
				
				// grab the start time label
				WebElement startTimeLabel = WebUI.findWebElement(findTestObject('Object Repository/HTA/p_StartTime_A'))
				
				// grab the start time label text
				startTime = startTimeLabel.getText()
				
				// click the start time label
				startTimeLabel.click()
				
				// verify start time select matches start time label
				WebUI.verifyOptionSelectedByLabel(findTestObject('Object Repository/HTA/select_StartTime'),startTime, false, 1)
				
				// found testable student
				found = true
				
				// break out of the loop and end the test
				break outerloop
			
			} 
			
			WebUI.back()
		
		}

	}
	
}

// if no student found to test, throw an error 
if (!found) {KeywordUtil.markFailed('*********** Unable to complete test - no testable students found **************')}

WebUI.closeBrowser()