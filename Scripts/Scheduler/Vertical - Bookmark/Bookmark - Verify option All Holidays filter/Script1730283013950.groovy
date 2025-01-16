import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.Cookie
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import internal.GlobalVariable as GlobalVariable

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
 

TestObject bookmarkIcon = new TestObject()

bookmarkIcon.addProperty('xpath', ConditionType.EQUALS, '//*[@id=":r7:"]')

WebUI.click(bookmarkIcon)

TestObject buttonElement = new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id=":r6:"]/button[4]')

// Click the button
WebUI.click(buttonElement)

// Fetch all cells in column 4
List<WebElement> statusElements = WebUI.findWebElements(
	new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="vertical-table"]/tbody/tr/td[4]'),
	10
)

// Log all background colors for debugging
WebUI.comment('Checking background colors of cells in column 4:')
boolean anyBlackBackground = statusElements.any { element ->
	def backgroundColor = element.getCssValue('background-color')
	WebUI.comment('Cell background color: ' + backgroundColor)
	backgroundColor == 'rgba(0, 0, 0, 1)' || backgroundColor == 'rgb(0, 0, 0)' // Match "black"
}

if (anyBlackBackground) {
	WebUI.comment('✅ At least one cell in column 4 has a black background.')
} else {
	WebUI.comment('⚠️ No cells in column 4 have a black background.')
}

// Close the browser
WebUI.closeBrowser()