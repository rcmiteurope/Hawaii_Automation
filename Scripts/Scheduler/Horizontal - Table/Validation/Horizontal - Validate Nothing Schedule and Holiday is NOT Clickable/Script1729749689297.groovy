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

WebUI.openBrowser('')
WebUI.setViewPortSize(GlobalVariable.spreadsheetWidth,GlobalVariable.spreadsheetHeight)

// Get WebDriver instance
WebDriver driver = DriverFactory.getWebDriver()

// Navigate to URL
WebUI.navigateToUrl(GlobalVariable.scheduler_url)

// Wait for the horizontal toggle to be visible
WebUI.waitForElementVisible(findTestObject('Page_Scheduler/div_Horizontal'), 10)

// Click the horizontal toggle
WebUI.click(findTestObject('Page_Scheduler/div_Horizontal'))

// Get the table element using XPath
WebElement table = driver.findElement(By.xpath('//*[@id="root"]/main/div[5]/table'))

// Get all the rows of the table
List<WebElement> rows = table.findElements(By.tagName('tr')) // Changed to tagName 'tr' to get all rows

for (WebElement row : rows) {
    // Get all the cells in the current row
    List<WebElement> cells = row.findElements(By.tagName('td')) // Changed to tagName 'td' to get all cells in the row

    for (WebElement cell : cells) {
        String cellText = cell.getText().trim()

        // Check if the cell text contains "Nothing Schedule" or "Holiday"
        if (cellText.equalsIgnoreCase('Nothing Schedule') || cellText.contains('Holiday')) {
            println('Cell with text "' + cellText + '" is not clickable')
        } else {
            println('Cell with text "' + cellText + '" is clickable')

            try {
                // Attempt to perform the click action on the cell
                cell.click()
                println('Clicked on cell with text: "' + cellText + '"')
            } catch (Exception e) {
                println('Cell with text "' + cellText + '" could not be clicked: ' + e.message)
            }
        }
    }
}

// Close browser (Optional, uncomment if you want to close the browser at the end)
// WebUI.closeBrowser()
