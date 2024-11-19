import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import databaseConnection.DatabaseConnection
import groovy.sql.Sql
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory

// Open browser and navigate to the scheduler URL
WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

// Variables for the test
def islandID = 1
List<String> expectedSchools = []

// Step 1: Check if tab-1 is active
WebElement tab1 = driver.findElement(By.id("tab-1"))
if (tab1.getAttribute("style").contains("background-color: lightskyblue")) {
    WebUI.comment("Tab-1 is active. Proceeding with school verification for islandID: " + islandID)

    // Step 2: Connect to the database and retrieve the expected schools for the active islandID
    Sql sql = DatabaseConnection.connectToDatabase()
    try {
        String query = "CALL sp_scheduler_getSchools(?, ?)"
        List<Map> rows = sql.rows(query, [islandID, null]) // Assuming `null` for providerID
        rows.each { row ->
            expectedSchools.add(row.schoolName.trim()) // Adjust column name if needed
        }
        WebUI.comment("Expected schools for islandID " + islandID + ": " + expectedSchools)
    } catch (Exception e) {
        WebUI.comment("An error occurred while fetching schools: " + e.message)
    } finally {
        DatabaseConnection.closeConnection(sql)
    }

    // Step 3: Retrieve the options in the school dropdown on the webpage
    WebElement schoolFilterSelect = driver.findElement(By.xpath("//select[@id='school_filter_select']"))
    List<WebElement> dropdownOptions = schoolFilterSelect.findElements(By.tagName("option"))
    List<String> dropdownSchoolNames = dropdownOptions.collect { it.getText().trim() }

    // Log the options found in the dropdown for debugging
    WebUI.comment("Schools found in dropdown: " + dropdownSchoolNames)

    // Step 4: Verify that all expected schools are present in the dropdown
    boolean allSchoolsExist = expectedSchools.every { it in dropdownSchoolNames }
    List<String> missingSchools = expectedSchools.findAll { !(it in dropdownSchoolNames) }

    if (allSchoolsExist) {
        WebUI.comment("Dropdown contains all the expected school options for islandID " + islandID + ".")
    } else {
        WebUI.comment("Dropdown is missing the following schools for islandID " + islandID + ": " + missingSchools)
        throw new Exception("Test Failed: Dropdown is missing expected schools.")
    }

} else {
    WebUI.comment("Tab-1 is not active. Skipping school verification for islandID: " + islandID)
}

// Close the browser
WebUI.closeBrowser()
