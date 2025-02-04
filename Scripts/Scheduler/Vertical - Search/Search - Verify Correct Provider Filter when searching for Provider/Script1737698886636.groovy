import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Cookie as Cookie
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import org.openqa.selenium.Keys as Keys
import org.testng.Assert
import java.text.SimpleDateFormat
import java.util.Date
WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', GlobalVariable.sc_auth_token)

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name))

WebUI.refresh()

// Check Horizontal Toggle
TestObject horizontalToggle = new TestObject("horizontalToggle")
horizontalToggle.addProperty("xpath", ConditionType.EQUALS, "//*[@id='root']/main/div[2]/div[1]/div[1]/div/div")
WebUI.verifyElementPresent(horizontalToggle, 5)
WebUI.check(horizontalToggle)
 
TestObject userToggle = new TestObject().addProperty('xpath', ConditionType.EQUALS, '//button[@id=\':rb:\']')

WebUI.click(userToggle)

TestObject searchOption = new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id=\':ra:\']/button[1]/div')

WebUI.click(searchOption
	
WebUI.click(findTestObject('Object Repository/Search/Page_Scheduler/div_Search_css-19bb58m'))

WebUI.click(findTestObject('Object Repository/Search/Page_Scheduler/div_Abad, Jose'))

List<WebElement> col4Cells = driver.findElements(By.xpath("//table[@id='vertical-table']/tbody/tr/td[5]"));

for (WebElement cell : col4Cells) {
	String cellText = cell.getText().trim();
   Assert.assertTrue(cellText.contains("Abad, Jose"));

}

// Close the browser
WebUI.closeBrowser()