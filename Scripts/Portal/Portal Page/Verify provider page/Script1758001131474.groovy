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
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.DayOfWeek


WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.portal_url)


WebUI.click(findTestObject('Object Repository/Portal/Page_/span_Sign in'))

WebUI.setText(findTestObject('Object Repository/Portal/Verify provider page/Page_Sign in to your account/input_Sign in to access Hawaii Timecard Por_5df149'),
	GlobalVariable.portalUsername)


WebUI.setEncryptedText(findTestObject('Object Repository/Portal/Verify provider page/Page_Sign in to your account/input_Enter password_passwd'),
	GlobalVariable.portalPassword)

WebUI.click(findTestObject('Object Repository/Portal/Verify provider page/Page_Sign in to your account/input_Sign in to access Hawaii Timecard Por_43c40a'))


LocalDate today = LocalDate.now()

// Find the upcoming Saturday (week ending)
// If today is Saturday, we’ll use today
LocalDate weekEndingDate = today.with(DayOfWeek.SATURDAY)

// Format the date in your API format: yyyy-MM-dd 00:00:00
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
String formattedDate = weekEndingDate.format(formatter) + "00:00:00"

// Step 5: Build dynamic API URL
String apiUrl = "https://timecard-portal-qa.rcmt-timecard.com/api/v1/providers?page=1&weekEnding=${formattedDate}&providerName="


// Call API with dynamic date
RequestObject request = new RequestObject()
request.setRestUrl(apiUrl)
request.setRestRequestMethod("GET")

ResponseObject response = WS.sendRequest(request)

// Parse API response
def jsonResponse = new JsonSlurper().parseText(response.getResponseText())

assert jsonResponse.isSuccess == true : "API call failed!"

def apiProviders = jsonResponse.data



// Navigate to Providers Page
WebUI.click(findTestObject('Object Repository/Portal/Verify provider page/Page/a_Timesheet Data'))

WebUI.click(findTestObject('Object Repository/Portal/Verify provider page/Page/th_Provider ID'))

WebUI.click(findTestObject('Object Repository/Portal/Verify provider page/Page/th_Provider'))

WebUI.click(findTestObject('Object Repository/Portal/Verify provider page/Page/th_Provider Email'))

// Verify each provider from API matches UI
apiProviders.eachWithIndex { provider, index ->

	String uiProviderID = WebUI.getText(findTestObject('Portal/Verify provider page/Page/Page_Providers/table_providerID', [('row'): index+1]))
	String uiProviderName = WebUI.getText(findTestObject('Portal/Verify provider page/Page/Page_Providers/table_providerName', [('row'): index+1]))
	String uiProviderEmail = WebUI.getText(findTestObject('Portal/Verify provider page/Page/Page_Providers/table_providerEmail', [('row'): index+1]))

	// Combine first & last name (from API) for validation
	String expectedName = provider.provider_firstname + " " + provider.provider_lastname

	assert uiProviderID == provider.provider_ID.toString() : "Mismatch in Provider ID at row ${index+1}"
	assert uiProviderName == expectedName : "Mismatch in Provider Name at row ${index+1}"
	assert uiProviderEmail == provider.provider_email : "Mismatch in Provider Email at row ${index+1}"
}


WebUI.closeBrowser()


