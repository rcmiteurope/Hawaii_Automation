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

// tuesday's schedule has two students (AB and KB)
WebUI.navigateToUrl(GlobalVariable.timecard_url + "/login?cd=" + GlobalVariable.wednesday)

CustomKeywords.'timecard.login'(GlobalVariable.multi_school_pin)

WebDriver driver = DriverFactory.getWebDriver()

List students = driver.findElements(By.xpath('//div[@id="studentsListContainer"]/child::div'))

// if there aren't exactly two students, throw an error
if (WebUI.verifyNotEqual(students.size(), 2, FailureHandling.OPTIONAL)) {
	
	// throw an error - For PIN xxxxxx on Wednesday, there needs to be exactly two students
	KeywordUtil.markFailed('For PIN ' + GlobalVariable.multi_school_pin + ' on Wednesday, there needs to be exactly two students')
	
} else {

	// list of valid initials
	validInitials = ['NJ', 'BJ']
	
	// loop through each student
	for (student in students) {
		
		// grab the student's initials
		initials = student.getText()
		
		// confirm the student's initials exist in initialsList
		if (!validInitials.contains(initials)) {
			
			// throw an error - For PIN xxxxxx, student SS is missing
			KeywordUtil.markFailed('For PIN ' + GlobalVariable.multi_school_pin + ' on Wednesday, student ' + initials + " is missing")
			
		} else {
			
			// remove the student's initials incase there are multiple students with the same initials (not really required because this is a controlled test)
			validInitials.remove(initials) 
			
		}
	}
	
	// make sure validInitials is blank (there's not reason in this controlled test that it wouldn't be)
	if (validInitials.size() > 0) {
		
		// throw an error - There should be no initials left in the names list
		KeywordUtil.markFailed('There should be no initials left in validInitials')
	}

}

WebUI.closeBrowser()