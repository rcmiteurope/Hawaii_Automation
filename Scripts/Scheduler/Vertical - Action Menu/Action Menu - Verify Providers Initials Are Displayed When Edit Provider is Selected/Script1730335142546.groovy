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

WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebUI.click(new TestObject().addProperty('id', ConditionType.EQUALS, 'master-checkbox-toggle160314'))

WebUI.executeJavaScript('document.getElementById("actionmenu-handle").click();', null)

//WebUI.click(findTestObject('Object Repository/Action Menu/Page_Scheduler/button_Edit Provider'))
WebUI.executeJavaScript('document.getElementById("action-menu-edit-provider").click();', null)

WebUI.verifyElementText(findTestObject('Object Repository/Action Menu/Page_Scheduler/button_AB'), 'AB')

WebUI.verifyElementText(findTestObject('Object Repository/Action Menu/Page_Scheduler/button_CD'), 'CD')

WebUI.verifyElementText(findTestObject('Object Repository/Action Menu/Page_Scheduler/button_EF'), 'EF')

WebUI.verifyElementText(findTestObject('Object Repository/Action Menu/Page_Scheduler/button_GH'), 'GH')

WebUI.verifyElementText(findTestObject('Object Repository/Action Menu/Page_Scheduler/button_IJ'), 'IJ')

WebUI.verifyElementText(findTestObject('Object Repository/Action Menu/Page_Scheduler/button_KL'), 'KL')

WebUI.verifyElementText(findTestObject('Object Repository/Action Menu/Page_Scheduler/button_MN'), 'MN')

WebUI.verifyElementText(findTestObject('Object Repository/Action Menu/Page_Scheduler/button_OP'), 'OP')

WebUI.verifyElementText(findTestObject('Object Repository/Action Menu/Page_Scheduler/button_QR'), 'QR')

WebUI.verifyElementText(findTestObject('Object Repository/Action Menu/Page_Scheduler/button_ST'), 'ST')

WebUI.verifyElementText(findTestObject('Object Repository/Action Menu/Page_Scheduler/button_UV'), 'UV')

WebUI.verifyElementText(findTestObject('Object Repository/Action Menu/Page_Scheduler/button_WX'), 'WX')

WebUI.verifyElementText(findTestObject('Object Repository/Action Menu/Page_Scheduler/button_YZ'), 'YZ')

WebUI.closeBrowser()

