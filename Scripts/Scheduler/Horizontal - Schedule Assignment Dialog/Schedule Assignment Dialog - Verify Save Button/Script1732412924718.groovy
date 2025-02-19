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
import org.openqa.selenium.Cookie as Cookie
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.Random

WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.scheduler_url)
WebDriver driver = DriverFactory.getWebDriver()
Cookie authCookie = new Cookie('sc_auth_token', GlobalVariable.sc_auth_token)

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name))

WebUI.refresh()

// Table column mapping: Day of the week → Table column index
Map<DayOfWeek, Integer> dayColumnMap = [
    (DayOfWeek.MONDAY)    : 2,
    (DayOfWeek.TUESDAY)   : 3,
    (DayOfWeek.WEDNESDAY) : 4,
    (DayOfWeek.THURSDAY)  : 5,
    (DayOfWeek.FRIDAY)    : 6
]

// Get today's day of the week
DayOfWeek currentDay = LocalDate.now().getDayOfWeek()

// Extract the column indexes for today and future days
List<Integer> targetColumns = dayColumnMap.findAll { key, value ->
    key.compareTo(currentDay) >= 0 // Include today and future days only
}.values().toList()

println "Today's day is: ${currentDay}"
println "Target columns (today and future): ${targetColumns}"

// XPath for cells in the target columns
String baseXPath = "//table[@id='horizontal-table']//tbody//td[{column}]/div"

// Find and click random cells
Random random = new Random()
for (Integer column : targetColumns) {
    // Build XPath for cells in the current column
    String columnXPath = baseXPath.replace("{column}", column.toString())
    
    List<WebElement> cells = DriverFactory.getWebDriver().findElements(By.xpath(columnXPath))
    
    if (!cells.isEmpty()) {
        // Randomly select one cell from the column
        int randomIndex = random.nextInt(cells.size())
        WebElement selectedCell = cells.get(randomIndex)
        
        println "Clicking cell in column ${column}: " + selectedCell.getText()
        selectedCell.click()
    } else {
        println "No cells found in column ${column}"
    }
}


WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="provider-button"]'))

WebUI.click(new TestObject().addProperty("id", ConditionType.EQUALS, "provider-search-bar"))

WebUI.click(new TestObject().addProperty("xpath", ConditionType.EQUALS, "//div[@role='option' and text()='Abad, Jose']"))

WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="sched-dialog-save"]'))

WebUI.closeBrowser()

