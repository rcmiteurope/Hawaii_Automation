import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.Cookie

// Open browser and navigate to the page
WebUI.openBrowser('')
WebUI.navigateToUrl('https://scheduler-staging.rcmt-timecard.com/')
WebDriver driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6IkVyaWNhLkJvcnJvbWVvQHJjbXQuY29tIiwidXNlcklEIjo4LCJpYXQiOjE3MzE5ODYxMDEsImV4cCI6MTczNDU3ODEwMX0.AUWF2TrOJtXoWXnwJaA3MHQJ0iUgTpDUw2YrdjazB_Q')

driver.manage().addCookie(authCookie)
driver.manage().addCookie(new Cookie('user_email', 'Erica.Borromeo%40rcmt.com'))
driver.manage().addCookie(new Cookie('user_name', 'Borromeo%2C%20Erica'))

WebUI.refresh()

TestObject horizontalToggle = new TestObject()

horizontalToggle.addProperty('xpath', ConditionType.EQUALS, '//*[@id="root"]/main/div[2]/div/div/label/div')

WebUI.check(horizontalToggle)

TestObject bookmarkIcon = new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id=":r3:"]')
WebUI.click(bookmarkIcon)

TestObject buttonElement = new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id=":r2:"]/button[4]')
// Fetch all status values from the table column 4
List<WebElement> statusElements = WebUI.findWebElements(
    new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="vertical-table"]/tbody/tr/td[4]'),
    10
)

// Log all values for debugging
WebUI.comment('Retrieved values from column 4:')
statusElements.each { element ->
    WebUI.comment('Value: ' + element.getText().trim())
}

// Check if there are any "Holiday" values in column 4
boolean anyContainHoliday = statusElements.any { element ->
    def cellValue = element.getText().trim()
    WebUI.comment('Checking column 4 value: "' + cellValue + '" for "Holiday".')
    cellValue.contains('Holiday')
}

if (anyContainHoliday) {
    WebUI.comment('✅ At least one value in column 4 contains "Holiday".')
} else {
    WebUI.comment('⚠️ No values in column 4 contain "Holiday".')
}

// Close the browser
WebUI.closeBrowser()
