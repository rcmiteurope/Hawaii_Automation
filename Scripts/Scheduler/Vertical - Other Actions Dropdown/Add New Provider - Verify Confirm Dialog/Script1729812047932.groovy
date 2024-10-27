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
import com.kms.katalon.core.testobject.ConditionType as ConditionType

WebUI.openBrowser('')

WebUI.navigateToUrl('https://scheduler-qa.rcmt-timecard.com/')

WebUI.selectOptionByValue(findTestObject('Object Repository/AddProvider/Page_Scheduler/select_Other ActionsAdd SchoolAdd StudentAd_8e5993'), 
    'add-provider', true)

WebUI.setText(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="filter-wrapper"]/div[1]/dialog/div[2]/div[1]/input'),
    'Erica')

WebUI.setText(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="filter-wrapper"]/div[1]/dialog/div[2]/div[2]/input'),
	 'Borromeo')

WebUI.setText(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="filter-wrapper"]/div[1]/dialog/div[2]/div[3]/input'),
	 'eri@gmail.com')


WebUI.click(findTestObject('Object Repository/AddProvider/Page_Scheduler/button_Submit'))

WebUI.verifyElementPresent(findTestObject('Object Repository/AddProvider/Page_Scheduler/dialog_Provider Already ExistsA provider wi_abeed5'), 
    0)

WebUI.closeBrowser()
