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

WebUI.openBrowser('')

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

WebUI.waitForPageLoad(10)

WebUI.click(findTestObject('Object Repository/Availability-app-test/opening-schedule/Page_/p_Today, Wednesday, August 20'))

WebUI.click(findTestObject('Object Repository/Availability-app-test/opening-schedule/Page_/h1_Not Scheduled'))

WebUI.click(findTestObject('Object Repository/Availability-app-test/opening-schedule/Page_/button_View Openings'))

WebUI.click(findTestObject('Object Repository/Availability-app-test/opening-schedule/Page_/h1_P370K I237'))

WebUI.click(findTestObject('Object Repository/Availability-app-test/opening-schedule/Page_/p_830 AM - 230 PM'))

WebUI.click(findTestObject('Object Repository/Availability-app-test/opening-schedule/Page_/p_Brooklyn'))

WebUI.delay(5)

// Close browser
WebUI.closeBrowser()