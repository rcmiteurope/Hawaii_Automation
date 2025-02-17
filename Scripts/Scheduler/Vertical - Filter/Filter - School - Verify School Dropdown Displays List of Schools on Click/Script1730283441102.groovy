import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import databaseConnection.DatabaseConnection 
import groovy.sql.Sql
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.Cookie as Cookie
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject

def islandID = 1 
def providerID = null

Sql sql = DatabaseConnection.connectToDatabase()

List<String> expectedSchools = []


try {
    String query = "CALL sp_scheduler_getSchools(?, ?)"
    
    
    List<Map> rows = sql.rows(query, [islandID, providerID]) 

    if (rows.isEmpty()) {
        WebUI.comment("No schools found for the provided parameters.")
    } else {
        rows.each { row ->
            WebUI.comment("Student found: " + row.schoolName) 
        }

        List<String> dropdownOptions = WebUI.getOptions(findTestObject('Object Repository/AddSchool/Page_Scheduler/select_SchoolDropdown'))

        List<String> normalizedDropdownOptions = dropdownOptions.collect { it.trim().toLowerCase() }
        
        List<String> missingSchools = []
        rows.each { row ->
            String schoolName = row.schoolName.trim().toLowerCase() 
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
    DatabaseConnection.closeConnection(sql)
}

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', GlobalVariable.sc_auth_token)

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name))

WebUI.refresh()


WebElement schoolFilterSelect = driver.findElement(By.xpath("//select[@id='school_filter_select']")) 

schoolFilterSelect.click();

List<WebElement> dropdownOptions = schoolFilterSelect.findElements(By.tagName('option'))

List<String> dropdownSchoolNames = dropdownOptions.collect { it.getText().trim() }

dropdownSchoolNames.each { school ->
    WebUI.comment("Option found in dropdown: " + school)
}

boolean allSchoolsExist = true
List<String> missingSchools = []

expectedSchools.each { expectedSchool ->
    if (!dropdownSchoolNames.contains(expectedSchool)) {
        allSchoolsExist = false
        missingSchools.add(expectedSchool)
    }
}

if (allSchoolsExist) {
    WebUI.comment("Dropdown contains all the expected school options.")
} else {
    WebUI.comment("Dropdown is missing the following schools: " + missingSchools)
    throw new Exception("Test Failed: Dropdown is missing the following schools: " + missingSchools)
}

// Close the browser
WebUI.closeBrowser()
