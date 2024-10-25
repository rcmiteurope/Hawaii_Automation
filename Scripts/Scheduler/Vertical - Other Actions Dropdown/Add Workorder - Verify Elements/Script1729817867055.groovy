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

WebUI.navigateToUrl('https://scheduler-qa.rcmt-timecard.com/')

WebUI.selectOptionByValue(findTestObject('Object Repository/Add Workorder/Page_Scheduler/select_Other ActionsAdd SchoolAdd StudentAd_8e5993'), 
    'add-workorder', true)

WebUI.verifyElementPresent(findTestObject('Object Repository/Add Workorder/Page_Scheduler/input_Search_react-select-3-input'), 
    0)

WebUI.verifyElementPresent(findTestObject('Object Repository/Add Workorder/Page_Scheduler/input_Search_react-select-3-input'), 
    0)

WebUI.verifyElementPresent(findTestObject('Object Repository/Add Workorder/Page_Scheduler/input_Date Range_datepicker'), 
    0)

WebUI.verifyElementPresent(findTestObject('Object Repository/Add Workorder/Page_Scheduler/button_Submit'), 0)

WebUI.verifyElementPresent(findTestObject('Object Repository/Add Workorder/Page_Scheduler/button_Cancel'), 0)

WebUI.closeBrowser()

