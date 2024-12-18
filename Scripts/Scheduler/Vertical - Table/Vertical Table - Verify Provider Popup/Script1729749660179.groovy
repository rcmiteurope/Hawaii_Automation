import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Cookie as Cookie
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.WebElement
import java.util.List
import java.util.Random
WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url)

def driver = DriverFactory.getWebDriver()

DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6IkVyaWNhLkJvcnJvbWVvQHJjbXQuY29tIiwidXNlcklEIjo4LCJpYXQiOjE3MzE5ODYxMDEsImV4cCI6MTczNDU3ODEwMX0.AUWF2TrOJtXoWXnwJaA3MHQJ0iUgTpDUw2YrdjazB_Q')

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', 'Erica.Borromeo%40rcmt.com'))

driver.manage().addCookie(new Cookie('user_name', 'Borromeo%2C%20Erica'))

WebUI.refresh()

TestObject horizontalToggle = new TestObject()

horizontalToggle.addProperty('xpath', ConditionType.EQUALS, '//*[@id="root"]/main/div[2]/div/div/label/div')

WebUI.check(horizontalToggle)

String rowsXPath = "//table[@id='vertical-table']//tbody/tr"

try {
    // Fetch all rows in the table
    List<WebElement> rows = WebUI.findWebElements(new TestObject().addProperty("xpath", ConditionType.EQUALS, rowsXPath), 10)
    
    if (!rows.isEmpty()) {
        WebUI.comment("Total rows found: " + rows.size())
        
        // Filter rows based on the value in the 5th column
        List<Integer> validRowIndexes = []
        for (int i = 1; i <= rows.size(); i++) {
            // Define XPath for the 5th <td> in the current row
            String tdXPath = "//table[@id='vertical-table']//tbody/tr[" + i + "]/td[5]/div"
            TestObject tdElement = new TestObject().addProperty("xpath", ConditionType.EQUALS, tdXPath)

            // Get the value of the 5th <td>
            String cellValue = WebUI.getText(tdElement).trim()

            // Add the row index to the list if the value is not excluded
            if (!(cellValue in ["OPEN", "Student Absent", "Holiday", "Waiting for Order"])) {
                validRowIndexes.add(i)
            }
        }

        // Check if there are valid rows
        if (!validRowIndexes.isEmpty()) {
            WebUI.comment("Valid rows found: " + validRowIndexes.size())

            // Randomly select a valid row
            Random random = new Random()
            int randomRowIndex = validRowIndexes.get(random.nextInt(validRowIndexes.size()))

            // Define XPath for the 5th <td> of the randomly selected valid row
            String tdXPath = "//table[@id='horizontal-table']//tbody/tr[" + randomRowIndex + "]/td[5]/div"
            TestObject validTdElement = new TestObject().addProperty("xpath", ConditionType.EQUALS, tdXPath)

            // Click the valid <td[5]>
            WebUI.click(validTdElement)

            // Get the value of the clicked <td[5]>
            String clickedValue = WebUI.getText(validTdElement)
            WebUI.comment("Randomly clicked td[5] with value: '" + clickedValue + "' in row " + randomRowIndex)

            // Define XPath for the label inside the dialog
            TestObject dialogLabel = new TestObject().addProperty("xpath", ConditionType.EQUALS, '//div[@class="dialog-class"]/label')

            // Wait for the dialog to appear
            WebUI.waitForElementVisible(dialogLabel, 10)

            // Get the value of the label inside the dialog
            String dialogLabelValue = WebUI.getText(dialogLabel)
            WebUI.comment("Value from the dialog label: '" + dialogLabelValue + "'")

            // Compare the two values
            if (clickedValue.equals(dialogLabelValue)) {
                WebUI.comment("Validation passed: The clicked cell value matches the dialog label value.")
            } else {
                WebUI.comment("Validation failed: The clicked cell value does not match the dialog label value.")
            }
        } else {
            WebUI.comment("No valid rows found with values other than OPEN, Student Absent, Holiday, or Waiting for Order.")
        }
    } else {
        WebUI.comment("No rows found in the table.")
    }
} catch (Exception e) {
    WebUI.comment("An error occurred: " + e.getMessage())
}
WebUI.closeBrowser()

