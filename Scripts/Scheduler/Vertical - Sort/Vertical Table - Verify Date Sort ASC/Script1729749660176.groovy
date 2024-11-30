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
import java.util.ArrayList
import internal.GlobalVariable as GlobalVariable

// Open browser and navigate to the page
WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6IkVyaWNhLkJvcnJvbWVvQHJjbXQuY29tIiwidXNlcklEIjo4LCJpYXQiOjE3MzE5ODYxMDEsImV4cCI6MTczNDU3ODEwMX0.AUWF2TrOJtXoWXnwJaA3MHQJ0iUgTpDUw2YrdjazB_Q')

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', 'Erica.Borromeo%40rcmt.com'))

driver.manage().addCookie(new Cookie('user_name', 'Borromeo%2C%20Erica'))

WebUI.refresh()

// Fetch the list of date and time strings from the table
List<WebElement> dateTimeElements = WebUI.findWebElements(
    new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="vertical-table"]/tbody/tr/td[3]'), 
    10
)

// Prepare to parse date and time
SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MM/dd HH:mm")
List<Date> parsedDateTimes = []

dateTimeElements.each { element ->
    String fullText = element.getText().trim() // Example: "Tue, 11/19 14:15 - 17:30"
    WebUI.comment("Full Text: " + fullText)
    try {
        String[] parts = fullText.split(", ")[1].split(" ") // Split into date and time parts
        String datePart = parts[0] // Extracts "11/19"
        String timePart = parts[1] // Extracts "14:15"
        String combinedDateTime = datePart + " " + timePart // Combines "11/19 14:15"
        WebUI.comment("Parsed DateTime: " + combinedDateTime)
        parsedDateTimes.add(dateTimeFormat.parse(combinedDateTime)) // Parse into Date object
    } catch (Exception e) {
        WebUI.comment("Error parsing date/time: " + fullText)
    }
}

// Create a sorted version for comparison (ascending order)
List<Date> sortedAsc = new ArrayList<>(parsedDateTimes)
sortedAsc.sort()

// Log the extracted and sorted dates/times for debugging
WebUI.comment('Extracted DateTimes: ' + parsedDateTimes.toString())
WebUI.comment('Expected Ascending Order: ' + sortedAsc.toString())

// Verify if the table is already sorted in ascending order
if (parsedDateTimes == sortedAsc) {
    WebUI.comment('✅ The table is already sorted in ascending order by date and time.')
} else {
    WebUI.comment('❌ The table is not sorted in ascending order by date and time. Attempting to sort.')

    // Click the sort button
    TestObject sortButton = new TestObject().addProperty('id', ConditionType.EQUALS, 'vertical_date')
    WebUI.click(sortButton)
    WebUI.delay(4)

    // Re-fetch and re-parse the table data
    dateTimeElements = WebUI.findWebElements(
        new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="vertical-table"]/tbody/tr/td[3]'), 
        10
    )

    parsedDateTimes = []
    dateTimeElements.each { element ->
        String fullText = element.getText().trim()
        try {
            String[] parts = fullText.split(", ")[1].split(" ")
            String datePart = parts[0]
            String timePart = parts[1]
            String combinedDateTime = datePart + " " + timePart
            parsedDateTimes.add(dateTimeFormat.parse(combinedDateTime))
        } catch (Exception e) {
            WebUI.comment("Error parsing date/time after sorting: " + fullText)
        }
    }

    // Verify if the table is now sorted in ascending order
    WebUI.comment('Extracted DateTimes after sorting: ' + parsedDateTimes.toString())
    assert parsedDateTimes == sortedAsc : '❌ The table is not sorted in ascending order by date and time.'
    WebUI.comment('✅ The table is correctly sorted in ascending order by date and time after clicking the sort button.')
}

// Close the browser
WebUI.closeBrowser()
