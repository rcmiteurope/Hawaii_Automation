import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')
WebUI.setViewPortSize(GlobalVariable.mobileWidth,GlobalVariable.mobileHeight)

//WebUI.authenticate(GlobalVariable.timecard_url, 'hawaii', 'hawaiircm', 0)

// navigate to website (any dow is fine)  
WebUI.navigateToUrl(GlobalVariable.timecard_url + "/login?cd=" + GlobalVariable.monday)

CustomKeywords.'timecard.login'(GlobalVariable.single_school_pin)

WebDriver driver = DriverFactory.getWebDriver()

for (int dow = 1; dow <= 5; dow++) {
	
	driver.findElement(By.xpath(('//*[@id="daysContainer"]/div[' + dow + ']/button'))).click()
	
	// get list of students
	int numberOfStudents = driver.findElements(By.xpath('//div[@id="studentsListContainer"]/child::div')).size()
	
	// loop through all students
	for (int i = 0; i < numberOfStudents; i++) {
		
		// grab each student on the home screen one-by-one
		WebElement student = driver.findElements(By.xpath('//div[@id="studentsListContainer"]/child::div'))[i]
	
		// grab the student's avatar filename from the home page
		String homeAvatar = student.findElement(By.xpath('child::img')).getAttribute("src")
		
		// click student to open the attendance page
		student.click()
		
		// grab the student's avatar filename from the attribute page
		String attendanceAvatar = WebUI.findWebElement(findTestObject('Object Repository/HTA/img_StudentAvatarMedium')).getAttribute("src")
		
		// verify the two filenames are the same
		WebUI.verifyMatch(homeAvatar, attendanceAvatar, false)
		
//		// click the home button before looping back
//		WebUI.click(findTestObject('Object Repository/HTA/a_Home'))
		
		// click back to return to Home
		WebUI.back()
		
	}
}

WebUI.closeBrowser()

