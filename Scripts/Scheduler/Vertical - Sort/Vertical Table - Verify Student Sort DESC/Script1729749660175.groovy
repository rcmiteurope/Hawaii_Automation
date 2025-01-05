import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.Cookie as Cookie
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver


// Open browser and navigate to the page
WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.scheduler_url) // Replace with your URL

WebDriver driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', GlobalVariable.sc_auth_token)

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name))

WebUI.refresh()

// Fetch the list of student names from the table
List<WebElement> studentElements = WebUI.findWebElements(
	new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="vertical-table"]/tbody/tr/td[2]'),
	10
)

// Extract text from the elements and store it in a list
List<String> studentNames = studentElements.collect { it.getText().trim() }

// Create a sorted version for comparison (descending order)
List<String> sortedDesc = new ArrayList<>(studentNames)
sortedDesc.sort(Collections.reverseOrder())

// Log the student names and the expected sorted order for debugging
WebUI.comment('Student Names: ' + studentNames.toString())
WebUI.comment('Expected Descending Order: ' + sortedDesc.toString())

// Check if the table is already sorted in descending order
if (studentNames == sortedDesc) {
	WebUI.comment('✅ The table is already sorted in descending order.')
} else {
	WebUI.comment('❌ The table is not sorted in descending order.')

	// Click the sort button to attempt sorting in descending order
	WebUI.click(findTestObject('Object Repository/Vertical Table/Page_Scheduler/span_Student'))
	WebUI.delay(1)

	// Fetch the updated list of student names after the first click
	studentElements = WebUI.findWebElements(
		new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="vertical-table"]/tbody/tr/td[2]'),
		10
	)
	studentNames = studentElements.collect { it.getText().trim() }

	// If still not sorted in descending order, click the sort button again
	if (studentNames != sortedDesc) {
		WebUI.comment('❌ The table is still not sorted in descending order. Clicking again to retry.')
		WebUI.click(findTestObject('Object Repository/Vertical Table/Page_Scheduler/span_Student'))
		WebUI.delay(1)

		// Fetch the updated list of student names after the second click
		studentElements = WebUI.findWebElements(
			new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="vertical-table"]/tbody/tr/td[2]'),
			10
		)
		studentNames = studentElements.collect { it.getText().trim() }
	}

	// Verify the table is now sorted in descending order
	WebUI.comment('Student Names after final click: ' + studentNames.toString())
	assert studentNames == sortedDesc : '❌ The table is not sorted in descending order.'
	WebUI.comment('✅ The table is correctly sorted in descending order.')
}

// Close the browser
WebUI.closeBrowser()