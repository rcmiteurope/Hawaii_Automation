import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.setViewPortSize(GlobalVariable.spreadsheetWidth, GlobalVariable.spreadsheetHeight)

// navigate to website (any dow is fine)  
WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

// grab the action menu dropdown arrow; can't be directly clicked
WebElement weActionMenuHandle = driver.findElement(By.xpath('//*[@id="actionmenu-handle"]'))

// convert action menu dropdown element into a test object
TestObject toActionMenuHandle = WebUI.convertWebElementToTestObject(weActionMenuHandle)

// make sure the action menu dropdown is disabled when the "select all" checkbox is clear
WebUI.verifyElementNotClickable(toActionMenuHandle)

WebUI.closeBrowser()

