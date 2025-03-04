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

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', GlobalVariable.sc_auth_token)

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name))

WebUI.refresh()


WebUI.click(findTestObject('Object Repository/Horizontal - Bugs/Page_Scheduler/div_Leeward'))

WebUI.click(findTestObject('Object Repository/Horizontal - Bugs/Page_Scheduler/div_OPEN 0745-1400'))

WebUI.click(findTestObject('Object Repository/Horizontal - Bugs/Page_Scheduler/button_Holiday'))

WebUI.setText(findTestObject('Object Repository/Horizontal - Bugs/Page_Scheduler/input_Holiday Name_swal2-input'), 'Test Holiday')

WebUI.click(findTestObject('Object Repository/Horizontal - Bugs/Page_Scheduler/button_OK'))

WebUI.click(findTestObject('Object Repository/Horizontal - Bugs/Page_Scheduler/button_Save'))

WebUI.click(findTestObject('Object Repository/Horizontal - Bugs/Page_Scheduler/div_Test Holiday'))

WebUI.click(findTestObject('Object Repository/Horizontal - Bugs/Page_Scheduler/button_Provider'))

WebUI.click(findTestObject('Object Repository/Horizontal - Bugs/Page_Scheduler/div_Provider_css-19bb58m'))

WebUI.click(findTestObject('Object Repository/Horizontal - Bugs/Page_Scheduler/div_Abad, Jose'))

WebUI.click(findTestObject('Object Repository/Horizontal - Bugs/Page_Scheduler/button_Save'))

WebUI.click(findTestObject('Object Repository/Horizontal - Bugs/Page_Scheduler/div_OPEN 0745-1415'))

WebUI.click(findTestObject('Object Repository/Horizontal - Bugs/Page_Scheduler/button_Holiday'))

WebUI.setText(findTestObject('Object Repository/Horizontal - Bugs/Page_Scheduler/input_Holiday Name_swal2-input'), 'Test 2')

WebUI.click(findTestObject('Object Repository/Horizontal - Bugs/Page_Scheduler/button_OK'))

WebUI.click(findTestObject('Object Repository/Horizontal - Bugs/Page_Scheduler/button_Save'))

WebUI.verifyElementText(findTestObject('Object Repository/Horizontal - Bugs/Page_Scheduler/p_Abad, Jose'), 'Abad, Jose')

WebUI.closeBrowser();