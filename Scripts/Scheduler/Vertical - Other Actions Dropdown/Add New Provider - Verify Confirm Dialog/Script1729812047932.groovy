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

// Check Horizontal Toggle
WebUI.check(new TestObject("dynamicObj").addProperty("xpath", ConditionType.EQUALS, "//*[@id='root']/main/div[2]/div[1]/div[1]/div/div"))
 

WebUI.waitForElementVisible(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="other-actions-dropdown"]'), 10)

// Click the dropdown to expand it
WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="other-actions-dropdown"]'))

// Select the desired option by value
WebUI.executeJavaScript("document.getElementById('other-actions-dropdown').value = 'add-provider';", null)

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

