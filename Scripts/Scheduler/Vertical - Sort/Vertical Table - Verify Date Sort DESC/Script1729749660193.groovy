import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.Cookie as Cookie
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Collections
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

// Add authentication cookie
Cookie authCookie = new Cookie('sc_auth_token', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6IkVyaWNhLkJvcnJvbWVvQHJjbXQuY29tIiwidXNlcklEIjo4LCJpYXQiOjE3MzE5ODU2MjQsImV4cCI6MTczMjA3MjAyNH0.E-SXO2It4u5nW4Oq-XqoBIPSiFpWEIl2weNP6ETRoUo')
driver.manage().addCookie(authCookie)
WebUI.refresh()

// Fetch the list of date and time strings from the third column of the table
List<WebElement> dateTimeElements = WebUI.findWebElements(
    new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="vertical-table"]/tbody/tr/td[3]'), 
    10
)

// Correctly extract and parse the date and time
SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MM/dd HH:mm")
List<Date> parsedDateTimes = dateTimeElements.collect {
    String fullText = it.getText().trim() // Example: "Tue, 11/19 14:15 - 17:30"
    
    // Ensure correct extraction of date and time
    String[] parts = fullText.split(", ")[1].split(" ") // Split into date and time parts
    String datePart = parts[0] // Extract "11/19"
    String timePart = parts[1] // Extract "14:15"
    
    String combinedDateTime = datePart + " " + timePart // Combine "11/19 14:15"
    dateTimeFormat.parse(combinedDateTime) // Parse into Date object
}

// Create a sorted version for comparison (descending order)
List<Date> sortedDesc = new ArrayList<>(parsedDateTimes)
sortedDesc.sort(Collections.reverseOrder())

// Log the extracted and sorted dates/times for debugging
WebUI.comment('Extracted DateTimes: ' + parsedDateTimes.toString())
WebUI.comment('Expected Descending Order: ' + sortedDesc.toString())

// Check if the table is already sorted in descending order
if (parsedDateTimes == sortedDesc) {
    WebUI.comment('✅ The table is already sorted in descending order by date and time.')
} else {
    WebUI.comment('❌ The table is not sorted in descending order by date and time. Attempting to sort.')
	WebUI.delay(5)
	
    // Click the sort button to attempt sorting in descending order
    TestObject sortButton = new TestObject().addProperty('id', ConditionType.EQUALS, 'vertical_date')
    WebUI.click(sortButton)
    WebUI.delay(2)

    // Fetch the updated list of date and time strings after sorting
    dateTimeElements = WebUI.findWebElements(
        new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="vertical-table"]/tbody/tr/td[3]'), 
        10
    )

    // Parse the updated date and time
    parsedDateTimes = dateTimeElements.collect {
        String fullText = it.getText().trim()
        String[] parts = fullText.split(", ")[1].split(" ")
        String datePart = parts[0]
        String timePart = parts[1]
        String combinedDateTime = datePart + " " + timePart
        dateTimeFormat.parse(combinedDateTime)
    }

    // Verify the table is now sorted in descending order
    WebUI.comment('Extracted DateTimes after clicking sort: ' + parsedDateTimes.toString())
    assert parsedDateTimes == sortedDesc : '❌ The table is not sorted in descending order by date and time.'
    WebUI.comment('✅ The table is correctly sorted in descending order by date and time after clicking the sort button.')
}

// Close the browser
WebUI.closeBrowser()
