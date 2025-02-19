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

Sql sql = DatabaseConnection.connectToDatabase()

List<String> expectedProviders = []

String query = "CALL sp_hts_providerDetails(?, ?, ?, ?)"
int limit = 0
int page = 1
int initial = 0
int districtID = 10

List<Map> rows = sql.rows(query, [page, limit, initial, districtID])

if (rows.isEmpty()) {
    WebUI.comment("No providers found for the given parameters.")
} else {
    rows.each { row ->
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

// Check the Horizontal Toggle checkbox
WebUI.check(
    new TestObject("toggleObj")
        .addProperty("xpath", ConditionType.EQUALS, "//*[@id='root']/main/div[2]/div[1]/div[1]/div/div")
)

// Click on 'Kauai' tab
WebUI.click(
    new TestObject("tab10")
        .addProperty("xpath", ConditionType.EQUALS, "//*[@id='tab-10']")
)

WebElement providerFilterSelect = driver.findElement(By.xpath("//select[@id='provider_filter_select']"))
providerFilterSelect.click()

List<WebElement> dropdownOptions = providerFilterSelect.findElements(By.tagName('option'))
List<String> dropdownProviderNames = dropdownOptions.collect { it.getText().trim() }

boolean allProvidersExist = true
List<String> missingProviders = []

expectedProviders.each { expected ->
    if (!dropdownProviderNames.contains(expected)) {
        allProvidersExist = false
        missingProviders.add(expected)
    }
}

if (!allProvidersExist) {
    WebUI.comment("Missing providers: " + missingProviders)
    throw new Exception("Test Failed: These providers are missing: " + missingProviders)
} else {
    WebUI.comment("All expected providers are present in the dropdown.")
}

WebUI.closeBrowser()
