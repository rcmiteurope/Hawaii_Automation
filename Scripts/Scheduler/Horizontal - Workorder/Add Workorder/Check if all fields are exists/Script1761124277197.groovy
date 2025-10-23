import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Keys
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase

WebUI.callTestCase(findTestCase('Test Cases/Scheduler/Horizontal - Workorder/Add Workorder/Open Web App'),[:], FailureHandling.STOP_ON_FAILURE)

// =================== DECLARE TEST OBJECTS ===================

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
TestObject btnAddMon = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Add Monday')
TestObject btnAddTue = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Add Tuesday')
TestObject btnAddWed = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Add Wednesday')
TestObject btnAddThu = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Add Thursday')
TestObject btnAddFri = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Add Friday')
TestObject btnRemMon = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Remove Monday')
TestObject btnRemTue = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Remove Tueday')
TestObject btnRemWed = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Remove Wednesday')
TestObject btnRemThu = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Remove Thursday')
TestObject btnRemFri = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Remove Friday')
TestObject btnCancel = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Cancel Button')
TestObject btnCreate = findTestObject('Object Repository/Horizontal/Page_Scheduler/Workorder/Add Workorder/Create Button')



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
	btnCreate
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
		println("✅ Field exists: " + shortName)
	} else {
		println("❌ Field missing: " + shortName)
		missingFields.add(shortName)
	}
}

if (missingFields.isEmpty()) {
	println("All required fields are present.")
} else {
	println("Missing fields detected:")
	missingFields.each { println("   - " + it) }
	KeywordUtil.markFailed("❌ Test failed. Missing fields: " + missingFields.join(', '))
}
//
WebUI.delay(2)
WebUI.closeBrowser()

