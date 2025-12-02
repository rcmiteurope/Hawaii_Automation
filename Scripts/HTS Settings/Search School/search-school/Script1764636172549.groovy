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
import groovy.json.JsonSlurper
import org.openqa.selenium.WebElement

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url)

TestObject dropdown = findTestObject('Object Repository/HTS-Settings/Search School/Page_Scheduler/select_Other Actions00000Add Leave of absen_0510bc')

// Wait until the element is present and visible
WebUI.waitForElementVisible(dropdown, 10)
WebUI.waitForElementClickable(dropdown, 10)
WebUI.selectOptionByValue(dropdown, 'schools', false)

// Call API to get provider list
def response = WS.sendRequest(findTestObject('HTS-Settings/school-list'))
def responseText = response.getResponseText()
WebUI.comment("API Response: ${responseText}")

// --- PARSE JSON ---
def school = new JsonSlurper().parseText(responseText)

// --- VALIDATE API STRUCTURE ---
assert school.data instanceof List : "API 'data' is not a list. Response: ${responseText}"
assert !school.data.isEmpty() : "School list is EMPTY. Response: ${responseText}"

// Extract school names
List<String> allSchoolNames = school.data
    .collect { it.schoolName }
    .findAll { it != null && it.trim() != "" }

// Validate
//assert allSchoolNames != null : "allSchoolNames is NULL"
//assert !allSchoolNames.isEmpty() : "School list is EMPTY"
assert allSchoolNames.size() >= 5 : "Not enough names to take 5 random schools"

Collections.shuffle(allSchoolNames)  // in-place shuffle
List<String> randomSchools = allSchoolNames.take(5)
WebUI.comment("Random schools: ${randomSchools}")

TestObject searchBox = findTestObject('Object Repository/HTS-Settings/Search School/Page_Scheduler/input_Schools_ant-input css-pjilya')

for (String schoolName : randomSchools) {

    WebUI.comment("------ Searching: ${schoolName} ------")

    // CLEAR the field before typing next
	WebUI.setText(searchBox, "")
    WebUI.delay(1)

    // TYPE search text
    WebUI.setText(searchBox, schoolName)

    // WAIT results
    WebUI.delay(2)
}

WebUI.closeBrowser()
