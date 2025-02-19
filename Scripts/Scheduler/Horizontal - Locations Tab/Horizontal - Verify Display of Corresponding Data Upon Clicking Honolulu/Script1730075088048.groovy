//import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
//import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
//import databaseConnection.DatabaseConnection
//import groovy.sql.Sql
//import internal.GlobalVariable as GlobalVariable
//import org.openqa.selenium.By
//import org.openqa.selenium.WebDriver
//import org.openqa.selenium.WebElement
//import com.kms.katalon.core.webui.driver.DriverFactory
//import org.openqa.selenium.support.ui.WebDriverWait
//import org.openqa.selenium.support.ui.ExpectedConditions
//import java.time.Duration
//import org.openqa.selenium.Cookie as Cookie
//
//// Open browser and navigate to the scheduler URL
//WebUI.openBrowser('')
//WebUI.navigateToUrl(GlobalVariable.scheduler_url)
//
//WebDriver driver = DriverFactory.getWebDriver()
//
//// Set authentication cookies
//driver.manage().addCookie(new Cookie('sc_auth_token', GlobalVariable.sc_auth_token))
//driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email))
//driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name))
//
//// Refresh the page to apply cookies
//WebUI.refresh()
//
//// Variables for the test
//def islandID = 1
//List<String> expectedSchools = []
//
//try {
//	// Step 1: Check if tab-1 exists and is active
//	WebElement tab1 = driver.findElement(By.id("tab-1"))
//
//	if (tab1 != null && tab1.getAttribute("style").contains("background-color: lightskyblue")) {
//		WebUI.comment("Tab-1 is active. Proceeding with school verification for islandID: " + islandID)
//
//		// Step 2: Connect to the database and retrieve the expected schools for the active islandID
//		Sql sql = DatabaseConnection.connectToDatabase()
//		try {
//			String query = "CALL sp_scheduler_getSchools(?, ?)"
//			List<Map> rows = sql.rows(query, [islandID, null]) // Assuming `null` for providerID
//			rows.each { row ->
//				expectedSchools.add(row.schoolName.trim()) // Adjust column name if needed
//			}
//			WebUI.comment("Expected schools for islandID " + islandID + ": " + expectedSchools)
//		} catch (Exception e) {
//			WebUI.comment("An error occurred while fetching schools: " + e.message)
//		} finally {
//			DatabaseConnection.closeConnection(sql)
//		}
//
//		// Step 3: Retrieve the options in the school dropdown on the webpage
//		WebElement schoolFilterSelect = driver.findElement(By.xpath("//select[@id='school_filter_select']"))
//		List<WebElement> dropdownOptions = schoolFilterSelect.findElements(By.tagName("option"))
//		List<String> dropdownSchoolNames = dropdownOptions.collect { it.getText().trim() }
//
//		// Log the options found in the dropdown for debugging
//		WebUI.comment("Schools found in dropdown: " + dropdownSchoolNames)
//
//		// Step 4: Verify that all expected schools are present in the dropdown
//		boolean allSchoolsExist = expectedSchools.every { it in dropdownSchoolNames }
//		List<String> missingSchools = expectedSchools.findAll { !(it in dropdownSchoolNames) }
//
//		if (allSchoolsExist) {
//			WebUI.comment("Dropdown contains all the expected school options for islandID " + islandID + ".")
//		} else {
//			WebUI.comment("Dropdown is missing the following schools for islandID " + islandID + ": " + missingSchools)
//			throw new Exception("Test Failed: Dropdown is missing expected schools.")
//			}
//		} else {
//		WebUI.comment("Tab-1 is not active or not styled as expected. Skipping school verification for islandID: " + islandID)
//		}
//	} catch (org.openqa.selenium.TimeoutException e) {
//	WebUI.comment("Error: Tab-1 was not found within the specified timeout.")
//	} catch (Exception e) {
//		WebUI.comment("Unexpected error: " + e.message)
//	} finally {
//	WebUI.delay(5)
//	
//	// Close the browser
//	WebUI.closeBrowser()
//}
//
//// Step 6: Close the browser
//WebUI.closeBrowser()

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.Cookie
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import groovy.sql.Sql
import databaseConnection.DatabaseConnection
import internal.GlobalVariable
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.kms.katalon.core.model.FailureHandling
 
Sql sql = DatabaseConnection.connectToDatabase()
 
List<String> expectedProviders = []
 
