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

WebUI.navigateToUrl('http://172.212.97.100/login')

WebUI.click(findTestObject('Page_Hawaii Timecard/button_1'))

WebUI.click(findTestObject('Page_Hawaii Timecard/button_2'))

WebUI.click(findTestObject('Page_Hawaii Timecard/button_3'))

WebUI.click(findTestObject('Page_Hawaii Timecard/button_4'))

WebUI.click(findTestObject('Page_Hawaii Timecard/button_5'))

WebUI.click(findTestObject('Page_Hawaii Timecard/button_0'))

WebUI.click(findTestObject('Page_Hawaii Timecard/button_Login'))

WebUI.selectOptionByValue(findTestObject('Object Repository/Timecard Summary/Page_Hawaii Timecard/select_June 1, 2024June 8, 2024June 15, 202_aca00c'), 
    'Sat Aug 03 2024', true)

WebUI.selectOptionByValue(findTestObject('Object Repository/Timecard Summary/Page_Hawaii Timecard/select_June 1, 2024June 8, 2024June 15, 202_aca00c'), 
    'Sat Jul 20 2024', true)

WebUI.click(findTestObject('Object Repository/Timecard Summary/Page_Hawaii Timecard/p_19'))

WebUI.closeBrowser()

WebUI.openBrowser('')

WebUI.navigateToUrl('http://172.212.97.100/login')

WebUI.click(findTestObject('Page_Hawaii Timecard/button_1'))

WebUI.click(findTestObject('Page_Hawaii Timecard/button_2'))

WebUI.click(findTestObject('Page_Hawaii Timecard/button_3'))

WebUI.click(findTestObject('Page_Hawaii Timecard/button_4'))

WebUI.click(findTestObject('Page_Hawaii Timecard/button_5'))

WebUI.click(findTestObject('Page_Hawaii Timecard/button_0'))

WebUI.click(findTestObject('Page_Hawaii Timecard/button_Login'))

WebUI.click(findTestObject('Object Repository/Timecard Summary/Page_Hawaii Timecard/img'))

WebUI.selectOptionByValue(findTestObject('Object Repository/Timecard Summary/Page_Hawaii Timecard/select_June 1, 2024June 8, 2024June 15, 202_aca00c'), 
    'Sat Aug 03 2024', true)

WebUI.click(findTestObject('Object Repository/Timecard Summary/Page_Hawaii Timecard/button_Thu1'))

WebUI.click(findTestObject('Object Repository/Timecard Summary/Page_Hawaii Timecard/a_Attendance'))

WebUI.click(findTestObject('Object Repository/Timecard Summary/Page_Hawaii Timecard/button_Thu1'))

WebUI.click(findTestObject('Object Repository/Timecard Summary/Page_Hawaii Timecard/p_Wed'))

WebUI.click(findTestObject('Object Repository/Timecard Summary/Page_Hawaii Timecard/p_30'))

WebUI.click(findTestObject('Object Repository/Timecard Summary/Page_Hawaii Timecard/button_Mon29'))

WebUI.closeBrowser()

WebUI.openBrowser('')

WebUI.navigateToUrl('http://172.212.97.100/login')

WebUI.click(findTestObject('Page_Hawaii Timecard/button_1'))

WebUI.click(findTestObject('Page_Hawaii Timecard/button_2'))

WebUI.click(findTestObject('Page_Hawaii Timecard/button_3'))

WebUI.click(findTestObject('Page_Hawaii Timecard/button_4'))

WebUI.click(findTestObject('Page_Hawaii Timecard/button_5'))

WebUI.click(findTestObject('Page_Hawaii Timecard/button_0'))

WebUI.click(findTestObject('Page_Hawaii Timecard/button_Login'))

WebUI.click(findTestObject('Object Repository/Timecard Summary/Page_Hawaii Timecard/img_Week Ending_w-10'))

WebUI.click(findTestObject('Object Repository/Timecard Summary/Page_Hawaii Timecard/a_Timecard Approval'))

WebUI.selectOptionByValue(findTestObject('Object Repository/Timecard Summary/Page_Hawaii Timecard/select_June 1, 2024June 8, 2024June 15, 202_aca00c'), 
    'Sat Aug 03 2024', true)

WebUI.click(findTestObject('Object Repository/Timecard Summary/Page_Hawaii Timecard/a_Timecard'))

WebUI.click(findTestObject('Object Repository/Timecard Summary/Page_Hawaii Timecard/h1_Ready For Approval (1)'))

WebUI.closeBrowser()

