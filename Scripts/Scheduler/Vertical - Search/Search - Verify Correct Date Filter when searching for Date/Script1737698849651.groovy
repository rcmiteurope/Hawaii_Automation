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
import org.testng.Assert as Assert
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', GlobalVariable.sc_auth_token)

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name))

WebUI.refresh()

// Check Horizontal Toggle
TestObject horizontalToggle = new TestObject('horizontalToggle')

horizontalToggle.addProperty('xpath', ConditionType.EQUALS, '//*[@id=\'root\']/main/div[2]/div[1]/div[1]/div/div')

WebUI.verifyElementPresent(horizontalToggle, 5)

WebUI.check(horizontalToggle)

TestObject userToggle = new TestObject().addProperty('xpath', ConditionType.EQUALS, '//button[@id=\':rb:\']')

WebUI.click(userToggle)

TestObject searchOption = new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id=\':ra:\']/button[5]/div')

WebUI.click(searchOption)

WebUI.click(findTestObject('Object Repository/Search/Page_Scheduler/input_Kauai_datepicker'))

WebUI.rightClick(findTestObject('Object Repository/Search/Page_Scheduler/button_31'))

WebUI.click(findTestObject('Object Repository/Search/Page_Scheduler/button_31'))

WebUI.click(findTestObject('Object Repository/Search/Page_Scheduler/button_31'))

List<WebElement> col4Cells = driver.findElements(By.xpath("//table[@id='vertical-table']/tbody/tr/td[3]"))

for (WebElement cell : col4Cells) {
    String cellText = cell.getText().trim() 
    WebUI.comment("Cell text: " + cellText)
    
    Assert.assertTrue(cellText.contains('1/31'))
}

// Close the browser
WebUI.closeBrowser()


//WebUI.click(findTestObject('Object Repository/Search/Page_Scheduler/svg_Kauai_size-1.5em_1'))

//WebUI.click(findTestObject('Object Repository/Search/Page_Scheduler/div_Student'))

//WebUI.click(findTestObject('Object Repository/Search/Page_Scheduler/div_Search_css-19bb58m'))

//WebUI.click(findTestObject('Object Repository/Search/Page_Scheduler/div_Abe, Tyler'))

//WebUI.click(findTestObject('Object Repository/Search/Page_Scheduler/path'))

//WebUI.click(findTestObject('Object Repository/Search/Page_Scheduler/button_Supervisor'))

//WebUI.click(findTestObject('Object Repository/Search/Page_Scheduler/div_Search_css-19bb58m'))

//WebUI.click(findTestObject('Object Repository/Search/Page_Scheduler/div_Cameros, Rachel'))

//WebUI.click(findTestObject('Object Repository/Search/Page_Scheduler/svg_Kauai_size-1.5em_1'))

//WebUI.click(findTestObject('Object Repository/Search/Page_Scheduler/button_Date'))

//WebUI.click(findTestObject('Object Repository/Search/Page_Scheduler/input_Kauai_datepicker'))

//WebUI.rightClick(findTestObject('Object Repository/Search/Page_Scheduler/button_31'))

//WebUI.click(findTestObject('Object Repository/Search/Page_Scheduler/button_31'))

//WebUI.click(findTestObject('Object Repository/Search/Page_Scheduler/button_31'))

