import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

import org.openqa.selenium.Cookie as Cookie
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select
import com.kms.katalon.core.testobject.ConditionType

// Open browser and navigate to the URL
WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.scheduler_url)

// Add cookies for authentication
WebDriver driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', GlobalVariable.sc_auth_token)

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name))

WebUI.refresh()

// Define the dropdown using XPath
TestObject dropdownObject = new TestObject()
dropdownObject.addProperty('xpath', ConditionType.EQUALS, '//*[@id="filter-wrapper"]/select[5]')

// Click the dropdown to make sure it's focused
WebUI.click(dropdownObject)

// Get WebDriver and locate the dropdown as a WebElement
WebElement dropdownElement = WebUI.findWebElement(dropdownObject)

// Use Selenium's Select class to fetch all options
Select dropdownSelect = new Select(dropdownElement)
List<String> actualOptions = dropdownSelect.getOptions().collect { it.getText().trim() }

// Define the expected options
List<String> expectedOptions = [
    'Add Provider',
    'Add School',
    'Add Student',
    'Add Workorder',
    'Save Bookmark'
]

// Verify that the dropdown contains the expected options
if (actualOptions.containsAll(expectedOptions) && actualOptions.size() == expectedOptions.size()) {
    WebUI.comment('✅ Dropdown contains all the expected options.')
} else {
    WebUI.comment('❌ Dropdown options do not match the expected list.')
    WebUI.comment('Actual options: ' + actualOptions.toString())
    WebUI.comment('Expected options: ' + expectedOptions.toString())
}

// Close the browser
WebUI.closeBrowser()
