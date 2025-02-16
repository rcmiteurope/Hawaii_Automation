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
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import org.openqa.selenium.WebElement 
import org.openqa.selenium.By 
import java.util.Random
import org.openqa.selenium.Keys as Keys
import org.testng.Assert
import java.text.SimpleDateFormat
import java.util.Date

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url)
WebDriver driver = DriverFactory.getWebDriver()
Cookie authCookie = new Cookie('sc_auth_token', GlobalVariable.sc_auth_token)

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name))

WebUI.refresh()

WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id=":r7:"]'))

WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, "//button[contains(text(), 'All Holidays')]"))

// Define the XPath for the columns to verify
String columnXPath = "(//table[@id='horizontal-table'])[2]/tbody/tr/td[position() >= 2 and position() <= 6]"

List<WebElement> tableCells = WebUI.findWebElements(
	new TestObject().addProperty('xpath', ConditionType.EQUALS, columnXPath), 10
)

for (WebElement cell : tableCells) {
	String cellText = cell.getText().trim()

	if (cellText.isEmpty()) {
		WebUI.comment("Skipped empty cell")
		continue
	}

	String backgroundColor = cell.getCssValue("background-color")

	WebUI.comment("Cell text: " + cellText)
	WebUI.comment("Background color: " + backgroundColor)

	Assert.assertTrue(
		cellText.contains("Holiday") || backgroundColor.equals("rgba(0, 0, 0, 0)"),
		"Cell does not contain 'Holiday' or have a black background. " +
		"Actual text: " + cellText + ", Actual background: " + backgroundColor
	);
}

WebUI.closeBrowser()

