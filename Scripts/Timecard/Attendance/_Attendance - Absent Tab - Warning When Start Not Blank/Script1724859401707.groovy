import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
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
			driver.findElements(By.xpath('//div[@id="studentsListContainer"]/child::div'))[i].click()
			
			// skip if is approved
			if (CustomKeywords.'timecard.isNotApproved'(dayOfWeek)) {
				
				CustomKeywords.'timecard.setToPresent'()
				CustomKeywords.'timecard.setPresentTimes'("08:00","00:00")

				// click the absent button
				driver.findElement(By.xpath(('//*[@id="absentBtn"]'))).click()
				
				// verify warning messagebox displayed
				WebUI.verifyTextPresent('Are you sure?', false)
				
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
