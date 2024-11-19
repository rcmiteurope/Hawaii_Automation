import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import databaseConnection.DatabaseConnection 
import groovy.sql.Sql
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory

// Parameters for fetching students
def islandID = 1 
def schoolID = 0
def providerID = 0

// Establish the SQL connection
Sql sql = DatabaseConnection.connectToDatabase()

List<String> expcetedStudents = []

try {
    // Query to retrieve expected students from the database
    String query = "CALL SP_HTS_GetStudents(?, ?, ?)"
	WebUI.comment("Calling stored procedure with islandID: `${islandID}` schoolID: `${schoolID}` and providerID: '${providerID}'")
	
    List<Map> rows = sql.rows(query, [islandID, schoolID, providerID])

	    // Check if any students were found
    if (rows.isEmpty()) {
        WebUI.comment("No students found for the provided parameters.")
    } else {
	// Log each school found
	rows.each { row ->
		WebUI.comment("Student found: " + row.firstname + row.lastname) // Adjust the column name as needed
	}

	// Assuming you have a method to get the dropdown options
	List<String> dropdownOptions = WebUI.getOptions(findTestObject('Object Repository/AddSchool/Page_Scheduler/select_SchoolDropdown'))

	// Normalize dropdown options for comparison
	List<String> normalizedDropdownOptions = dropdownOptions.collect { it.trim().toLowerCase() }
	
	// Check if all expected schools are present in the dropdown
	List<String> missingStudentss = []
	rows.each { row ->
		String firstname = row.firstname.trim().toLowerCase() 
		String lastname = row.lastname.trim().toLowerCase() // Normalize school name
		
		String fullName = "${lastName}, ${firstName}"
		
		if (!normalizedDropdownOptions.contains(fullName)) {
			missingSchools.add(fullName)
		}
	}
	
    if (missingStudents.isEmpty()) {
        WebUI.comment("Dropdown contains all the expected student options.")
    } else {
        WebUI.comment("Dropdown is missing the following students: " + missingStudents)
        throw new Exception("Test Failed: Dropdown is missing students: " + missingStudents)
    }
    }
} catch (Exception e) {
    WebUI.comment("An error occurred: " + e.message)
} finally {
    // Close the SQL connection and browser
    DatabaseConnection.closeConnection(sql)
}

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url)

// Get the WebDriver instance
WebDriver driver = DriverFactory.getWebDriver()

// Locate the school filter dropdown using its selector
WebElement studentFilterSelect = driver.findElement(By.xpath("//select[@id='student_filter_select']"))

// Get the dropdown options
List<WebElement> dropdownOptions = studentFilterSelect.findElements(By.tagName('option'))

// Collect the text values from the dropdown options
List<String> dropdownStudentNames = dropdownOptions.collect { it.getText().trim() }

// Log the options found in the dropdown
dropdownStudentNames.each { student ->
	WebUI.comment("Option found in dropdown: " + student)
}

// Verify that all expected schools are present in the dropdown
boolean allStudentsExist = true
List<String> missingStudents = []

expectedStudents.each { expectedStudent ->
	if (!dropdownStudentNames.contains(expectedStudent)) {
		allStudentsExist = false
		missingStudents.add(expectedStudent)
	}
}

// Log results of the verification
if (allStudentsExist) {
	WebUI.comment("Dropdown contains all the expected student options.")
} else {
	WebUI.comment("Dropdown is missing the following students: " + missingStudents)
	throw new Exception("Test Failed: Dropdown is missing the following students: " + missingStudents)
}

// Close the browser
WebUI.closeBrowser()
