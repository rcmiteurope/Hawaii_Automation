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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import java.text.SimpleDateFormat
import java.util.Date
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType



WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.availability_app_url)

WebUI.setText(findTestObject('Object Repository/Availability-app-test/Page_/input_Username_login'), GlobalVariable.username)

WebUI.setEncryptedText(findTestObject('Object Repository/Availability-app-test/Page_/input_Password_password'), GlobalVariable.password)

WebUI.click(findTestObject('Object Repository/Availability-app-test/Page_/button_Sign In'))

WebUI.navigateToUrl(GlobalVariable.view_sched_url)


WebUI.waitForPageLoad(10)

// ✅ Verification 1: Check URL contains "/schedule"
String currentUrl = WebUI.getUrl()
WebUI.verifyMatch(currentUrl, GlobalVariable.view_sched_url, true)



// Format should match how the date appears in your app
Date today = new Date()
SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMMM d") // Example: Wednesday, August 20
String currentDate = sdf.format(today)

// Sometimes apps prefix with "Today, " → handle that too
String todayLabel = "Today, " + currentDate

// Create a dynamic XPath using the current date
TestObject dynamicDate = new TestObject("dynamicDate")
dynamicDate.addProperty("xpath", ConditionType.EQUALS, "//p[text()='" + todayLabel + "']")

WebUI.waitForPageLoad(10)

WebUI.click(dynamicDate) 

WebUI.click(findTestObject('Object Repository/Availability-app-test/opening-schedule/Page_/h1_Not Scheduled'))

WebUI.click(findTestObject('Object Repository/Availability-app-test/opening-schedule/Page_/button_View Openings'))


WebUI.delay(5)

// Close browser
WebUI.closeBrowser()