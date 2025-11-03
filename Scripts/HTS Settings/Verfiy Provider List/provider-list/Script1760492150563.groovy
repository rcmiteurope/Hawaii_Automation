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
import com.kms.katalon.core.testobject.ResponseObject
import groovy.json.JsonSlurper
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.ObjectRepository as OR
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import org.openqa.selenium.Cookie
import org.openqa.selenium.WebElement 
import org.openqa.selenium.By

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

driver.manage().addCookie(new Cookie('sc_auth_token', GlobalVariable.sc_auth_token_jirome))

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email_jirome))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name_jirome))

WebUI.delay(3)
//WebUI.selectOptionByValue(findTestObject('Object Repository/HTS-Settings/view-provider-list/Page_Scheduler/select_Other Actions00000Add Leave of absen_0510bc'), 
//    'providers', true)

TestObject dropdown = findTestObject('HTS-Settings/view-provider-list/Page_Scheduler/select_Other Actions00000Add Leave of absen_0510bc')

// Wait until the element is present and visible
WebUI.waitForElementVisible(dropdown, 10)
WebUI.waitForElementClickable(dropdown, 10)
WebUI.selectOptionByValue(dropdown, 'providers', false)

// --- Normalize helper ---
def normalize = { s ->
    return s == null ? '' : s.trim().replaceAll("\\s+", " ").replaceAll("[’‘]", "'")
}

// --- Go to Providers page ---
WebUI.waitForElementVisible(findTestObject('HTS-Settings/view-provider-list/Page_Scheduler/span_Providers'), 10)
WebUI.click(findTestObject('HTS-Settings/view-provider-list/Page_Scheduler/span_Providers'))
WebUI.delay(5)

// --- Get API Data ---
ResponseObject response = WS.sendRequest(findTestObject('HTS-Settings/provider-list'))
WS.verifyResponseStatusCode(response, 200)

def providerList = new JsonSlurper().parseText(response.getResponseText())
println "API returned ${providerList.size()} provider records."

// --- Wait for the table ---
WebUI.waitForElementVisible(findTestObject('HTS-Settings/view-provider-list/Page_Scheduler/td_FirstName'), 15)
WebUI.delay(2)

// --- Get all UI table rows ---
List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"))
println "${rows.size()}"

// --- Counters ---
int matched = 0
List<String> notFoundList = []

// --- Loop through all rows ---
rows.eachWithIndex { row, i ->
    List<WebElement> cells = row.findElements(By.tagName("td"))
    if (cells.size() >= 6) {
        def uiID = normalize(cells[0].getText())
        def uiFirstName = normalize(cells[1].getText())
        def uiLastName = normalize(cells[2].getText())
        def uiEmail = normalize(cells[3].getText())
        def uiPin = normalize(cells[4].getText())
        def uiIsland = normalize(cells[5].getText())

        // find provider in API
        def match = providerList.find {
            normalize(it.providerID?.toString()) == uiID
        }

        if (match) {
            matched++
        } else {
            notFoundList.add("Row ${i+1} | Provider ID: ${uiID}, Name: ${uiFirstName} ${uiLastName}")
        }
    }
}

// --- Summary Report ---
println "--------------------------------------------"
println "Validation Summary:"
println "Matched: ${matched}"
println "Not Found: ${notFoundList.size()}"
println "Total UI Rows: ${rows.size()}"
println "--------------------------------------------"

// --- Log all not found entries ---
if (notFoundList.size() > 0) {
    println "Providers NOT found in API:"
    notFoundList.each { println it }
}

// --- Final assertion (after loop) ---
assert notFoundList.isEmpty() : "Some providers shown in the UI were not found in API! Total: ${notFoundList.size()}"


// Close browser
WebUI.closeBrowser()

