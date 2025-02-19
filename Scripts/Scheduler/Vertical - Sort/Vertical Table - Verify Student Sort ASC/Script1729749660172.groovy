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

WebUI.navigateToUrl(GlobalVariable.scheduler_url // Replace with your URL
)

WebDriver driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', GlobalVariable.sc_auth_token)

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name))

WebUI.refresh()

// Fetch the list of student names from the table
List<WebElement> studentElements = WebUI.findWebElements(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="vertical-table"]/tbody/tr/td[2]'),
    10)

// Extract text from the elements and store it in a list
List<WebElement> studentNames = studentElements.collect({
    it.getText().trim()
})

// Create a sorted version for comparison (ascending order)
List<WebElement> sortedAsc = new ArrayList(studentNames)

sortedAsc.sort()

// Log the student names and the expected sorted order for debugging
WebUI.comment('Student Names: ' + studentNames.toString())

WebUI.comment('Expected Ascending Order: ' + sortedAsc.toString())

// Check if the table is already sorted in ascending order
if (studentNames == sortedAsc) {
    WebUI.comment('✅ The table is already sorted in ascending order.') // Click to sort the table in ascending order
    // Add a short delay to allow the sorting to complete
    // Extract the text again to check the new order
    // Verify the table is now sorted in ascending order
} else {
    WebUI.comment('❌ The table is not sorted in ascending order.')

	WebUI.click(findTestObject('Object Repository/Vertical Table/Page_Scheduler/span_Student'))

    WebUI.delay(4)

    studentElements = WebUI.findWebElements(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="vertical-table"]/tbody/tr/td[2]'),
        10)

    studentNames = studentElements.collect({
        it.getText().trim()
    })

    WebUI.comment('Student Names after clicking SVG: ' + studentNames.toString())

    assert studentNames == sortedAsc : '❌ The table is not sorted in ascending order.'

    WebUI.comment('✅ The table is correctly sorted in ascending order after clicking the SVG.')
}

// 11. Close the browser
WebUI.closeBrowser()