String query = "CALL sp_HTS_GetSchedules(?,?,?,?,?,?,?,?)"
int island = 1
int filter_type = 0
def status =  null
LocalDate week_start = LocalDate.now()
LocalDate week_end = LocalDate.now()
def month = null
def day = null
def providerID = null
 
List<Map> expectedRows = sql.rows(query, [island, filter_type, status, week_start, week_end, month, day, providerID ])
 
if (expectedRows.isEmpty()) {
	WebUI.comment("No schedule found for the given parameters.")
} else {
	expectedRows.each { row ->
		  println("Row data: " + row)
 
	}
}
 
DatabaseConnection.closeConnection(sql)
 
WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.scheduler_url)
 
WebDriver driver = DriverFactory.getWebDriver()
driver.manage().addCookie(new Cookie('sc_auth_token', GlobalVariable.sc_auth_token))
driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email))
driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name))
 
WebUI.refresh()
 
// Click on 'Honolulu' tab
WebUI.click(
	new TestObject("tab1")
		.addProperty("xpath", ConditionType.EQUALS, "//*[@id='tab-1']")
)
 
List<WebElement> rows = driver.findElements(By.xpath("//table[@id='vertical-table']/tbody/tr[position()>1]"))
 
// 3. Validate each expected row against the corresponding table row
expectedRows.eachWithIndex { expectedRow, rowIndex ->
	try {
		// Ensure the row index exists within the table rows
		if (rowIndex < rows.size()) {
			// Get the current table row
			WebElement row = rows.get(rowIndex)
 
			// Locate specific columns within the table row
			List<WebElement> cells = row.findElements(By.tagName("td"))
			if (cells.size() >= 3) {  // Ensure the row has enough columns
				// Extract actual values from the table
				String actualSchoolName = cells.get(0).getText().trim()  // First column
				String actualStudentName = cells.get(1).getText().trim()  // Second column
				String actualDateTimeText = cells.get(2).getText().trim()  // Third column (e.g., "Mon, 01/27 06:00 - 16:15")
 
				// Parse the date/time column
				String[] parts = actualDateTimeText.split(' ')
				String actualMD = parts[1]             // "01/27"
				String actualStartTime = parts[2]      // "06:00"
				String actualEndTime = parts[4]        // "16:15"
 
				// Extract expected values from the current expectedRow
				String expectedSchoolName = expectedRow.schoolName.trim()
				String expectedStudentName = expectedRow.studentName.trim()
				String scheduledDate = expectedRow.scheduledDate  // "2025-01-27"
				String expectedStartTime = expectedRow.startTime
				String expectedEndTime = expectedRow.endTime
				String expectedMD = scheduledDate.substring(5, 7) + '/' + scheduledDate.substring(8, 10)  // "01/27"
 
				 // Compare values
				boolean isMatch = actualSchoolName == expectedSchoolName &&
								  actualStudentName == expectedStudentName &&
								  actualMD == expectedMD &&
								  actualStartTime == expectedStartTime &&
								  actualEndTime == expectedEndTime
 
				if (isMatch) {
					println("Match found in Row ${rowIndex + 1}:")
					println("  School Name: ${actualSchoolName}")
					println("  Student Name: ${actualStudentName}")
					println("  Date: ${actualMD}")
					println("  Start Time: ${actualStartTime}")
					println("  End Time: ${actualEndTime}")
				} else {
					println("No match in Row ${rowIndex + 1}")
				}
 
				WebUI.verifyMatch(actualSchoolName, expectedSchoolName, false, FailureHandling.CONTINUE_ON_FAILURE)
				WebUI.verifyMatch(actualStudentName, expectedStudentName, false, FailureHandling.CONTINUE_ON_FAILURE)
				WebUI.verifyMatch(actualMD, expectedMD, false, FailureHandling.CONTINUE_ON_FAILURE)
				WebUI.verifyMatch(actualStartTime, expectedStartTime, false, FailureHandling.CONTINUE_ON_FAILURE)
				WebUI.verifyMatch(actualEndTime, expectedEndTime, false, FailureHandling.CONTINUE_ON_FAILURE)
			} else {
				println("Row ${rowIndex + 1} does not have enough columns. Skipping this row.")
			}
		} else {
			println("Row ${rowIndex + 1} in expectedRows does not have a corresponding table row. Skipping.")
		}
	} catch (Exception e) {
		println("Error processing row ${rowIndex + 1}: ${e.message}")
	}
}
WebUI.closeBrowser()
