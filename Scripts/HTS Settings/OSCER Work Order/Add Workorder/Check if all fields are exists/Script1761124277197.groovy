import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Keys
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

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

TestObject btnAddMon = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Schedule Details/Add Monday')
TestObject btnAddTue = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Schedule Details/Add Tuesday')
TestObject btnAddWed = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Schedule Details/Add Wednesday')
TestObject btnAddThu = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Schedule Details/Add Thursday')
TestObject btnAddFri = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Schedule Details/Add Friday')
TestObject btnRemMon = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Schedule Details/Remove Monday')
TestObject btnRemTue = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Remove Tueday')
TestObject btnRemWed = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Schedule Details/Remove Wednesday')
TestObject btnRemThu = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Schedule Details/Remove Thursday')
TestObject btnRemFri = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Schedule Details/Remove Friday')
TestObject btnCancel = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Cancel Button')
TestObject btnCreate = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Create Button')

TestObject inputStartMon = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Schedule Details/Start Time Mon')
TestObject inputStartTue = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Schedule Details/Start Time Tue')
TestObject inputStartWed = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Schedule Details/Start Time Wed')
TestObject inputStartThu = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Schedule Details/Start Time Thu')
TestObject inputStartFri = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Schedule Details/Start Time Fri')
TestObject inputEndMon = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Schedule Details/End Time Mon')
TestObject inputEndTue = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Schedule Details/End Time Tue')
TestObject inputEndWed = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Schedule Details/End Time Wed')
TestObject inputEndThu = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Schedule Details/End Time Thu')
TestObject inputEndFri = findTestObject('Object Repository/HTS Settings/OSCER Workorder/Add Workorder/Schedule Details/End Time Fri')


WebUI.delay(1)
WebUI.click(search)

WebUI.delay(1)

WebUI.setText(search, 'Nya Jay-Funaki')

WebUI.sendKeys(search, Keys.chord(Keys.ENTER))

WebUI.delay(1)
WebUI.click(addWorkorderBtn)

//================= Validations =======================
// Check if the fields exists
List<TestObject> requiredFields = [
	inputSchool,
	inputStartDate,
	inputEndDate,
	inputSchedule,
	inputUnitType,
	checkBoxSupplemental,
	inputWorkorderType,
	inputNotes,
	btnAddMon,
	btnAddTue,
	btnAddWed,
	btnAddThu,
	btnAddFri,
	btnRemMon,
	btnRemTue,
	btnRemWed,
	btnRemThu,
	btnRemFri,
	btnCancel,
	btnCreate,
    inputStartMon,
    inputStartTue,
    inputStartWed,
    inputStartThu,
    inputStartFri,
    inputEndMon,
    inputEndTue,
    inputEndWed,
    inputEndThu,
    inputEndFri
]

List<String> missingFields = []



for (TestObject field : requiredFields) {
	if (field == null) {
		println("⚠️ Skipping null TestObject reference.")
		continue
	}

	String fullName = field.getObjectId()
	String shortName = fullName.tokenize('/').last()  // get last part only (e.g., “Select School”)

	if (WebUI.verifyElementPresent(field, 5, FailureHandling.OPTIONAL)) {
		println("Field exists: " + shortName)
	} else {
		println("Field missing: " + shortName)
		missingFields.add(shortName)
	}
}

if (missingFields.isEmpty()) {
	println("All required fields are present.")
} else {
	println("Missing fields detected:")
	missingFields.each { println("   - " + it) }
	KeywordUtil.markFailed("Test failed. Missing fields: " + missingFields.join(', '))
}
//
WebUI.delay(2)
WebUI.closeBrowser()

