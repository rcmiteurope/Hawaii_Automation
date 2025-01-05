import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
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
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject

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

//WebUI.selectOptionByLabel(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="date_filter_select"]'), 'Next Week', false)
 
// Store the actual text for comparison
String actualText = WebUI.getText(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="tr-main-0"]/td[5]/div'))

// Check if the actual text matches the expected text
if (actualText == 'Waiting on Orders') {
    WebUI.click(new TestObject().addProperty('id', ConditionType.EQUALS, 'master-checkbox-toggle226217'))

    WebUI.executeJavaScript('document.getElementById("actionmenu-handle").click();', null)

    WebUI.executeJavaScript('document.getElementById("waiting-on-orders").click();', null)

	WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, "//button[@class='swal2-confirm swal2-styled' and text()='Save']"))
	
    actualText = WebUI.getText(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="tr-main-1"]/td[5]/div'))
   	WebUI.verifyMatch(actualText, 'Waiting on Orders', true)

} else {
    WebUI.click(new TestObject().addProperty('id', ConditionType.EQUALS, 'master-checkbox-toggle226216'))

    WebUI.executeJavaScript('document.getElementById("actionmenu-handle").click();', null)

    WebUI.executeJavaScript('document.getElementById("waiting-on-orders").click();', null)

	WebUI.click(new TestObject().addProperty('xpath', ConditionType.EQUALS, "//button[@class='swal2-confirm swal2-styled' and text()='Save']"))
	
    actualText = WebUI.getText(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="tr-main-0"]/td[5]/div'))
	 WebUI.verifyMatch(actualText, 'Waiting on Orders', true)
	 
}

WebUI.closeBrowser()

