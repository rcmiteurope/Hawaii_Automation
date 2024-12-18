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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.By as By
import java.util.Random as Random


WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6IkVyaWNhLkJvcnJvbWVvQHJjbXQuY29tIiwidXNlcklEIjo4LCJpYXQiOjE3MzE5ODYxMDEsImV4cCI6MTczNDU3ODEwMX0.AUWF2TrOJtXoWXnwJaA3MHQJ0iUgTpDUw2YrdjazB_Q')

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', 'Erica.Borromeo%40rcmt.com'))

driver.manage().addCookie(new Cookie('user_name', 'Borromeo%2C%20Erica'))

WebUI.refresh()

String cellsXPath = "//table[@id='horizontal-table']//tbody//td/div/div"

String randomCellXPath = ""

try {
	List<WebElement> cells = WebUI.findWebElements(new TestObject().addProperty("xpath", ConditionType.EQUALS, cellsXPath), 10)

	if (!cells.isEmpty()) {
		WebUI.comment("Total cells found: " + cells.size())
		
		// Generate a random index
		Random random = new Random()
		int randomIndex = random.nextInt(cells.size())

		// Fetch the random cell and store its XPath
		WebElement randomCell = cells.get(randomIndex)
		randomCellXPath = cellsXPath + "[" + (randomIndex + 1) + "]"

		// Log information about the cell
		WebUI.comment("Attempting to click on cell with text: " + randomCell.getText())

		randomCell.click()
		WebUI.comment("Successfully clicked on a random cell.")
	} else {
		WebUI.comment("No cells found in the table.")
	}
} catch (Exception e) {
	WebUI.comment("An error occurred while selecting the random cell: " + e.getMessage())
}

// Perform other actions
WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="provider-button"]'))
WebUI.click(findTestObject('Object Repository/ScheduleAssignmentDialog/Page_Scheduler/div_Provider_css-hlgwow'))
WebUI.click(findTestObject('Object Repository/ScheduleAssignmentDialog/Page_Scheduler/div_Abad, Jose'))
WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="sched-dialog-save"]'))


// Re-click the random cell
try {
	if (!randomCellXPath.isEmpty()) {
		WebElement reselectedCell = driver.findElement(By.xpath(randomCellXPath))
		WebUI.comment("Re-clicking the previously selected cell with text: " + reselectedCell.getText())
		reselectedCell.click()
		WebUI.comment("Successfully re-clicked the previously selected cell.")
	} else {
		WebUI.comment("Random cell XPath was not stored. Unable to re-click.")
	}
} catch (Exception e) {
	WebUI.comment("An error occurred while re-clicking the random cell: " + e.getMessage())
}


WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="supervisor-button"]'))
WebUI.click(findTestObject('Object Repository/ScheduleAssignmentDialog/Page_Scheduler/div_Supervisor_css-19bb58m'))
WebUI.click(findTestObject('Object Repository/ScheduleAssignmentDialog/Page_Scheduler/div_Cameros, Rachel'))
WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="sched-dialog-save"]'))

//WebUI.verifyElementText(findTestObject('Object Repository/ScheduleAssignmentDialog/Page_Scheduler/p_Cameros, Rachel'), 'Cameros, Rachel')

WebUI.closeBrowser()
