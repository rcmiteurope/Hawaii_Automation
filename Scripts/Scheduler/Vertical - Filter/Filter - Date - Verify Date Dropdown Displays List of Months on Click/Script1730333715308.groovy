import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale
import internal.GlobalVariable as GlobalVariable

import org.openqa.selenium.Cookie as Cookie
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', GlobalVariable.sc_auth_token)

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name))

WebUI.refresh()


// Click the dropdown using its ID
WebElement dropdown = driver.findElement(By.id('date_filter_select'))
dropdown.click()

// Wait for options to be visible (optional, adjust if necessary)
// WebUI.delay(1)

// Get the dropdown options (assuming they are <option> tags within a <select>)
List<WebElement> options = dropdown.findElements(By.tagName('option'))

// Get the current month and generate the expected months list
List<String> expectedMonths = []
LocalDate currentDate = LocalDate.now()

// Generate the expected months starting from the current month
for (int i = 0; i < 12; i++) {
    String monthName = currentDate.plusMonths(i).getMonth()
                        .getDisplayName(TextStyle.FULL, Locale.ENGLISH)
    expectedMonths.add(monthName)
}

// Collect the text values from the dropdown options
List<String> dropdownMonths = options.collect { it.getText().trim() }

// Log the dropdown options for debugging
dropdownMonths.each { month ->
    WebUI.comment("Month found in dropdown: " + month)
}

// Verify that the dropdown contains all the expected months starting from the current month
boolean allMonthsExist = true
List<String> missingMonths = []

// Check for each expected month
for (String expectedMonth : expectedMonths) {
    if (!dropdownMonths.contains(expectedMonth)) {
        allMonthsExist = false
        missingMonths.add(expectedMonth)
    }
}

// Log results
if (allMonthsExist) {
    WebUI.comment("Dropdown contains all the expected months starting from the current month.")
} else {
    WebUI.comment("Dropdown is missing the following months: " + missingMonths)
}

// Close the browser
WebUI.closeBrowser()
