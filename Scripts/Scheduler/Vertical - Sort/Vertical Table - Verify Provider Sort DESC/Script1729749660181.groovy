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
import org.openqa.selenium.WebElement as WebElement

// Open browser and navigate to the page
WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url) // Replace with your URL

// Fetch the list of provider names from the table
List<WebElement> providerElements = WebUI.findWebElements(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="vertical-table"]/tbody/tr/td[3]'), 10) // Adjust XPath for provider name column

// Extract text from the elements and store it in a list
List<String> providerNames = providerElements.collect { it.getText().trim() }

// Create a sorted version for comparison (descending order)
List<String> sortedDesc = new ArrayList<>(providerNames)
sortedDesc.sort(Collections.reverseOrder())

// Log the provider names and the expected sorted order for debugging
WebUI.comment('Provider Names: ' + providerNames.toString())
WebUI.comment('Expected Descending Order: ' + sortedDesc.toString())

// Check if the table is already sorted in descending order
if (providerNames == sortedDesc) {
    WebUI.comment('✅ The table is already sorted in descending order.')
} else {
    WebUI.comment('❌ The table is not sorted in descending order.')

    // Click to sort the table in descending order
    WebUI.click(findTestObject('Object Repository/Vertical Table/Page_Scheduler/span_Provider'))

    // Add a short delay to allow the sorting to complete
    WebUI.delay(4)

    // Extract the text again to check the new order
    providerElements = WebUI.findWebElements(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="vertical-table"]/tbody/tr/td[3]'), 10)
    providerNames = providerElements.collect { it.getText().trim() }

    WebUI.comment('Provider Names after clicking SVG: ' + providerNames.toString())

    // Verify the table is now sorted in descending order
    assert providerNames == sortedDesc : '❌ The table is not sorted in descending order.'
    WebUI.comment('✅ The table is correctly sorted in descending order after clicking the SVG.')
}

// Close the browser
WebUI.closeBrowser()
