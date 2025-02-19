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

WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebUI.click(findTestObject('Object Repository/Horizontal Weekdays/Page_Scheduler/label_Not found_bg-white w-5 h-5 border bor_cc64be'))

WebUI.verifyElementText(findTestObject('Object Repository/Horizontal Weekdays/Page_Scheduler/p_Mon'), 'Mon')

WebUI.verifyElementText(findTestObject('Object Repository/Horizontal Weekdays/Page_Scheduler/p_Tue'), 'Tue')

WebUI.verifyElementText(findTestObject('Object Repository/Horizontal Weekdays/Page_Scheduler/p_Wed'), 'Wed')

WebUI.verifyElementText(findTestObject('Object Repository/Horizontal Weekdays/Page_Scheduler/p_Thu'), 'Thu')

WebUI.verifyElementText(findTestObject('Object Repository/Horizontal Weekdays/Page_Scheduler/p_Fri'), 'Fri')

WebUI.closeBrowser()

