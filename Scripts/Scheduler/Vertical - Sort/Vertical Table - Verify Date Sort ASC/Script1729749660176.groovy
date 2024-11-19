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
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url) 

WebDriver driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6IkVyaWNhLkJvcnJvbWVvQHJjbXQuY29tIiwidXNlcklEIjo4LCJpYXQiOjE3MzE5ODU2MjQsImV4cCI6MTczMjA3MjAyNH0.E-SXO2It4u5nW4Oq-XqoBIPSiFpWEIl2weNP6ETRoUo')

driver.manage().addCookie(authCookie)

WebUI.refresh()

List<WebElement> dateTimeElements = WebUI.findWebElements(
    new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="vertical-table"]/tbody/tr/td[3]'), 
    10
)

SimpleDateFormat dateTimeFormat = new SimpleDateFormat("MM/dd HH:mm")
List<Date> parsedDateTimes = dateTimeElements.collect {
    String fullText = it.getText().trim() // Example: "Tue, 11/19 14:15 - 17:30"
    String datePart = fullText.split(", ")[1].split(" ")[0] // Extracts "11/19"
    String timePart = fullText.split(" ")[1] // Extracts "14:15"
    String combinedDateTime = datePart + " " + timePart // Combines to "11/19 14:15"
    dateTimeFormat.parse(combinedDateTime) // Parses into Date object
}

// Create a sorted version for comparison (ascending order)
List<Date> sortedAsc = new ArrayList<>(parsedDateTimes)
sortedAsc.sort()

// Log the extracted and sorted dates/times for debugging
WebUI.comment('Extracted DateTimes: ' + parsedDateTimes.toString())
WebUI.comment('Expected Ascending Order: ' + sortedAsc.toString())

if (parsedDateTimes == sortedAsc) {
    WebUI.comment('✅ The table is already sorted in ascending order by date and time.')
} else {
    WebUI.comment('❌ The table is not sorted in ascending order by date and time.')

	TestObject sortButton = new TestObject().addProperty('id', ConditionType.EQUALS, 'vertical_date')
	
	WebUI.click(sortButton)
	
    WebUI.delay(4)

    dateTimeElements = WebUI.findWebElements(
        new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="vertical-table"]/tbody/tr/td[3]'), 
        10
    )

    parsedDateTimes = dateTimeElements.collect {
        String fullText = it.getText().trim()
        String datePart = fullText.split(", ")[1].split(" ")[0]
        String timePart = fullText.split(" ")[1]
        String combinedDateTime = datePart + " " + timePart
        dateTimeFormat.parse(combinedDateTime)
    }

    WebUI.comment('Extracted DateTimes after clicking sort: ' + parsedDateTimes.toString())
    assert parsedDateTimes == sortedAsc : 'The table is not sorted in ascending order by date and time.'
    WebUI.comment('✅ The table is correctly sorted in ascending order by date and time after clicking the sort button.')
}

WebUI.closeBrowser()
