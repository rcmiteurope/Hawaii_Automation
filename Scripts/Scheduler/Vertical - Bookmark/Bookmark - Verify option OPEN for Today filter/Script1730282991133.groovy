import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.Cookie
import java.text.SimpleDateFormat
import java.util.Date
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject

// Open browser and navigate to the page
WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', GlobalVariable.sc_auth_token)

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name))

WebUI.refresh()

// Check Horizontal Toggle
WebUI.check(new TestObject("dynamicObj").addProperty("xpath", ConditionType.EQUALS, "//*[@id='root']/main/div[2]/div[1]/div[1]/div/div"))
 

//WebUI.selectOptionByLabel(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="date_filter_select"]'),
	// 'Next Week', false)

TestObject bookmarkIcon = new TestObject()
bookmarkIcon.addProperty('xpath', ConditionType.EQUALS, '//*[@id=":r7:"]')
WebUI.click(bookmarkIcon)


TestObject buttonElement = new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id=":r6:"]/button[2]')

// Click the button
WebUI.click(buttonElement)

// Today's date for comparison (e.g., "11/19")
String todayDate = new SimpleDateFormat("MM/dd").format(new Date())

// Fetch all date values from the table column 3
List<WebElement> dateElements = WebUI.findWebElements(
    new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="vertical-table"]/tbody/tr/td[3]'),
    10
)

// Verify all dates match today's date (only month and day)
boolean allDatesMatch = dateElements.every { element ->
    def fullText = element.getText().trim() // Example: "Tue, 11/19 14:15 - 17:30"
    def datePart = fullText.split(", ")[1].split(" ")[0] // Extracts "11/19"
    WebUI.comment('Checking date part: "' + datePart + '" against today: "' + todayDate + '"')
    datePart == todayDate
}

if (allDatesMatch) {
    WebUI.comment('✅ All dates in column 3 match today\'s date: ' + todayDate)
} else {
    WebUI.comment('❌ Not all dates in column 3 match today\'s date: ' + todayDate)
    assert false : 'Some dates do not match today\'s date'
}

// Close the browser
WebUI.closeBrowser()
