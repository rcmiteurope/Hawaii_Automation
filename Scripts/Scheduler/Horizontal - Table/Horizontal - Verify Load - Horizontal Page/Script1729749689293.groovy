import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject // Import for findTestObject

WebUI.openBrowser('')

// Set viewport size
WebUI.setViewPortSize(GlobalVariable.spreadsheetWidth, GlobalVariable.spreadsheetHeight)

// Navigate to the scheduler URL
WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

// Wait for the horizontal element to be visible
WebUI.waitForElementVisible(findTestObject('Page_Scheduler/div_Horizontal'), 10) // Verify if the test object exists in the repository

// Click the horizontal element
WebUI.click(findTestObject('Page_Scheduler/div_Horizontal'))

// Get text from another element
String text = WebUI.getText(findTestObject('Page_Scheduler/th_Student  School'))

// Print the retrieved text
println("Retrieved text: " + text)

//WebUI.closeBrowser()
