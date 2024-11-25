import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import databaseConnection.DatabaseConnection 
import groovy.sql.Sql
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.Cookie as Cookie
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions

// Parameters for fetching students
def islandID = 1 
def schoolID = 0
def providerID = 0

// Establish the SQL connection
Sql sql = DatabaseConnection.connectToDatabase()

List<String> expectedStudents = [] // Correctly initialize the list

try {
    // Query to retrieve expected students from the database
    String query = "CALL SP_HTS_GetStudents(?, ?, ?)"
    WebUI.comment("Calling stored procedure with islandID: ${islandID}, schoolID: ${schoolID}, and providerID: ${providerID}")
    
    List<Map> rows = sql.rows(query, [islandID, schoolID, providerID])

    // Check if any students were found
    if (rows.isEmpty()) {
        WebUI.comment("No students found for the provided parameters.")
    } else {
        // Log each student found
        rows.each { row ->
            String fullName = "${row.lastname}, ${row.firstname}"
            WebUI.comment("Student found: " + fullName)
            expectedStudents.add(fullName.trim().toLowerCase()) // Add to the expected list
        }
    }
} catch (Exception e) {
    WebUI.comment("An error occurred while fetching students: " + e.message)
} finally {
    DatabaseConnection.closeConnection(sql)
}

// Open the browser and navigate to the URL
WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.scheduler_url)

// Add authentication cookies
WebDriver driver = DriverFactory.getWebDriver()
Cookie authCookie = new Cookie('sc_auth_token', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6IkVyaWNhLkJvcnJvbWVvQHJjbXQuY29tIiwidXNlcklEIjo4LCJpYXQiOjE3MzE5ODYxMDEsImV4cCI6MTczNDU3ODEwMX0.AUWF2TrOJtXoWXnwJaA3MHQJ0iUgTpDUw2YrdjazB_Q')driver.manage().addCookie(new Cookie('user_email', 'Erica.Borromeo%40rcmt.com'))
driver.manage().addCookie(new Cookie('user_name', 'Borromeo%2C%20Erica'))
driver.manage().addCookie(new Cookie('user_email', 'Erica.Borromeo%40rcmt.com'))

// Refresh the page
WebUI.refresh()

// Locate the student filter dropdown using its selector
WebElement studentFilterSelect = new WebDriverWait(driver, 10).until(
    ExpectedConditions.visibilityOfElementLocated(By.id("student_filter_select"))
)

// Wait for the options in the dropdown to be loaded
new WebDriverWait(driver, 10).until(
    ExpectedConditions.presenceOfNestedElementsLocatedBy(
        By.id("student_filter_select"), By.tagName("option")
    )
)

// Get the dropdown options
List<WebElement> dropdownOptions = studentFilterSelect.findElements(By.tagName('option'))

// Collect the text values from the dropdown options
List<String> dropdownStudentNames = dropdownOptions.collect { it.getText().trim().toLowerCase() }

// Log the options found in the dropdown
dropdownStudentNames.each { student ->
    WebUI.comment("Option found in dropdown: " + student)
}

// Verify that all expected students are present in the dropdown
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
