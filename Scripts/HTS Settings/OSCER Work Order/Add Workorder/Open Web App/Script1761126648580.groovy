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
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Keys
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import org.openqa.selenium.WebDriver as WebDriver
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Cookie as Cookie
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl(GlobalVariable.scheduler_url)
WebDriver driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', GlobalVariable.sc_auth_token)

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email_jirome))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name_jirome))
WebUI.delay(2)





TestObject dropdown = new TestObject('dynamic_otherActionsDropdown')
dropdown.addProperty('xpath', ConditionType.EQUALS, "//select[@id='other-actions-dropdown']")


WebUI.waitForElementVisible(dropdown, 15)
WebUI.waitForElementClickable(dropdown, 15)
WebUI.click(dropdown)
WebUI.delay(1)


int retries = 0
boolean selected = false

while (!selected && retries < 3) {
	try {
		// Recreate the dropdown each retry to avoid stale reference
		dropdown = new TestObject("dropdown_retry_${retries}")
		dropdown.addProperty('xpath', ConditionType.EQUALS, "//select[@id='other-actions-dropdown']")

		WebUI.waitForElementVisible(dropdown, 10)
		WebUI.waitForElementClickable(dropdown, 10)

		WebUI.selectOptionByValue(dropdown, 'workorders', false)
		println("✅ 'workorders' option successfully selected.")
		selected = true

	} catch (Exception e) {
		// Check if already selected before retry
		try {
			String currentValue = WebUI.getAttribute(dropdown, 'value')
			if (currentValue == 'workorders') {
				println("✅ 'workorders' is already selected. Skipping retry.")
				selected = true
				break
			}
		} catch (ignored) { }

		retries++
		println("⚠️ Attempt ${retries} failed: ${e.message}")
		WebUI.delay(2)
	}
}


