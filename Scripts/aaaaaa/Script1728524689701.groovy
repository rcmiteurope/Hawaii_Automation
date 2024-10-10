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

x`xxWebUI.selectOptionByValue(findTestObject('Object Repository/select_All Students Abe, Tyler Avilla, Linc_0e3850'), '{"id":1728,"firstname":"Nya","lastname":"Jay-Funaki","source":1}', 
    true)

WebUI.selectOptionByValue(findTestObject('Object Repository/select_TodayTomorrowThis WeekNext WeekOctob_108650'), '{"week_start":"2024-10-13","week_end":"2024-10-19","filter_type":0}', 
    true)

WebUI.click(findTestObject('Object Repository/input_Date_select-all-toggle'))

WebUI.click(findTestObject('Object Repository/path'))

WebUI.click(findTestObject('Object Repository/span_Edit Provider'))

WebUI.click(findTestObject('Object Repository/button_AB'))

WebUI.click(findTestObject('Object Repository/h2_Aguirre, Lucia'))

WebUI.closeBrowser()

