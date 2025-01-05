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

// Open browser and navigate to the URL
WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.scheduler_url)

// Add authentication cookies
WebDriver driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', GlobalVariable.sc_auth_token)

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name))

WebUI.refresh()
// Toggle horizontal view
TestObject horizontalToggle = new TestObject()
horizontalToggle.addProperty('xpath', ConditionType.EQUALS, '//*[@id="root"]/main/div[2]/div/div/label/div')
WebUI.check(horizontalToggle)

WebUI.selectOptionByValue(findTestObject('Object Repository/AddProvider/Page_Scheduler/select_Other ActionsAdd SchoolAdd StudentAd_8e5993'), 
    'add-workorder', true)

// Verify the presence of input fields
TestObject inputField1 = new TestObject()
inputField1.addProperty('xpath', ConditionType.EQUALS, '//*[@id="filter-wrapper"]/div/dialog/div[2]/div[1]/div[1]/div')
WebUI.verifyElementPresent(inputField1, 0)

TestObject inputField2 = new TestObject()
inputField2.addProperty('xpath', ConditionType.EQUALS, '//*[@id="filter-wrapper"]/div/dialog/div[2]/div[1]/div[2]/div/div/div[1]/div[2]')
WebUI.verifyElementPresent(inputField2, 0)

TestObject inputField3 = new TestObject()
inputField3.addProperty('xpath', ConditionType.EQUALS, '//*[@id="filter-wrapper"]/div/dialog/div[2]/div[2]/div/div')
WebUI.verifyElementPresent(inputField3, 0)

// Verify the presence of Submit and Cancel buttons
TestObject submitCancelDiv = new TestObject()
submitCancelDiv.addProperty('xpath', ConditionType.EQUALS, '//*[@id="filter-wrapper"]/div/dialog/div[2]/div[3]')
WebUI.verifyElementPresent(submitCancelDiv, 0)

// Close the browser
WebUI.closeBrowser()