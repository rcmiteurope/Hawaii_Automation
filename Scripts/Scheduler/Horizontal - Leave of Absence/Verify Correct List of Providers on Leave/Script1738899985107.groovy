import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import groovy.sql.Sql
import internal.GlobalVariable
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import java.time.format.DateTimeFormatter
import databaseConnection.DatabaseConnection

// Database Connection
Sql sql = DatabaseConnection.connectToDatabase()

// Fetch Monday and Friday of the current week
LocalDate today = LocalDate.now()
LocalDate startDate = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
LocalDate endDate = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY))

// Query the database for provider leave details
String query = "CALL sp_HTS_Get_Providers_Leave_Details(?, ?, ?)"
int islandID = 9

List<Map<String, Object>> expectedRows = sql.rows(query, [startDate.toString(), endDate.toString(), islandID])

if (expectedRows.isEmpty()) {
    WebUI.comment("No provider leave details found for the given parameters.")
} else {
    WebUI.comment("Fetched provider leave details from the database.")
}

// Close the database connection
sql.close()

// Open browser and navigate to the application
WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.scheduler_url)

// Authenticate with cookies
WebDriver driver = DriverFactory.getWebDriver()
driver.manage().addCookie(new org.openqa.selenium.Cookie('sc_auth_token', GlobalVariable.sc_auth_token))
WebUI.refresh()

// Locate the table and extract rows
List<WebElement> tableRows = driver.findElements(By.xpath("//table[@id='horizontal-table']/tbody/tr"))

// Validate table data against the database response
expectedRows.eachWithIndex { expectedRow, index ->
    try {
        if (index < tableRows.size()) {
            WebElement currentRow = tableRows.get(index)

            // Extract actual data from the table
            WebElement dataCell = currentRow.findElement(By.xpath(".//td[last()]//div"))
            String actualProviderName = dataCell.getText().trim()
            String actualBackgroundColor = dataCell.getCssValue("background-color").trim()
            String actualForegroundColor = dataCell.getCssValue("color").trim()

            // Extract expected data from the database response
            String expectedProviderName = expectedRow.provider_name
            String expectedBackgroundColor = expectedRow.leave_dates[0].style.background
            String expectedForegroundColor = expectedRow.leave_dates[0].style.foreground

            // Verify data matches
            WebUI.verifyMatch(actualProviderName, expectedProviderName, false, FailureHandling.CONTINUE_ON_FAILURE)
            WebUI.verifyMatch(actualBackgroundColor, expectedBackgroundColor, false, FailureHandling.CONTINUE_ON_FAILURE)
            WebUI.verifyMatch(actualForegroundColor, expectedForegroundColor, false, FailureHandling.CONTINUE_ON_FAILURE)

            println("Row ${index + 1} matches:")
            println("  Provider Name: ${actualProviderName}")
            println("  Background Color: ${actualBackgroundColor}")
            println("  Foreground Color: ${actualForegroundColor}")
        } else {
            println("Row ${index + 1} does not exist in the table.")
        }
    } catch (Exception e) {
        println("Error validating row ${index + 1}: ${e.message}")
    }
}

// Close the browser
WebUI.closeBrowser()
