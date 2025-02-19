import java.sql.*
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.testdata.DBData
import com.kms.katalon.core.testdata.reader.DBDataReader
import internal.GlobalVariable as GlobalVariable

// Database credentials (modify accordingly)
String dbUrl = 'jdbc:mysql://your-db-host:3306/your_database'  // Change to your DB
String dbUser = 'your_username'   // Change to your DB username
String dbPassword = 'your_password'  // Change to your DB password
String dbDriver = 'com.mysql.cj.jdbc.Driver'  // Use correct driver for your DB

// Expected columns for each table (Modify per your schema)
Map<String, List<String>> expectedSchema = [
    'students'      : ['id', 'name', 'age', 'email'],
    'teachers'      : ['id', 'name', 'subject', 'email'],
    'courses'       : ['id', 'course_name', 'description', 'duration']
]

@Keyword
def verifyDBTablesColumns() {
    Connection connection = null
    try {
        // Establish connection
        Class.forName(dbDriver)
        connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)
        Statement statement = connection.createStatement()

        // Loop through each expected table
        expectedSchema.each { table, expectedColumns ->
            List<String> actualColumns = []

            // Retrieve actual column names from DB
            ResultSet rs = statement.executeQuery("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '${table}'")
            while (rs.next()) {
                actualColumns.add(rs.getString("COLUMN_NAME"))
            }
            rs.close()

            // Verify the column names match
            if (actualColumns.containsAll(expectedColumns) && expectedColumns.containsAll(actualColumns)) {
                KeywordUtil.markPassed("✅ Table '${table}' matches expected schema.")
            } else {
                KeywordUtil.markFailed("❌ Table '${table}' does NOT match expected schema! \nExpected: ${expectedColumns} \nFound: ${actualColumns}")
            }
        }
    } catch (Exception e) {
        KeywordUtil.markFailed("❌ Database error: ${e.message}")
    } finally {
        if (connection != null) {
            connection.close()
        }
    }
}

// Call the function to run the verification
verifyDBTablesColumns()
