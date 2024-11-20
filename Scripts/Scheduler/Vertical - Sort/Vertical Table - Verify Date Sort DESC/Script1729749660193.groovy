import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.WebElement
import org.openqa.selenium.Cookie
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Collections
import internal.GlobalVariable as GlobalVariable

// Open browser and navigate to the page
WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

// Add authentication cookie
Cookie authCookie = new Cookie('sc_auth_token', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6IkVyaWNhLkJvcnJvbWVvQHJjbXQuY29tIiwidXNlcklEIjo4LCJpYXQiOjE3MzE5ODYxMDEsImV4cCI6MTczNDU3ODEwMX0.AUWF2TrOJtXoWXnwJaA3MHQJ0iUgTpDUw2YrdjazB_Q')
driver.manage().addCookie(authCookie)
WebUI.refresh()

// Define sort button TestObject
TestObject sortButton = new TestObject().addProperty('id', ConditionType.EQUALS, 'vertical_date')

// Extract date and time from the table and parse
SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MM/dd HH:mm")
List<Date> parseDateTimeElements(List<WebElement> elements) {
    return elements.collect { element ->
        String fullText = element.getText().trim() // Example: "Tue, 11/19 14:15 - 17:30"
        try {
            String[] parts = fullText.split(", ")[1].split(" ")
            String datePart = parts[0]
            String timePart = parts[1]
            String combinedDateTime = datePart + " " + timePart
            dateTimeFormat.parse(combinedDateTime)
        } catch (Exception e) {
            WebUI.comment("Error parsing date/time: " + fullText)
            null // Return null for invalid rows
        }
    }.findAll { it != null } // Filter out nulls
}

// Fetch initial data
List<WebElement> dateTimeElements = WebUI.findWebElements(
    new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="vertical-table"]/tbody/tr/td[3]'),
    10
)
List<Date> parsedDateTimes = parseDateTimeElements(dateTimeElements)

// Create a sorted version for descending comparison
List<Date> sortedDesc = new ArrayList<>(parsedDateTimes)
sortedDesc.sort(Collections.reverseOrder())

// Log initial data
WebUI.comment('Extracted DateTimes: ' + parsedDateTimes.toString())
WebUI.comment('Expected Descending Order: ' + sortedDesc.toString())

// Keep clicking until sorted in descending order
int maxAttempts = 5
int attempts = 0
while (parsedDateTimes != sortedDesc && attempts < maxAttempts) {
    WebUI.comment("Sorting attempt: ${attempts + 1}")
    WebUI.click(sortButton)
    WebUI.delay(2)

    // Re-fetch and parse data
    dateTimeElements = WebUI.findWebElements(
        new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="vertical-table"]/tbody/tr/td[3]'),
        10
    )
    parsedDateTimes = parseDateTimeElements(dateTimeElements)

    WebUI.comment('Extracted DateTimes after sorting: ' + parsedDateTimes.toString())
    attempts++
}

// Verify the result
if (parsedDateTimes == sortedDesc) {
    WebUI.comment('✅ The table is correctly sorted in descending order.')
} else {
    WebUI.comment('❌ Failed to sort the table in descending order after ${maxAttempts} attempts.')
    assert false : 'The table could not be sorted in descending order.'
}

// Close the browser
WebUI.closeBrowser()
