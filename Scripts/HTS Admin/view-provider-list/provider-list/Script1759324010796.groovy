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
import com.kms.katalon.core.webui.driver.DriverFactory
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import groovy.json.JsonSlurper
import org.openqa.selenium.WebDriver
import org.openqa.selenium.Cookie

// Open Browser
WebUI.openBrowser('')

// Navigate to page
WebUI.navigateToUrl('https://scheduler-qa.rcmt-timecard.com/')

// Wait for dropdown to be present
WebUI.waitForElementVisible(
    findTestObject('Object Repository/HTS-Settings/view-provider-list/provider-list/Page_Scheduler/select_Other Actions00000Add Leave of absen_0510bc'), 
    20
)

// Select "Providers" option by label (handles spaces)
WebUI.selectOptionByValue(
    findTestObject('Object Repository/HTS-Settings/view-provider-list/provider-list/Page_Scheduler/select_Other Actions00000Add Leave of absen_0510bc'),
    'providers',
    false
)
WebUI.delay(10)


// 🔑 Get cookies from browser session
def driver = DriverFactory.getWebDriver()
Set<Cookie> cookies = driver.manage().getCookies()
String cookieHeader = cookies.collect { it.getName() + "=" + it.getValue() }.join("; ")

// 🔑 Clone API request object and inject cookies
def apiRequest = findTestObject('HTS-Settings/provider-list')
apiRequest.getHttpHeaderProperties().add(
	new com.kms.katalon.core.testobject.TestObjectProperty("Cookie",
		com.kms.katalon.core.testobject.ConditionType.EQUALS,
		cookieHeader)
)

// ✅ Use the modified request object
ResponseObject response = WS.sendRequest(apiRequest)
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
def normalize = { s ->
    if (s == null) return ""   // handle null safely
    return s.trim()
            .replaceAll("[’‘]", "'")   // normalize curly quotes
            .replaceAll("\\s+", " ")   // collapse spaces
}

String firstNameUI = WebUI.getText(
    findTestObject('HTS-Settings/view-provider-list/Page_Scheduler/td_FirstName')
)

assert normalize(firstNameUI) == normalize(providerList[0].firstname)

String lastNameUI = WebUI.getText(findTestObject('Object Repository/HTS-Settings/view-provider-list/Page_Scheduler/td_LastName'))

assert lastNameUI == providerList[0].lastname

// Check Sorting by ID
WebUI.click(findTestObject('null'))

// Add logic here: capture first row ID again and validate ascending/descending order
// Check Sorting by First Name
WebUI.click(findTestObject('null'))

// Close browser
WebUI.closeBrowser()
