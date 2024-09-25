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
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.By


WebUI.openBrowser('')
WebUI.setViewPortSize(GlobalVariable.spreadsheetWidth, GlobalVariable.spreadsheetHeight)

WebUI.navigateToUrl(GlobalVariable.scheduler_url)
WebDriver driver = DriverFactory.getWebDriver()

WebUI.verifyElementVisible(findTestObject('Object Repository/Page_Scheduler/label_Horizontal'))

WebUI.verifyElementClickable(findTestObject('Object Repository/Page_Scheduler/label_Horizontal'))

WebUI.click(findTestObject('Object Repository/Page_Scheduler/label_Horizontal'))

// Locate all dropdown elements (adjust the XPath to target your dropdowns)
List<WebElement> dropdownElements = driver.findElements(By.xpath('//*[@id="filter-wrapper"]'))

// Iterate through each dropdown element and verify if it's clickable
for (int i = 0; i < dropdownElements.size(); i++) {
    WebElement dropdownElement = dropdownElements.get(i)

    // Convert WebElement to TestObject so Katalon can interact with it
    TestObject toDropdown = WebUI.convertWebElementToTestObject(dropdownElement)

    // Verify if the dropdown is clickable
    if (WebUI.verifyElementClickable(toDropdown, FailureHandling.CONTINUE_ON_FAILURE)) {
        println("Dropdown " + (i + 1) + " is clickable.")
    } else {
        println("Dropdown " + (i + 1) + " is NOT clickable.")
    }
}