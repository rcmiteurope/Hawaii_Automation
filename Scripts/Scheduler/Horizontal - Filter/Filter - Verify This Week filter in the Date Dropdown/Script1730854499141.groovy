import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import internal.GlobalVariable as GlobalVariable

import org.openqa.selenium.Cookie as Cookie
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import java.time.LocalDate
import java.time.DayOfWeek
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.model.FailureHandling

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', GlobalVariable.sc_auth_token)

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name))

WebUI.refresh()

//Click Lanai
WebUI.click(new TestObject('dynamic').addProperty('xpath', ConditionType.EQUALS, '//*[@id=\'tab-9\']'))


// Locate the dropdown using the XPath
WebElement dropdown = driver.findElement(By.id('date_filter_select'))
dropdown.click()

WebElement selectedOption = dropdown.findElement(By.xpath("//option[text()='This Week']"))
selectedOption.click()

// Calculate next week's Monday to Friday
LocalDate today = LocalDate.now()
LocalDate nextMonday = today.with(DayOfWeek.MONDAY)
List<String> expectedDates = (0..4).collect { nextMonday.plusDays(it).format(java.time.format.DateTimeFormatter.ofPattern("MM/dd")) }

// XPath for the date elements in the table headers
List<String> headerXpaths = [
    '//*[@id="col_2_date"]',
    '//*[@id="col_3_date"]',
    '//*[@id="col_4_date"]',
    '//*[@id="col_5_date"]',
    '//*[@id="col_6_date"]'
]

// Validate each date in the header
for (int i = 0; i < expectedDates.size(); i++) {
    TestObject dateElement = new TestObject().addProperty('xpath', ConditionType.EQUALS, headerXpaths[i])
    String actualDate = WebUI.getText(dateElement)
	
	// Print the expected and actual dates
	WebUI.comment("Expected Date: ${expectedDates[i]}" + "Actual Date: ${actualDate}")

    WebUI.verifyEqual(actualDate, expectedDates[i], FailureHandling.CONTINUE_ON_FAILURE)
}


// Close the browser
WebUI.closeBrowser()
