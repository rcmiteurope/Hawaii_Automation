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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.By as By
import java.util.Random as Random

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6IkVyaWNhLkJvcnJvbWVvQHJjbXQuY29tIiwidXNlcklEIjo4LCJpYXQiOjE3MzE5ODYxMDEsImV4cCI6MTczNDU3ODEwMX0.AUWF2TrOJtXoWXnwJaA3MHQJ0iUgTpDUw2YrdjazB_Q')

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', 'Erica.Borromeo%40rcmt.com'))

driver.manage().addCookie(new Cookie('user_name', 'Borromeo%2C%20Erica'))

WebUI.refresh()

WebUI.click(findTestObject('Object Repository/ScheduleAssignmentDialog/Page_Scheduler/div_OPEN 0830-1030'))

WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="provider-button"]'))

WebUI.click(findTestObject('Object Repository/ScheduleAssignmentDialog/Page_Scheduler/div_Provider_css-19bb58m'))

WebUI.click(findTestObject('Object Repository/ScheduleAssignmentDialog/Page_Scheduler/div_Abad, Jose'))

WebUI.click(findTestObject('Object Repository/ScheduleAssignmentDialog/Page_Scheduler/input_Select Options_sched-checkbox'))

WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="supervisor-button"]'))

WebUI.click(findTestObject('Object Repository/ScheduleAssignmentDialog/Page_Scheduler/div_Supervisor_css-t3ipsp-control'))

WebUI.click(findTestObject('Object Repository/ScheduleAssignmentDialog/Page_Scheduler/div_Conklin, Erin'))

WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="sched-dialog-save"]'))

WebUI.click(findTestObject('Object Repository/ScheduleAssignmentDialog/Page_Scheduler/div_Abad, Jose   o Conklin, Erin 0830-1030'))

WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="hori-remove-supervisor"]'))

WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="sched-dialog-save"]'))

WebUI.verifyElementText(findTestObject('Object Repository/ScheduleAssignmentDialog/Page_Scheduler/p_Abad, Jose_1'), 'Abad, Jose')

WebUI.closeBrowser()

