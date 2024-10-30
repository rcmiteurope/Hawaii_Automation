import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import databaseConnection.DatabaseConnection 
import groovy.sql.Sql
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

def islandID = 1 
def providerID = null

Sql sql = DatabaseConnection.connectToDatabase()

List<String> expectedSchools = []


try {
    // SQL query to retrieve expected schools from the database
    String query = "CALL sp_scheduler_getSchools(?, ?)"
    
   // WebUI.comment("Calling stored procedure with islandID: ${islandID} and providerID: '${providerID}'")
    
    List<Map> rows = sql.rows(query, [islandID, providerID]) // Using named parameters

    // Process the results
    if (rows.isEmpty()) {
        WebUI.comment("No schools found for the provided parameters.")
    } else {
        // Log each school found
        rows.each { row ->
            WebUI.comment("Student found: " + row.schoolName) // Adjust the column name as needed
        }

        // Assuming you have a method to get the dropdown options
        List<String> dropdownOptions = WebUI.getOptions(findTestObject('Object Repository/AddSchool/Page_Scheduler/select_SchoolDropdown'))

        // Normalize dropdown options for comparison
        List<String> normalizedDropdownOptions = dropdownOptions.collect { it.trim().toLowerCase() }
        
        // Check if all expected schools are present in the dropdown
        List<String> missingSchools = []
        rows.each { row ->
            String schoolName = row.schoolName.trim().toLowerCase() // Normalize school name
            if (!normalizedDropdownOptions.contains(schoolName)) {
                missingSchools.add(row.schoolName)
            }
        }

        if (!missingSchools.isEmpty()) {
            throw new Exception("Dropdown is missing the following schools: " + missingSchools)
        } else {
            WebUI.comment("All expected schools are present in the dropdown.")
        }
    }
} catch (Exception e) {
    WebUI.comment("An error occurred: " + e.message)
} finally {
    // Close the SQL connection
    DatabaseConnection.closeConnection(sql)
}

// Now verify the dropdown has the expected schools
WebUI.openBrowser('') 

WebUI.navigateToUrl(GlobalVariable.scheduler_url)

// Get the WebDriver instance
WebDriver driver = DriverFactory.getWebDriver()

// Locate the school filter dropdown using its selector
WebElement schoolFilterSelect = driver.findElement(By.xpath("//select[@id='school_filter_select']")) 

schoolFilterSelect.click();

// Get the dropdown options
List<WebElement> dropdownOptions = schoolFilterSelect.findElements(By.tagName('option'))

// Collect the text values from the dropdown options
List<String> dropdownSchoolNames = dropdownOptions.collect { it.getText().trim() }

// Log the options found in the dropdown
dropdownSchoolNames.each { school ->
    WebUI.comment("Option found in dropdown: " + school)
}

// Verify that all expected schools are present in the dropdown
boolean allSchoolsExist = true
List<String> missingSchools = []

expectedSchools.each { expectedSchool ->
    if (!dropdownSchoolNames.contains(expectedSchool)) {
        allSchoolsExist = false
        missingSchools.add(expectedSchool)
    }
}

// Log results of the verification
if (allSchoolsExist) {
    WebUI.comment("Dropdown contains all the expected school options.")
} else {
    WebUI.comment("Dropdown is missing the following schools: " + missingSchools)
    throw new Exception("Test Failed: Dropdown is missing the following schools: " + missingSchools)
}

// Close the browser
WebUI.closeBrowser()
