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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

// Open the browser
WebUI.openBrowser('')

// Set viewport size
WebUI.setViewPortSize(GlobalVariable.spreadsheetWidth, GlobalVariable.spreadsheetHeight)

// Get WebDriver instance
WebDriver driver = DriverFactory.getWebDriver()

// Navigate to URL
WebUI.navigateToUrl(GlobalVariable.scheduler_url)

// Find all elements with class name 'studentName'
List<WebElement> endTime = driver.findElements(By.className('endTime'))

// Loop through each element and check if text is empty
for (WebElement element : endTime) {
    String text = element.getText().trim() // Get text and trim any whitespace

    if (text.isEmpty()) {
        WebUI.comment('Found an empty element.')
        KeywordUtil.markFailed('Found an Empty endTime')  // Fail the test if an empty element is found
    } else {
        WebUI.comment("Element text: $text") // Log the text if not empty
    }
}

// Close browser
WebUI.closeBrowser()
