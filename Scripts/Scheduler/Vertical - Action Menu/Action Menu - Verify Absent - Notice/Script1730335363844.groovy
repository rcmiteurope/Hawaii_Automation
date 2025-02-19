import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
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
import com.kms.katalon.core.testobject.TestObject
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Cookie
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import java.util.List
import java.util.Random

// Open browser and navigate to the URL
WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

// Add authentication cookies
Cookie authCookie = new Cookie('sc_auth_token', GlobalVariable.sc_auth_token)

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name))

WebUI.refresh()

// Check Horizontal Toggle
WebUI.check(new TestObject("dynamicObj").addProperty("xpath", ConditionType.EQUALS, "//*[@id='root']/main/div[2]/div[1]/div[1]/div/div"))
 

// Define XPath for all rows in the table
String rowsXPath = "//table[@id='vertical-table']//tbody/tr"

try {
	// Fetch all rows
	List<WebElement> rows = WebUI.findWebElements(new TestObject().addProperty("xpath", ConditionType.EQUALS, rowsXPath), 10)
	
	if (!rows.isEmpty()) {
		WebUI.comment("Total rows found: " + rows.size())
		
		// Randomly select a row
		Random random = new Random()
		int randomRowIndex = random.nextInt(rows.size()) + 1

		// Define XPath for the checkbox in td[4] and value in td[5]
		String checkboxXPath = "//table[@id='vertical-table']//tbody/tr[" + randomRowIndex + "]/td[4]//input[@type='checkbox']"
		String valueXPath = "//table[@id='vertical-table']//tbody/tr[" + randomRowIndex + "]/td[5]//div"

		TestObject checkbox = new TestObject().addProperty("xpath", ConditionType.EQUALS, checkboxXPath)
		TestObject valueCell = new TestObject().addProperty("xpath", ConditionType.EQUALS, valueXPath)

		// Check the checkbox in td[4]
		WebUI.check(checkbox)
		WebUI.comment("Checked the checkbox in row " + randomRowIndex)

		// Get the value from td[5]
		String cellValue = WebUI.getText(valueCell).trim()
		WebUI.comment("Initial value in row " + randomRowIndex + ", td[5]: " + cellValue)

		// Perform action using JavaScript
		WebUI.executeJavaScript('document.getElementById("actionmenu-handle").click();', null)
		WebUI.executeJavaScript('document.getElementById("absent-notice").click();', null)

		// Click "Save" button
		TestObject saveButton = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//button[@class='swal2-confirm swal2-styled' and text()='Save']")
		WebUI.click(saveButton)

		// Re-fetch the value from td[5] to validate the new value
		valueCell = new TestObject().addProperty("xpath", ConditionType.EQUALS, valueXPath)
		String newCellValue = WebUI.getText(valueCell).trim()
		WebUI.comment("New value in row " + randomRowIndex + ", td[5]: " + newCellValue)

		// Validate the new value
		if (newCellValue.contains("Student Absent - Notice")) {
    WebUI.comment("Validation passed: The value contains 'Student Absent- Notice'.")
} else {
    WebUI.comment("Validation failed: The value does not contain 'Student Absent - Notice'. Found: " + newCellValue)
}
	} else {
		WebUI.comment("No rows found in the table.")
	}
} catch (Exception e) {
	WebUI.comment("An error occurred: " + e.getMessage())
} finally {
	WebUI.closeBrowser()
}
