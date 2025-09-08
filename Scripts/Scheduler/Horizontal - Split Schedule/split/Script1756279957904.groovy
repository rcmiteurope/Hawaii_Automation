import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper as JsonSlurper
import internal.GlobalVariable as GlobalVariable
// STEP 1: Open Scheduler page
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.Cookie as Cookie
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url)
WebDriver driver = DriverFactory.getWebDriver()
Cookie authCookie = new Cookie('sc_auth_token', GlobalVariable.sc_auth_token_jirome)

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email_jirome))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name_jirome))

WebUI.refresh()

WebUI.click(findTestObject('Object Repository/Split Schedule/Page_Scheduler/div_OPEN 0500-120035'))

// Wait until the button is visible and clickable
WebUI.waitForElementVisible(findTestObject('Object Repository/Split Schedule/Page_Scheduler/button_Split Schedule'), 10)
WebUI.waitForElementClickable(findTestObject('Object Repository/Split Schedule/Page_Scheduler/button_Split Schedule'), 10)

// Scroll into view in case it's off screen
WebUI.scrollToElement(findTestObject('Object Repository/Split Schedule/Page_Scheduler/button_Split Schedule'), 5)

// Extra: small delay to let UI animations finish
WebUI.delay(1)

TestObject splitBtn = findTestObject('Object Repository/Split Schedule/Page_Scheduler/button_Split Schedule')
WebElement element = WebUI.findWebElement(splitBtn, 10)
WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(element))
// Now click
WebUI.click(findTestObject('Object Repository/Split Schedule/Page_Scheduler/button_Split Schedule'))

WebUI.click(findTestObject('Object Repository/Split Schedule/Page_Scheduler/button_Add Split'))

WebUI.click(findTestObject('Object Repository/Split Schedule/Page_Scheduler/button_Confirm All Splits'))

WebUI.click(findTestObject('Object Repository/Split Schedule/Page_Scheduler/button_Save'))

WebUI.closeBrowser()


