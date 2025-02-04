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
import org.openqa.selenium.Cookie as Cookie
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', GlobalVariable.sc_auth_token)

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name))

WebUI.refresh()

// Click on 'Kauai' tab
WebUI.click(
	new TestObject("tab10")
		.addProperty("xpath", ConditionType.EQUALS, "//*[@id='tab-10']")
)

// Check Horizontal Toggle
//WebUI.check(new TestObject("dynamicObj").addProperty("xpath", ConditionType.EQUALS, "//*[@id='root']/main/div[2]/div[1]/div[1]/div/div"))
 
WebUI.selectOptionByValue(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="other-actions-dropdown"]'), 'add-leave', true)

WebUI.selectOptionByLabel(
    new TestObject().addProperty('xpath', ConditionType.EQUALS, "//*[@id='filter-wrapper']/div/dialog/div[2]/div[1]/select"), 
    "Aana Leslie Ann", 
    true
)

String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

String formattedDate = today + " - " + today

TestObject datepicker = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//*[@id='datepicker']")

WebUI.click(datepicker)

WebUI.setText(datepicker, formattedDate)

WebUI.sendKeys(null, Keys.chord(Keys.ENTER))


WebUI.comment("Set datepicker value: " + formattedDate)
WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id=\'foreground-holder\']'))
WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//div[@title=\'#1515ff\']'))
WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, "//*[@id='background-holder']"))
WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, "//div[@title='#D0E3F4']"))

WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, "//*[@id='submit-dialog']"))

WebUI.closeBrowser()

