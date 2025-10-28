import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import org.openqa.selenium.Keys

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.callTestCase(findTestCase('Test Cases/HTS Settings/OSCER Work Order/Add Workorder/Open Web App'),[:], FailureHandling.STOP_ON_FAILURE)


// =================== DECLARE TEST OBJECTS ===================

TestObject search = findTestObject('Object Repository/HTS Settings/OSCER Workorder/View Workorder/search')
TestObject addWorkorderBtn = findTestObject('Object Repository/HTS Settings/OSCER Workorder/View Workorder/add_Workorder_button')


TestObject inputSchool = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Select School')
TestObject inputStartDate = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Start Date')
TestObject inputEndDate = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/End Date')
TestObject inputSchedule = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Schedule')
TestObject inputUnitType = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Unit Type')
TestObject checkBoxSupplemental = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Supplemental')
TestObject inputWorkorderType = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Workorder Type')
TestObject inputNotes = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Notes')

TestObject inputStartMon = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Schedule Details/Start Time Mon')
TestObject inputEndMon = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Schedule Details/End Time Mon')

TestObject btnCreate = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Create Button')


List requiredFields = [
	inputSchool,
	inputStartDate,
	inputEndDate,
	inputSchedule,
	inputUnitType,
	inputWorkorderType
	// add all your required fields here
]


DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
String futureDate = LocalDate.now().plusDays(5).format(formatter)
String currentDate = LocalDate.now().format(formatter)


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

// Input start and end date
WebUI.setText(inputStartDate, currentDate)
WebUI.setText(inputEndDate, futureDate)

// Select schedule
WebUI.click(inputSchedule)
WebUI.sendKeys(inputSchedule, Keys.chord(Keys.ENTER))


// Select Workorder Type
WebUI.click(inputWorkorderType)
WebUI.sendKeys(inputWorkorderType, Keys.chord(Keys.ENTER))

// Select Unit Type
WebUI.click(inputUnitType)
WebUI.sendKeys(inputUnitType, Keys.chord(Keys.ENTER))

// Input Notes
WebUI.setText(inputNotes,'Test Schedule Day Without Time')

boolean isDisabled = WebUI.getAttribute(btnCreate, 'disabled') != null

if (!isDisabled) {
	WebUI.comment('Create button is ENABLED even though no time is entered.')
	assert false : 'Create button should be disabled as no time was entered.'
} else {
	WebUI.comment('Create button is correctly DISABLED when no time is entered.')
}

// Set Start Time for Monday
WebUI.setText(inputStartMon, '06:00')
WebUI.sendKeys(inputStartMon, Keys.chord(Keys.ENTER))

// Set End Time for Monday
WebUI.setText(inputEndMon, '12:00')
WebUI.sendKeys(inputEndMon, Keys.chord(Keys.ENTER))



//WebUI.delay(1)
//WebUI.click(btnCreate)
