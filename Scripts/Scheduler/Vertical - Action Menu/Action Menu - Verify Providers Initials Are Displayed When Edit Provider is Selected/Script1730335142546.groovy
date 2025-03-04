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
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', GlobalVariable.sc_auth_token)

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name))

WebUI.refresh()
/*
 * // Check Horizontal Toggle WebUI.check(new
 * TestObject("dynamicObj").addProperty("xpath", ConditionType.EQUALS,
 * "//*[@id='root']/main/div[2]/div[1]/div[1]/div/div"))
 * 
 * 
 * //WebUI.selectOptionByLabel(new TestObject().addProperty('xpath',
 * ConditionType.EQUALS, '//*[@id="date_filter_select"]'), 'Next Week', false)
 * 
 * WebUI.click(findTestObject('Object Repository/Action
 * Menu/Page_Scheduler/input_Mon, 1125 0800 - 1415_master-checkbox_5083b2'))
 * 
 * WebUI.executeJavaScript('document.getElementById("actionmenu-handle").click()
 * ;', null)
 * 
 * WebElement weEditProvider =
 * driver.findElement(By.xpath('//*[@id="provider-callout"]/following-sibling::
 * button')) // todo: change xpath TestObject toEditProvider =
 * WebUI.convertWebElementToTestObject(weEditProvider)
 * WebUI.mouseOver(toEditProvider) WebUI.click(toEditProvider)
 * 
 * 
 * WebUI.verifyElementText(findTestObject('Object Repository/Action
 * Menu/Page_Scheduler/button_AB'), 'AB')
 * 
 * WebUI.verifyElementText(findTestObject('Object Repository/Action
 * Menu/Page_Scheduler/button_CD'), 'CD')
 * 
 * WebUI.verifyElementText(findTestObject('Object Repository/Action
 * Menu/Page_Scheduler/button_EF'), 'EF')
 * 
 * WebUI.verifyElementText(findTestObject('Object Repository/Action
 * Menu/Page_Scheduler/button_GH'), 'GH')
 * 
 * WebUI.verifyElementText(findTestObject('Object Repository/Action
 * Menu/Page_Scheduler/button_IJ'), 'IJ')
 * 
 * WebUI.verifyElementText(findTestObject('Object Repository/Action
 * Menu/Page_Scheduler/button_KL'), 'KL')
 * 
 * WebUI.verifyElementText(findTestObject('Object Repository/Action
 * Menu/Page_Scheduler/button_MN'), 'MN')
 * 
 * WebUI.verifyElementText(findTestObject('Object Repository/Action
 * Menu/Page_Scheduler/button_OP'), 'OP')
 * 
 * WebUI.verifyElementText(findTestObject('Object Repository/Action
 * Menu/Page_Scheduler/button_QR'), 'QR')
 * 
 * WebUI.verifyElementText(findTestObject('Object Repository/Action
 * Menu/Page_Scheduler/button_ST'), 'ST')
 * 
 * WebUI.verifyElementText(findTestObject('Object Repository/Action
 * Menu/Page_Scheduler/button_UV'), 'UV')
 * 
 * WebUI.verifyElementText(findTestObject('Object Repository/Action
 * Menu/Page_Scheduler/button_WX'), 'WX')
 * 
 * WebUI.verifyElementText(findTestObject('Object Repository/Action
 * Menu/Page_Scheduler/button_YZ'), 'YZ')
 */
WebUI.closeBrowser()

