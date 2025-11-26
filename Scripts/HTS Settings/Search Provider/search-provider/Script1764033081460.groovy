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

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url)



TestObject dropdown = findTestObject('HTS-Settings/view-provider-list/Page_Scheduler/select_Other Actions00000Add Leave of absen_0510bc')

// Wait until the element is present and visible
WebUI.waitForElementVisible(dropdown, 10)
WebUI.waitForElementClickable(dropdown, 10)
WebUI.selectOptionByValue(dropdown, 'providers', false)

// Call API to get provider list
def response = WS.sendRequest(findTestObject('HTS-Settings/provider-list'))
def responseText = response.getResponseText()
WebUI.comment("API Response: ${responseText}")

// --- PARSE JSON ---
def providers = new JsonSlurper().parseText(responseText)

// Validate response
if (!providers || !(providers instanceof List) || providers.isEmpty()) {
    assert false : "Provider list is EMPTY or INVALID. Response: ${responseText}"
}

// --- Filter providers: FIRST NAME NOT NULL ---
providers = providers.findAll {
	it.firstname && it.firstname.toString().trim()
}

if (providers.isEmpty()) {
    assert false : "No providers contain a valid FIRST NAME to search."
}

// --- RANDOM 5 FIRST NAMES ---
def shuffled = providers.collect()          // clone list
Collections.shuffle(shuffled)               // randomize
def randomProviders = shuffled.take(Math.min(5, shuffled.size()))

WebUI.comment("Random FIRST NAME search targets: ${randomProviders*.firstname}")

TestObject searchBox = findTestObject('Object Repository/HTS-Settings/Search Provider/Page_Scheduler/input_Providers_ant-input css-pjilya')

// --- VALIDATE TEST OBJECTS ---
if (searchBox == null) {
	assert false : "❌ TestObject searchBox is NULL. Check the OR path: input_Providers"
}

// --- WAIT FOR SEARCH BOX ---
WebUI.waitForElementVisible(searchBox, 20)
WebUI.waitForElementClickable(searchBox, 20)

// --- SEARCH LOOP ---
randomProviders.each { provider ->

    def keyword = provider.firstname
    WebUI.comment("Searching FIRST NAME: ${keyword}")

    // Clear search box
    WebUI.setText(searchBox, "")
    WebUI.delay(2)

    // Enter first name
    WebUI.setText(searchBox, keyword)
    WebUI.delay(3)
}

WebUI.closeBrowser()

