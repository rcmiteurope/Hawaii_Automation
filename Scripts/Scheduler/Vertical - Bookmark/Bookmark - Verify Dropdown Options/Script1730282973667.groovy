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
import org.openqa.selenium.Cookie as Cookie
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.WebDriver as WebDriver
import com.kms.katalon.core.testobject.ConditionType as ConditionType

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', GlobalVariable.sc_auth_token)

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name))

WebUI.refresh()

TestObject horizontalToggle = new TestObject()

horizontalToggle.addProperty('xpath', ConditionType.EQUALS, '//*[@id="root"]/main/div[2]/div/div/label/div')

WebUI.check(horizontalToggle)

TestObject bookmarkIcon = new TestObject()

bookmarkIcon.addProperty('xpath', ConditionType.EQUALS, '//*[@id=":r7:"]')

WebUI.click(bookmarkIcon)

TestObject button = new TestObject()
button.addProperty('xpath', ConditionType.EQUALS, '//*[@id=":r6:"]/button[1]')
WebUI.verifyElementText(button, 'Home')


TestObject button1 = new TestObject()
button1.addProperty('xpath', ConditionType.EQUALS, '//*[@id=":r6:"]/button[2]')
WebUI.verifyElementText(button1, 'OPEN for Today')


TestObject button2 = new TestObject()
button2.addProperty('xpath', ConditionType.EQUALS, '//*[@id=":r6:"]/button[3]')
WebUI.verifyElementText(button2, 'OPEN for Tomorrow')

TestObject button3 = new TestObject()
button3.addProperty('xpath', ConditionType.EQUALS, '//*[@id=":r6:"]/button[4]')
WebUI.verifyElementText(button3, 'All Holidays')

WebUI.closeBrowser()

