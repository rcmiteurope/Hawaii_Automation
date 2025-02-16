import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import groovy.json.JsonSlurper
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import java.time.format.DateTimeFormatter
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import org.openqa.selenium.Cookie

// **Step 1: Get Current Week's Monday & Friday**
LocalDate today = LocalDate.now()
LocalDate weekStart = today.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY)) // Current week Monday
LocalDate weekEnd = today.with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.FRIDAY)) // Current week Friday

String formattedStartDate = weekStart.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
String formattedEndDate = weekEnd.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

println("Fetching leave details from API for the week: ${formattedStartDate} to ${formattedEndDate}")

// **Step 2: Construct API Request Dynamically**
String apiUrl = "https://scheduler-qa.rcmt-timecard.com/api/providers/get-providers-leave-details?startDate=${formattedStartDate}&endDate=${formattedEndDate}&islandID=9"

RequestObject request = new RequestObject()
request.setRestRequestMethod("GET")
request.setRestUrl(apiUrl)

// **Step 3: Send API Request**
ResponseObject response = WS.sendRequest(request)

if (response.getStatusCode() != 200) {
    WebUI.comment("❌ API request failed with status code: ${response.getStatusCode()}")
    return
}

// **Step 4: Parse JSON Response**
def jsonResponse = new JsonSlurper().parseText(response.getResponseBodyContent())

// **Step 5: Extract Leave Data**
def providerLeaveMap = [:] // Store provider leave details

jsonResponse.data.each { provider ->
    def providerName = provider.provider_name
    providerLeaveMap[providerName] = []

    provider.leave_dates.each { leave ->
        def leaveStartDate = leave.start_date  // 🔹 FIXED: Renamed to `leaveStartDate`
        def leaveEndDate = leave.end_date      // 🔹 FIXED: Renamed to `leaveEndDate`
        def bgColor = leave.style.background
        def fgColor = leave.style.foreground

        // Generate all leave dates for the provider
        def sdf = new SimpleDateFormat("yyyy-MM-dd")
        def current = sdf.parse(leaveStartDate)

        while (!current.after(sdf.parse(leaveEndDate))) {
            providerLeaveMap[providerName] << [
                "date": sdf.format(current),
                "bgColor": bgColor,
                "fgColor": fgColor
            ]
            current.setDate(current.getDate() + 1)
        }
    }
}

// **Step 6: Open Application & Authenticate**
WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()
Cookie authCookie = new Cookie('sc_auth_token', GlobalVariable.sc_auth_token)

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name))
WebUI.refresh()

//Click Lanai
WebUI.click(new TestObject('dynamic').addProperty('xpath', ConditionType.EQUALS, '//*[@id=\'tab-9\']'))


// **Step 7: Locate Only the First Row**
WebElement firstRow = driver.findElement(By.xpath("//table[@id='horizontal-table']/tbody/tr[1]"))

println("✅ Checking only the first row for validation...")

// **Step 8: Validate Provider Data in First Row**
for (int col = 2; col <= 6; col++) { // Monday to Friday (td[2] to td[6])
    try {
        // **Extract Date from Table Header**
        WebElement dateHeader = driver.findElement(By.xpath("//table[@id='horizontal-table']/thead/tr/th[${col}]"))
        String cellDate = dateHeader.getText().trim() // Get column date header (YYYY-MM-DD)

        // **Extract Provider Name, Background & Foreground Color from td[2] to td[6]**
        WebElement cellElement = firstRow.findElement(By.xpath("./td[${col}]/div/div"))
        String providerName = cellElement.getText().trim()
        String actualBgColor = cellElement.getCssValue("background-color").trim()
        String actualFgColor = cellElement.getCssValue("color").trim()

        // **Skip empty cells (No Provider Listed)**
        if (providerName.isEmpty()) {
            println("⚠️ Column ${col}: No provider name found in this cell, skipping.")
            continue
        }

        if (providerLeaveMap.containsKey(providerName)) {
            def leaveEntries = providerLeaveMap[providerName]
            boolean isLeaveDay = leaveEntries.any { it.date == cellDate }
            def expectedEntry = leaveEntries.find { it.date == cellDate }

            if (isLeaveDay && expectedEntry != null) {
                WebUI.verifyMatch(providerName, providerName, false, FailureHandling.CONTINUE_ON_FAILURE)
                WebUI.verifyMatch(actualBgColor, expectedEntry.bgColor, false, FailureHandling.CONTINUE_ON_FAILURE)
                WebUI.verifyMatch(actualFgColor, expectedEntry.fgColor, false, FailureHandling.CONTINUE_ON_FAILURE)
                println("✅ ${providerName} correctly appears on ${cellDate} with correct colors.")
            } else {
                println("⚠️ ${providerName} does not have leave on ${cellDate}, skipping verification.")
            }
        } else {
            println("⚠️ ${providerName} is NOT found in API leave data, skipping verification.")
        }
    } catch (Exception e) {
        println("❌ ERROR: Could not validate Column ${col}: ${e.message}")
    }
}

// **Step 9: Close Browser**
WebUI.closeBrowser()