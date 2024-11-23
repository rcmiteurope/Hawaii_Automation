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

Cookie authCookie = new Cookie('sc_auth_token', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6IkVyaWNhLkJvcnJvbWVvQHJjbXQuY29tIiwidXNlcklEIjo4LCJpYXQiOjE3MzE5ODYxMDEsImV4cCI6MTczNDU3ODEwMX0.AUWF2TrOJtXoWXnwJaA3MHQJ0iUgTpDUw2YrdjazB_Q')

driver.manage().addCookie(authCookie)
driver.manage().addCookie(new Cookie('user_email', 'Erica.Borromeo%40rcmt.com'))
driver.manage().addCookie(new Cookie('user_name', 'Borromeo%2C%20Erica'))

WebUI.refresh()

TestObject horizontalToggle = new TestObject()

horizontalToggle.addProperty('xpath', ConditionType.EQUALS, '//*[@id="root"]/main/div[2]/div/div/label/div')

WebUI.check(horizontalToggle)

TestObject bookmarkIcon = new TestObject()
bookmarkIcon.addProperty('xpath', ConditionType.EQUALS, '//*[@id=":r7:"]')
WebUI.click(bookmarkIcon)

TestObject buttonElement = new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id=":r6:"]/button[3]')

// Click the button
WebUI.click(buttonElement)

// Fetch all status values from the table column 4
List<WebElement> statusElements = WebUI.findWebElements(
    new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="vertical-table"]/tbody/tr/td[4]'),
    10
)

// Verify all statuses are "OPEN"
boolean allStatusesOpen = statusElements.every { element ->
    def statusText = element.getText().trim()
    WebUI.comment('Checking status: "' + statusText + '"')
    statusText == 'OPEN'
}

if (allStatusesOpen) {
    WebUI.comment('✅ All statuses in column 4 are "OPEN".')
} else {
    WebUI.comment('❌ Not all statuses in column 4 are "OPEN".')
    assert false : 'Some statuses are not "OPEN"'
}

// Close the browser
WebUI.closeBrowser()
