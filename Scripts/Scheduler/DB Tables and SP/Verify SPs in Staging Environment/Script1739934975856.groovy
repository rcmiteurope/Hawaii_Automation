import groovy.sql.Sql
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.testdata.ExcelData
import com.kms.katalon.core.testdata.reader.ExcelFactory
import databaseConnection.DatabaseConnection
import com.kms.katalon.core.configuration.RunConfiguration

// Get project directory dynamically
String projectDir = RunConfiguration.getProjectDir()

// Use a relative path from the project root
String excelFilePath = projectDir + "/Data Files/db_client_services_qa.xlsx"

ExcelData excelData = ExcelFactory.getExcelDataWithDefaultSheet(excelFilePath, "sp_list", true)

List<String> expectedSPs = []

for (int row = 1; row <= excelData.getRowNumbers(); row++) { 
String spName = excelData.getValue(1, row) 
    if (spName) {
        expectedSPs.add(spName.trim()) 
    }
}

KeywordUtil.logInfo("Stored Procedures from Excel: " + expectedSPs)

// Connect to the database
Sql sql = DatabaseConnection.connectToDatabase()

// Fetch stored procedures from the database
List<String> actualSPs = []
try {
    def results = sql.rows("SELECT ROUTINE_NAME FROM INFORMATION_SCHEMA.ROUTINES WHERE ROUTINE_TYPE = 'PROCEDURE' AND ROUTINE_SCHEMA = 'client_services' ORDER BY ROUTINE_NAME;")
    results.each { row ->
        actualSPs.add(row.ROUTINE_NAME)
    }
    
    // Print all stored procedures found in the database
    KeywordUtil.logInfo("Stored Procedures found in Staging DB: " + actualSPs)
    
} catch (Exception e) {
    KeywordUtil.markFailed("Database Query Failed: " + e.message)
}

// Find missing stored procedures (expected but not found in DB)
List<String> missingSPs = expectedSPs - actualSPs

// Find extra stored procedures (found in DB but not in expected list)
List<String> extraSPs = actualSPs - expectedSPs

// Log the comparison results
if (missingSPs.isEmpty() && extraSPs.isEmpty()) {
    KeywordUtil.markPassed("All stored procedures match the expected list.")
} else {
   String errorMessage = ""

    if (!missingSPs.isEmpty()) {
        errorMessage += "Missing Stored Procedures in Staging: " + missingSPs + "\n"
    }
    if (!extraSPs.isEmpty()) {
        errorMessage += "Extra Stored Procedures Found in Staging DB: " + extraSPs + "\n"
    }

    KeywordUtil.markFailed(errorMessage)
}


// Close database connection
DatabaseConnection.closeConnection(sql)