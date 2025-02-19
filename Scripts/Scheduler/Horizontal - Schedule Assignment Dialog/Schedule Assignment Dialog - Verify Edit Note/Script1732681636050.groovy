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
import java.util.Random

WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.scheduler_url)
WebDriver driver = DriverFactory.getWebDriver()
Cookie authCookie = new Cookie('sc_auth_token', GlobalVariable.sc_auth_token)

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name))

WebUI.refresh()

String cellsXPath = "(//table[@id='horizontal-table'])[2]//tbody//td/div/div"

try {
    List<WebElement> cells = WebUI.findWebElements(new TestObject().addProperty("xpath", ConditionType.EQUALS, cellsXPath), 10)

    if (!cells.isEmpty()) {
        WebUI.comment("Total cells found: " + cells.size())
        
        // Generate a random index
        Random random = new Random()
        int randomIndex = random.nextInt(cells.size())

        // Fetch the random cell and click it
        WebElement randomCell = cells.get(randomIndex)

        // Log information about the cell
        WebUI.comment("Attempting to click on cell with text: " + randomCell.getText())

        randomCell.click()
        WebUI.comment("Successfully clicked on a random cell.")

    } else {
        WebUI.comment("No cells found in the table.")
    }
} catch (org.openqa.selenium.StaleElementReferenceException e) {
    WebUI.comment("StaleElementReferenceException occurred. Consider re-fetching elements.")
} catch (Exception e) {
    WebUI.comment("An unexpected error occurred: " + e.getMessage())
}
WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="horizontal-note"]'))

WebUI.setText(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="note-textarea"]'), 'Test note')

WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="sched-dialog-save"]'))

//WebUI.verifyElementPresent(findTestObject('Object Repository/ScheduleAssignmentDialog/Page_Scheduler/svg_Aina Haina_size-5'), 
  //  0)
WebUI.closeBrowser()
