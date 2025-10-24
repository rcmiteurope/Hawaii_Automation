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
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject 
import com.kms.katalon.core.testobject.ConditionType 
import com.kms.katalon.core.testobject.TestObject 
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI 
import org.openqa.selenium.Keys 
import com.kms.katalon.core.model.FailureHandling 
import com.kms.katalon.core.util.KeywordUtil 
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase 
import java.time.LocalDate 
import java.time.format.DateTimeFormatter 
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS


// --- Open Web App ---
WebUI.callTestCase(findTestCase('Test Cases/HTS Settings/OSCER Work Order/Add Workorder/Open Web App'), [:], FailureHandling.STOP_ON_FAILURE)


// --- Define Test Objects ---
TestObject search = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/View Workorder/search')
TestObject addWorkorderBtn = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/View Workorder/add_Workorder_Button')
TestObject inputSchool = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Select School')
TestObject inputStartDate = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Start Date')
TestObject inputEndDate = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/End Date')
TestObject inputSchedule = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Schedule')
TestObject inputUnitType = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Unit Type')
TestObject checkBoxSupplemental = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Supplemental')
TestObject inputWorkorderType = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Workorder Type')
TestObject inputNotes = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Notes')

TestObject btnAddMon = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Schedule Details/Add Monday')
TestObject btnAddTue = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Schedule Details/Add Tuesday')
TestObject btnAddWed = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Schedule Details/Add Wednesday')
TestObject btnAddThu = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Schedule Details/Add Thursday')
TestObject btnAddFri = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Schedule Details/Add Friday')

TestObject btnRemMon = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Schedule Details/Remove Monday')
TestObject btnRemTue = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Remove Tueday')
TestObject btnRemWed = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Schedule Details/Remove Wednesday')
TestObject btnRemThu = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Schedule Details/Remove Thursday')
TestObject btnRemFri = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Schedule Details/Remove Friday')

TestObject btnCancel = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Cancel Button')
TestObject btnCreate = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Create Button')

// --- Workflow Execution ---
WebUI.delay(1)
WebUI.click(search)
WebUI.delay(1)
WebUI.sendKeys(search, Keys.chord(Keys.ENTER))
WebUI.delay(1)

WebUI.click(addWorkorderBtn)

// Select School
WebUI.click(inputSchool)
WebUI.sendKeys(inputSchool, Keys.chord(Keys.ENTER))

// Set invalid date range (Start Date > End Date)
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
String futureDate = LocalDate.now().plusDays(5).format(formatter)
String currentDate = LocalDate.now().format(formatter)

WebUI.setText(inputStartDate, futureDate)


// Select schedule
WebUI.click(inputSchedule)
WebUI.sendKeys(inputSchedule, Keys.chord(Keys.ENTER))

// Select Workorder Type
WebUI.click(inputWorkorderType)
WebUI.sendKeys(inputWorkorderType, Keys.chord(Keys.ENTER))

WebUI.click(inputUnitType)
WebUI.sendKeys(inputUnitType, Keys.chord(Keys.ENTER))


WebUI.setText(inputEndDate, currentDate)


// Click Create
WebUI.click(btnCreate)


if (WebUI.getText(inputEndDate).isEmpty()) {
    println("The start date is greater than the end date. The system is requiring the user to input the correct value.")
	KeywordUtil.markPassed("Test passed: End Date is empty as expected.")
	
	WebUI.delay(2)
	WebUI.closeBrowser()
} else {
    println("Test failed: The end date field is not empty.")
	KeywordUtil.markFailed("Test failed: End Date field is not empty.")
}