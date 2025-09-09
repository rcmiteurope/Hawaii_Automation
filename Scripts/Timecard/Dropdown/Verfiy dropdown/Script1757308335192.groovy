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
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select
import com.kms.katalon.core.webui.driver.DriverFactory
import java.text.SimpleDateFormat
import org.openqa.selenium.By

WebUI.openBrowser('')

WebUI.navigateToUrl('https://hi-qa.rcmt-timecard.com/login')

// Enter PIN 995593
WebUI.click(findTestObject('Object Repository/Timecard - Verify Dropdown/Page_Hawaii Timecard/button_9'))
WebUI.click(findTestObject('Object Repository/Timecard - Verify Dropdown/Page_Hawaii Timecard/button_9'))
WebUI.click(findTestObject('Object Repository/Timecard - Verify Dropdown/Page_Hawaii Timecard/button_5'))
WebUI.click(findTestObject('Object Repository/Timecard - Verify Dropdown/Page_Hawaii Timecard/button_5'))
WebUI.click(findTestObject('Object Repository/Timecard - Verify Dropdown/Page_Hawaii Timecard/button_9'))
WebUI.click(findTestObject('Object Repository/Timecard - Verify Dropdown/Page_Hawaii Timecard/button_3'))
WebUI.click(findTestObject('Object Repository/Timecard - Verify Dropdown/Page_Hawaii Timecard/button_Login'))

// --- Step 2: Verify Dropdown Dates ---
// Get the dropdown WebElement
WebElement dropdownElement = DriverFactory.getWebDriver().findElement(By.id("calendarWeekEnding"))
Select select = new Select(dropdownElement)

// Define date range: June 1, 2024 → August 31, 2026
SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH)
Date minDate = sdf.parse("June 1, 2024")
Date maxDate = sdf.parse("August 31, 2026")

// Loop through all dropdown options
for (WebElement option : select.getOptions()) {
    String optionText = option.getText().trim()
    Date optionDate = sdf.parse(optionText)

    if (optionDate.before(minDate) || optionDate.after(maxDate)) {
        WebUI.comment("❌ Invalid date found in dropdown: " + optionText)
        assert false : "Dropdown contains invalid date: " + optionText
    } else {
        WebUI.comment("✅ Valid date: " + optionText)
    }
}

WebUI.closeBrowser()