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
import org.openqa.selenium.WebDriver
import org.openqa.selenium.Cookie
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.util.KeywordUtil

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', GlobalVariable.sc_auth_token)

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name))

WebUI.refresh()

//Click Kauai
WebUI.click(new TestObject('dynamic').addProperty('xpath', ConditionType.EQUALS, '//*[@id=\'tab-10\']'))

//Click Next week
WebUI.click(new TestObject('dynamic').addProperty('xpath', ConditionType.EQUALS, '//*[@id=\'horizontal_next_btn\']'))

//Click Cell
WebUI.click(new TestObject('dynamic').addProperty('xpath', ConditionType.EQUALS, "(//table[@id='horizontal-table'])[2]//tbody//tr[4]//td[6]//div"))

//Click action
WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="hori-absent-nncns"]'))

WebUI.click(new TestObject('dynamic').addProperty('xpath', ConditionType.EQUALS, '//*[@id=\'sched-dialog-save\']'))

//Cell Location
TestObject dynamicObject = new TestObject('dynamic').addProperty('xpath', ConditionType.EQUALS, "(//table[@id='horizontal-table'])[2]//tbody//tr[4]//td[6]//div")

// Get the text of the element
String elementText = WebUI.getText(dynamicObject)

// Check if the text matches the expected value
String expectedText = 'Student Absent - NCNS' // Replace with your expected text

if (elementText.contains(expectedText)) {
    println("Text matches: ${elementText}")
} else {
    KeywordUtil.markFailedAndStop("Text does not match. Expected: ${expectedText}, Found: ${elementText}")
}

WebUI.closeBrowser()

