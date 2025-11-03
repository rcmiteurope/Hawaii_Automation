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

WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

driver.manage().addCookie(new Cookie('sc_auth_token', GlobalVariable.sc_auth_token_jirome))

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email_jirome))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name_jirome))

WebUI.selectOptionByValue(findTestObject('Object Repository/HTS-Settings/view-provider-list/Page_Scheduler/select_Other Actions00000Add Leave of absen_0510bc'), 
    'workorders', true)

WebUI.click(findTestObject('Object Repository/HTS-Settings/view-provider-list/Page_Scheduler/span_Providers'))

// Call API to get provider list
ResponseObject response = WS.sendRequest(findTestObject('HTS-Settings/provider-list'))

WS.verifyResponseStatusCode(response, 200)

// Parse JSON
def providerList = new JsonSlurper().parseText(response.getResponseText())

println('Response text: ' + response.getResponseText())

println('Status code: ' + response.getStatusCode())

println('Response body: ' + response.getResponseText())

// Verify first record in API response
assert providerList[0].providerID == 2203

assert providerList[0].firstname == 'A\'Taizha'

assert providerList[0].lastname == 'Wells'

// Validate UI Table against API Data
String firstNameUI = WebUI.getText(findTestObject('Object Repository/HTS-Settings/view-provider-list/Page_Scheduler/td_FirstName'))

assert firstNameUI == providerList[0].firstname

String lastNameUI = WebUI.getText(findTestObject('Object Repository/HTS-Settings/view-provider-list/Page_Scheduler/td_LastName'))

assert lastNameUI == providerList[0].lastname

// Check Sorting by ID
WebUI.click(findTestObject('null'))

// Add logic here: capture first row ID again and validate ascending/descending order
// Check Sorting by First Name
WebUI.click(findTestObject('null'))

// Close browser
WebUI.closeBrowser()

