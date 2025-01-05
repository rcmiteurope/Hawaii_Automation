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

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', GlobalVariable.sc_auth_token)

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name))

WebUI.refresh()

// Locate the dropdown using the XPath
WebElement dropdown = driver.findElement(By.id('provider_filter_select'))
dropdown.click()

// Wait for options to be visible (optional, adjust if necessary)
// WebUI.delay(1)

// Get the dropdown options (assuming they are <option> tags within a <select>)
List<WebElement> options = dropdown.findElements(By.tagName('option'))

// Define the expected options
List<String> expectedOptions = ['All Provider', 'OPEN Only', 'Holidays', 'Waiting On Orders', 'Student Absent'] 

// Collect the text values from the dropdown options
List<String> dropdownOptions = options.take(5).collect { it.getText().trim() }

// Log the dropdown options for debugging
dropdownOptions.each { option ->
    WebUI.comment("Option found in dropdown: " + option)
}

// Verify that all expected options are present
boolean allOptionsExist = true
List<String> missingOptions = []

for (String expectedOption : expectedOptions) {
    if (!dropdownOptions.contains(expectedOption)) {
        allOptionsExist = false
        missingOptions.add(expectedOption)
    }
}

// Log results
if (allOptionsExist) {
    WebUI.comment("Dropdown contains all the expected options.")
} else {
    WebUI.comment("Dropdown is missing the following options: " + missingOptions)
    throw new Exception("Test Failed: Dropdown is missing the following options: " + missingOptions)
}

// Close the browser
WebUI.closeBrowser()
