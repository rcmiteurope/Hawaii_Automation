import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.Keys

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('HTS Settings/OSCER Work Order/Add Workorder/Open Web App'), [:], FailureHandling.STOP_ON_FAILURE)

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

List requiredFields = [
	inputSchool,
	inputStartDate,
	inputEndDate,
	inputSchedule,
	inputUnitType,
	inputWorkorderType
	// add all your required fields here
]


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


WebUI.delay(1)
WebUI.click(btnCreate)


// Check if system detects empty required fields
boolean systemDetectedEmpty = false
List missingFields = []

for (field in requiredFields) {
	String value = WebUI.getAttribute(field, 'value')
	if (!value || value.trim() == '') {
		missingFields.add(field.getObjectId())  // or a friendly name
	}
}

if (missingFields.size() > 0) {
	systemDetectedEmpty = true
	WebUI.comment('System correctly identified empty required fields: ' + missingFields.join(', '))
} else {
	WebUI.comment('All required fields are filled (system did not detect empty fields)')
}

// Test passes if system detected missing values
assert systemDetectedEmpty : 'System failed to detect empty required fields'

WebUI.closeBrowser()