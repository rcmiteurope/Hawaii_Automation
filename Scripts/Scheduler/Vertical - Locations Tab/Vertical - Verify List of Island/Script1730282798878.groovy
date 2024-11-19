import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import databaseConnection.DatabaseConnection // Replace this with your database connection class
import groovy.sql.Sql
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import internal.GlobalVariable as GlobalVariable

// Establish database connection
Sql sql = DatabaseConnection.connectToDatabase() // Use your actual database connection method

// Initialize an empty list to hold expected locations from the database
List<String> expectedLocations = []

try {
	// Query to fetch the expected islands from the database
	String query = "SELECT islandName FROM vw_scheduler_getislands"

	// Execute the query and store results in expectedLocations
	List<Map> rows = sql.rows(query)
	rows.each { row ->
		expectedLocations.add(row.islandName.trim()) // Using the `islandName` column
	}

	// Print the expected islands retrieved from the database
	WebUI.comment("Expected locations from the database: " + expectedLocations)
	
} catch (Exception e) {
	WebUI.comment("An error occurred while fetching expected islands: " + e.message)
} finally {
	// Close the SQL connection
	DatabaseConnection.closeConnection(sql)
}

// Open browser and navigate to the page (replace with your URL)
WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.scheduler_url)

// Get the WebDriver instance
WebDriver driver = DriverFactory.getWebDriver()

// Locate the parent container for location tabs
WebElement locationsContainer = driver.findElement(By.id("locations"))

// Initialize a list to store found location texts
List<String> foundLocations = []

// Loop through tabs with IDs in the format "tab-1", "tab-2", etc.
for (int i = 1; ; i++) {
    try {
        WebElement tab = locationsContainer.findElement(By.id("tab-" + i))
        String locationText = tab.getText().trim()
        foundLocations.add(locationText)
        
        // Log each location tab found
        WebUI.comment("Found location tab: " + locationText)
        
        // Click each tab to verify it can be interacted with (optional)
        WebUI.delay(1) // Add delay to simulate user interaction
    } catch (org.openqa.selenium.NoSuchElementException e) {
        // Exit loop if no more tabs are found
        break
    }
}

// Check if all expected locations are present in the found locations
boolean allLocationsPresent = expectedLocations.every { it in foundLocations }

if (allLocationsPresent) {
    WebUI.comment("All expected locations are present in the locations tab.")
} else {
    List<String> missingLocations = expectedLocations.findAll { !(it in foundLocations) }
    WebUI.comment("Missing locations in the tab: " + missingLocations)
    throw new Exception("Test Failed: Missing expected locations in the locations tab.")
}

// Close the browser
WebUI.closeBrowser()