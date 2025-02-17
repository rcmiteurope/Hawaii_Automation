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
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Cookie as Cookie
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import org.openqa.selenium.WebElement
import java.util.List

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url)

def driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', GlobalVariable.sc_auth_token)

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name))

WebUI.refresh()

// Check Horizontal Toggle
WebUI.check(new TestObject("dynamicObj").addProperty("xpath", ConditionType.EQUALS, "//*[@id='root']/main/div[2]/div[1]/div[1]/div/div"))

String rowsXPath = "//table[@id='vertical-table']//tbody/tr"

try {
	// Fetch all rows
	List<WebElement> rows = WebUI.findWebElements(new TestObject().addProperty("xpath", ConditionType.EQUALS, rowsXPath), 10)
	
	if (!rows.isEmpty()) {
		WebUI.comment("Total rows found: " + rows.size())
		
		// Loop through all rows and validate checkboxes in the 4th <td>
		for (int i = 1; i <= rows.size(); i++) {
			// Define XPath for checkbox in the 4th <td> of the current row
			String checkboxXPath = "//table[@id='vertical-table']//tbody/tr[" + i + "]/td[4]//input[@type='checkbox']"
			TestObject checkbox = new TestObject().addProperty("xpath", ConditionType.EQUALS, checkboxXPath)
			
			// Check if the checkbox is clickable
			if (WebUI.verifyElementClickable(checkbox, 5)) {
				WebUI.comment("Checkbox in row " + i + " is clickable.")
			} else {
				WebUI.comment("ERROR: Checkbox in row " + i + " is NOT clickable.")
			}
		}
	} else {
		WebUI.comment("No rows found in the table.")
	}
} catch (Exception e) {
	WebUI.comment("An error occurred: " + e.getMessage())
}
WebUI.closeBrowser();
