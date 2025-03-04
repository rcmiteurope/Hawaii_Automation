import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import internal.GlobalVariable as GlobalVariable
import groovy.json.JsonSlurper
import org.openqa.selenium.WebDriver
import org.openqa.selenium.Cookie
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType


// Step 1: Open Browser and Login
WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.Test_EWeb_Url)

WebDriver driver = DriverFactory.getWebDriver()
driver.manage().addCookie(new Cookie('user_name', GlobalVariable.test_username))
driver.manage().addCookie(new Cookie('password', GlobalVariable.test_password))

// Step 2: Prepare API Request
RequestObject request = new RequestObject()
request.setRestUrl("https://www.ewebstaffing.com/rcm-test/api/scheduleStatus.php")
request.setRestRequestMethod("GET")

// Add Authorization & Headers
List<TestObjectProperty> headers = new ArrayList<>()
headers.add(new TestObjectProperty("Content-Type", ConditionType.EQUALS, "application/json"))
request.setHttpHeaderProperties(headers)

// Add Request Body
String requestBody = """{
    "sessionToken": "n5o7rddmhsmr9hqrgvpi4v8b5q",
    "dateRange": {
        "from": "2025-02-27",
        "to": "2025-02-27"
    }
}"""
request.setBodyContent(new HttpTextBodyContent(requestBody, "UTF-8", "application/json"))

// Step 3: Send API Request
def response = WS.sendRequest(request)
def jsonResponse = new JsonSlurper().parseText(response.getResponseText())

// Step 4: Validate API Response Data
if (jsonResponse.data == null || jsonResponse.data.isEmpty()) {
    WebUI.comment("❌ No data returned from API")
    WebUI.closeBrowser()
    return
}

def apiData = jsonResponse.data[0] // Assuming first entry is correct

// Step 5: Extract Data from Web Page
String serviceDate = WebUI.getText(findTestObject('Object Repository/ServiceDate'))
String startTime = WebUI.getText(findTestObject('Object Repository/StartTime'))
String endTime = WebUI.getText(findTestObject('Object Repository/EndTime'))
String status = WebUI.getText(findTestObject('Object Repository/Status'))
String school = WebUI.getText(findTestObject('Object Repository/School'))

// Step 6: Compare Web Data with API Response
boolean isDataMatching = (
    serviceDate == apiData.ServiceDate &&
    startTime == apiData.StartTime &&
    endTime == apiData.EndTime &&
    status == apiData.Status &&
    school == apiData.School
)

if (isDataMatching) {
    WebUI.comment("✅ Webpage data matches API data!")
} else {
    WebUI.comment("❌ Mismatch found between API and Webpage data!")
}

// Step 7: Close Browser
WebUI.closeBrowser()
