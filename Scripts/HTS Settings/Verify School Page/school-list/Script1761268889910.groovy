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
import org.openqa.selenium.WebDriver
import org.openqa.selenium.Cookie
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.testobject.ResponseObject
import groovy.json.JsonSlurper
import org.openqa.selenium.WebElement
import com.kms.katalon.core.util.KeywordUtil

// --- Normalize helper ---
def normalize = { s ->
    return s == null ? '' : s.trim().replaceAll("\\s+", " ").replaceAll("[’‘]", "'").toLowerCase()
}

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

driver.manage().addCookie(new Cookie('sc_auth_token', GlobalVariable.sc_auth_token_jirome))

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email_jirome))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name_jirome))

WebUI.delay(3)

// Wait for page and dropdown to reload
def dropdown = findTestObject('Object Repository/HTS-Settings/view-school-list/Page_Scheduler/select_Other Actions00000Add Leave of absen_0510bc')

// Wait until the element is present and visible
WebUI.waitForElementVisible(dropdown, 10)
WebUI.waitForElementClickable(dropdown, 10)
WebUI.selectOptionByValue(dropdown, 'schools', false)

// --- Go to School page ---
WebUI.waitForElementVisible(findTestObject('HTS-Settings/view-school-list/Page_Scheduler/span_Schools'), 10)
WebUI.click(findTestObject('HTS-Settings/view-school-list/Page_Scheduler/span_Schools'))
WebUI.delay(5)

// --- Get API Data ---
ResponseObject response = WS.sendRequest(findTestObject('HTS-Settings/school-list'))
WS.verifyResponseStatusCode(response, 200)

// --- Parse JSON Response ---
def jsonResponse = new JsonSlurper().parseText(response.getResponseBodyContent())
def apiData = jsonResponse.data

// --- Collect Expected Data from API ---
def apiSchools = apiData.collect { [schoolName: it.schoolName, islandName: it.islandName] }

// --- Get data from UI table ---
// Adjust your Test Object XPaths accordingly
List<WebElement> schoolNameElements = WebUI.findWebElements(findTestObject('HTS-Settings/view-school-list/Page_Scheduler/td_School Name'), 10)
List<WebElement> islandElements = WebUI.findWebElements(findTestObject('HTS-Settings/view-school-list/Page_Scheduler/td_Island'), 10)

def uiSchoolList = []
for (int i = 0; i < schoolNameElements.size(); i++) {
	uiSchoolList << [
		school_name: normalize(schoolNameElements[i].getText()),
		island: normalize(islandElements[i].getText())
	]
}

// --- Compare API vs UI ---
println "\n--- VALIDATING SCHOOL LIST (School Name + Island) ---"
println "API Row Count: ${apiSchools.size()}"
println "UI Row Count:  ${uiSchoolList.size()}\n"

boolean allMatch = true
def mismatches = []

// --- Compare API -> UI ---
for (def apiSchool : apiSchools) {
    def match = uiSchoolList.find { ui ->
        ui.school_name == normalize(apiSchool.schoolName) &&
        ui.island == normalize(apiSchool.islandName)
    }
    if (!match) {
        def uiMatch = uiSchoolList.find { ui -> ui.school_name == normalize(apiSchool.schoolName) }
        if (uiMatch) {
            mismatches << [
                schoolName: apiSchool.schoolName,
                expectedIsland: apiSchool.islandName,
                actualIsland: uiMatch.island
            ]
        } else {
            mismatches << [
                schoolName: apiSchool.schoolName,
                expectedIsland: apiSchool.islandName,
                actualIsland: "Not found in UI"
            ]
        }
        allMatch = false
    }
}

// --- Compare UI -> API (extra rows) ---
for (def uiSchool : uiSchoolList) {
    def match = apiSchools.find { api ->
        normalize(api.schoolName) == uiSchool.school_name &&
        normalize(api.islandName) == uiSchool.island
    }
    if (!match) {
        mismatches << [
            schoolName: uiSchool.school_name,
            expectedIsland: "Not found in API",
            actualIsland: uiSchool.island
        ]
        allMatch = false
    }
}

// --- Log mismatches clearly ---
if (mismatches.size() > 0) {
    KeywordUtil.logInfo("\n❌ MISMATCH FOUND BETWEEN API AND UI DATA ❌")
    KeywordUtil.logInfo("--------------------------------------------------")
    mismatches.eachWithIndex { m, i ->
        KeywordUtil.logInfo("${i + 1}. School: ${m.schoolName}")
        KeywordUtil.logInfo("   Expected Island: ${m.expectedIsland}")
        KeywordUtil.logInfo("   Actual Island:   ${m.actualIsland}")
        KeywordUtil.logInfo("--------------------------------------------------")
		println("${i + 1}. School: ${m.schoolName} | Expected: ${m.expectedIsland} | Actual: ${m.actualIsland}")
		KeywordUtil.logInfo("${i + 1}. School: ${m.schoolName} | Expected: ${m.expectedIsland} | Actual: ${m.actualIsland}")
    }
} else {
    KeywordUtil.logInfo("UI matches API data completely (School Name + Island).")
}


WebUI.closeBrowser()


