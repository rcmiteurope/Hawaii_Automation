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

Cookie authCookie = new Cookie('sc_auth_token', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6IkVyaWNhLkJvcnJvbWVvQHJjbXQuY29tIiwidXNlcklEIjo4LCJpYXQiOjE3MzE5ODYxMDEsImV4cCI6MTczNDU3ODEwMX0.AUWF2TrOJtXoWXnwJaA3MHQJ0iUgTpDUw2YrdjazB_Q')

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', 'Erica.Borromeo%40rcmt.com'))

driver.manage().addCookie(new Cookie('user_name', 'Borromeo%2C%20Erica'))

WebUI.refresh()

TestObject horizontalToggle = new TestObject()

horizontalToggle.addProperty('xpath', ConditionType.EQUALS, '//*[@id="root"]/main/div[2]/div/div/label/div')

WebUI.check(horizontalToggle)

WebUI.selectOptionByLabel(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="date_filter_select"]'), 'Next Week', false)

WebUI.click(findTestObject('Object Repository/Action Menu/Page_Scheduler/input_Mon, 1125 0800 - 1415_master-checkbox_5083b2'))

WebUI.executeJavaScript('document.getElementById("actionmenu-handle").click();', null)

WebElement weEditSupervisor = driver.findElement(By.xpath('//*[@id="action-menu-edit-provider"]/following-sibling::button'))	// todo: change xpath
TestObject toEditSupervisor = WebUI.convertWebElementToTestObject(weEditSupervisor)
WebUI.mouseOver(toEditSupervisor)	
WebUI.click(toEditSupervisor)
 

TestObject dynamicObject = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//*[@id='change-supervisor-menu-AB']")
WebUI.verifyElementText(dynamicObject, 'AB')

TestObject dynamicObject1 = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//*[@id='change-supervisor-menu-CD']")
WebUI.verifyElementText(dynamicObject1, 'CD')
 
TestObject dynamicObject2 = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//*[@id='change-supervisor-menu-EF']")
WebUI.verifyElementText(dynamicObject2, 'EF')

TestObject dynamicObject3 = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//*[@id='change-supervisor-menu-GH']")
WebUI.verifyElementText(dynamicObject3, 'GH')
 
TestObject dynamicObject4 = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//*[@id='change-supervisor-menu-IJ']")
WebUI.verifyElementText(dynamicObject4, 'IJ')
  
TestObject dynamicObject5 = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//*[@id='change-supervisor-menu-KL']")
WebUI.verifyElementText(dynamicObject5, 'KL')

TestObject dynamicObject6 = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//*[@id='change-supervisor-menu-MN']")
WebUI.verifyElementText(dynamicObject6, 'MN')
 
TestObject dynamicObject7 = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//*[@id='change-supervisor-menu-OP']")
WebUI.verifyElementText(dynamicObject7, 'OP')

TestObject dynamicObject8 = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//*[@id='change-supervisor-menu-QR']")
WebUI.verifyElementText(dynamicObject8, 'QR')

TestObject dynamicObject9 = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//*[@id='change-supervisor-menu-ST']")
WebUI.verifyElementText(dynamicObject9, 'ST')
 
TestObject dynamicObject10 = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//*[@id='change-supervisor-menu-UV']")
WebUI.verifyElementText(dynamicObject10, 'UV')
  
TestObject dynamicObject11 = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//*[@id='change-supervisor-menu-WX']")
WebUI.verifyElementText(dynamicObject11, 'WX')
 
TestObject dynamicObject12 = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//*[@id='change-supervisor-menu-YZ']")
WebUI.verifyElementText(dynamicObject12, 'YZ')

WebUI.closeBrowser()

