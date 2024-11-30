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
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.Cookie as Cookie
import com.kms.katalon.core.webui.driver.DriverFactory

//Open browser and navigate to the page
WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url)  // Replace with your URL

WebDriver driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6IkVyaWNhLkJvcnJvbWVvQHJjbXQuY29tIiwidXNlcklEIjo4LCJpYXQiOjE3MzE5ODYxMDEsImV4cCI6MTczNDU3ODEwMX0.AUWF2TrOJtXoWXnwJaA3MHQJ0iUgTpDUw2YrdjazB_Q')

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', 'Erica.Borromeo%40rcmt.com'))

driver.manage().addCookie(new Cookie('user_name', 'Borromeo%2C%20Erica'))


WebUI.refresh()

//Fetch the list of school names from the table
List<WebElement> schoolElements = WebUI.findWebElements(
    new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="vertical-table"]/tbody/tr/td[1]'), 
    10)  

//Extract text from the elements and store it in a list
List<String> schoolNames = schoolElements.collect { it.getText().trim() }

//Create a sorted version for comparison (ascending order)
List<String> sortedAsc = new ArrayList<>(schoolNames)
sortedAsc.sort()

//Log the school names and the expected sorted order for debugging
WebUI.comment('School Names: ' + schoolNames.toString())
WebUI.comment('Expected Ascending Order: ' + sortedAsc.toString())

//Check if the table is already sorted in ascending order
if (schoolNames == sortedAsc) {
    WebUI.comment('✅ The table is already sorted in ascending order.')
} else {
    WebUI.comment('❌ The table is not sorted in ascending order.')

    //Click the sort the table in ascending order
    WebUI.click(findTestObject('Object Repository/Vertical Table/Page_Scheduler/svg'))

    //Add a short delay to allow the sorting to complete
    WebUI.delay(1)

    //Extract the text again to check the new order
    schoolElements = WebUI.findWebElements(
        new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="vertical-table"]/tbody/tr/td[1]'), 
        10)

    schoolNames = schoolElements.collect { it.getText().trim() }

    //Verify the table is now sorted in ascending order
    WebUI.comment('School Names after clicking SVG: ' + schoolNames.toString())

    assert schoolNames == sortedAsc : '❌ The table is not sorted in ascending order.'

    WebUI.comment('✅ The table is correctly sorted in ascending order after clicking the SVG.')
}

// 11. Close the browser
WebUI.closeBrowser()
