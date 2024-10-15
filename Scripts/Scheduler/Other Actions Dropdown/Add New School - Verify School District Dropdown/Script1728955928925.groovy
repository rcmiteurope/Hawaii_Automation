import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
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


// Open the browser and navigate to the specified URL
WebUI.openBrowser('')

WebUI.navigateToUrl('https://scheduler-qa.rcmt-timecard.com/')

// Get the WebDriver instance
WebDriver driver = DriverFactory.getWebDriver()

// Assuming islandList is defined globally as a List<String>
List<String> islands = GlobalVariable.regionMenuOptions


// Open the "Add New School" pop-up modal (adjust the selector as needed)
WebUI.selectOptionByValue(findTestObject('Object Repository/AddSchool/Page_Scheduler/select_Other ActionsAdd SchoolAdd StudentAd_8e5993'), 
    'add-school', true)


// Locate the School District dropdown using the XPath
WebElement schoolDistrictDropdown = driver.findElement(By.xpath("//div[@id='filter-wrapper']/div/dialog/div[2]/div[4]/select"))
schoolDistrictDropdown.click()

// Wait for options to be visible (optional, adjust if necessary)
// WebUI.delay(1)

// Get the dropdown options (assuming they are <option> tags within a <select>)
List<WebElement> options = schoolDistrictDropdown.findElements(By.tagName('option'))

// Collect the text values from the dropdown options
List<String> dropdownOptions = options.collect { it.getText().trim() }

// Log the dropdown options for debugging
dropdownOptions.each { option ->
    WebUI.comment("Option found in School District dropdown: " + option)
}

// Verify that all islands from islandList are included in the dropdown
boolean allIslandsExist = true
List<String> missingIslands = []

for (String island : islands) {
    if (!dropdownOptions.contains(island)) {
        allIslandsExist = false
        missingIslands.add(island)
    }
}

// Log results
if (allIslandsExist) {
    WebUI.comment("School District dropdown contains all the expected island options.")
} else {
    WebUI.comment("School District dropdown is missing the following islands: " + missingIslands)
    throw new Exception("Test Failed: School District dropdown is missing the following islands: " + missingIslands)
}

// Close the browser
WebUI.closeBrowser()
