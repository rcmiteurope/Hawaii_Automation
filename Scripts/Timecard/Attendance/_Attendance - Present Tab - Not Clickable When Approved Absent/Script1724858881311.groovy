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
	
	// if there are students schedule this day
	if (numberOfStudents != 0) {
		
		// loop through all students
		for (int i = 0; i < numberOfStudents; i++) {
			
			// click each student on the home screen one-by-one
			driver.findElements(By.xpath('//div[@id="studentsListContainer"]/child::div'))[i].click()
			
			// if is approved *and* is absent
			if (CustomKeywords.'timecard.isApproved'(dayOfWeek) 
				&& CustomKeywords.'timecard.isAbsent'()) {
				
				// click the present button
				driver.findElement(By.xpath('//*[@id="presentBtn"]')).click()
				
				// verify still seeing absent screen
				WebUI.verifyEqual(CustomKeywords.'timecard.isAbsent'(), true)
				
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
