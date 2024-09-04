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

// navigate to website (any dow is fine)  
WebUI.navigateToUrl(GlobalVariable.timecard_url + "/login?cd=" + GlobalVariable.monday)

CustomKeywords.'timecard.login'(GlobalVariable.single_school_pin)

WebDriver driver = DriverFactory.getWebDriver()

Random random = new Random()

outerloop:
for (int dow = 1; dow <= 5; dow++) {
	
	driver.findElement(By.xpath(('//*[@id="daysContainer"]/div[' + dow + ']/button'))).click()
	
	// get list of students
	int numberOfStudents = driver.findElements(By.xpath('//div[@id="studentsListContainer"]/child::div')).size()
	
	// loop through all students
	for (int i = 0; i < numberOfStudents; i++) {
		
		// grab each student on the home screen one-by-one
		WebElement student = driver.findElements(By.xpath('//div[@id="studentsListContainer"]/child::div'))[i]
	
		// click student to open the attendance page
		student.click()
		
		// grab the student's avatar filename from the attribute page
		String attendanceAvatarFilename = WebUI.findWebElement(findTestObject('Object Repository/HTA/img_StudentAvatarMedium')).getAttribute("src")
		
		// click the edit avatar pencil
		WebUI.findWebElement(findTestObject('Object Repository/HTA/button_EditStudentAvatar')).click()

		// grab a list of all avatars in the avatar container
		List<WebElement> avatars = WebUI.findWebElement(findTestObject('Object Repository/HTA/div_StudentAvatarContainer')).findElements(By.xpath('descendant::img'))
		
		// loop until the avatar filenames are different
		while (true) { 	// workaround code until katalon support do/while

			// pick a random number
			 randomNumber = random.nextInt(avatars.size()-1)
			
			// grab the random avatar
			avatar = avatars[randomNumber]
	
			// grab the name of the new avatar
			String clickedAvatarFilename = avatar.getAttribute('src')
				
			if (clickedAvatarFilename != attendanceAvatarFilename) {

				// click the avatar
				avatar.click()

				// grab the name of the displayed avatar
				String displayedAvatarFilename = WebUI.findWebElement(findTestObject('Object Repository/HTA/img_StudentAvatarMedium')).getAttribute("src")
			
				// verify the clicked avatar is the one displayed on the attendance screen
				if (clickedAvatarFilename != displayedAvatarFilename) {
					
					// throw an error - Check that PIN xxxxxx has students from different schools
					KeywordUtil.markFailed('The new avaitar is not being displayed')
					
					// exit cleanly
					break outerloop
				}
				
			}
		}
		
		// click back to return to Home
		WebUI.back()

	}
}

WebUI.closeBrowser()

