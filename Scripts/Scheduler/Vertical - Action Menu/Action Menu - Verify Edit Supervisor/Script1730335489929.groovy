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
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', GlobalVariable.sc_auth_token)

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name))

WebUI.refresh()

// Check Horizontal Toggle
WebUI.check(new TestObject("dynamicObj").addProperty("xpath", ConditionType.EQUALS, "//*[@id='root']/main/div[2]/div[1]/div[1]/div/div"))
 

//WebUI.selectOptionByLabel(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="date_filter_select"]'), 
  //  'Next Week', false)

WebUI.click(findTestObject('Object Repository/Action Menu/Page_Scheduler/input_Mon, 1125 0800 - 1415_master-checkbox_5083b2'))

WebUI.executeJavaScript('document.getElementById("actionmenu-handle").click();', null)

WebElement weEditProvider = driver.findElement(By.xpath('//*[@id="provider-callout"]/following-sibling::button'))	// todo: change xpath
TestObject toEditProvider = WebUI.convertWebElementToTestObject(weEditProvider)
WebUI.mouseOver(toEditProvider)	
WebUI.click(toEditProvider)
 
// click the AB menu option
driver.findElement(By.xpath('//*[@id="change-provider-menu-AB"]')).click()
 

WebUI.click(findTestObject('Object Repository/Action Menu/Page_Scheduler/h2_Abad, Jose'))

WebUI.click(findTestObject('Object Repository/Action Menu/Page_Scheduler/button_Save'))

WebUI.click(findTestObject('Object Repository/Action Menu/Page_Scheduler/input_Mon, 1125 0800 - 1415_master-checkbox_5083b2'))

WebUI.executeJavaScript('document.getElementById("actionmenu-handle").click();', null)

WebElement weEditSupervisor = driver.findElement(By.xpath('//*[@id="action-menu-edit-provider"]/following-sibling::button')) // Update XPath if needed
TestObject toEditSupervisor = WebUI.convertWebElementToTestObject(weEditSupervisor)

// Hover over the element and click
WebUI.mouseOver(toEditSupervisor)
WebUI.click(toEditSupervisor)

driver.findElement(By.xpath('//*[@id="change-supervisor-menu-AB"]')).click()
 
WebUI.click(findTestObject('Object Repository/Action Menu/Page_Scheduler/h2_Cameros, Rachel'))

WebUI.click(findTestObject('Object Repository/Action Menu/Page_Scheduler/button_Save'))

String actualText = WebUI.getText(new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="tr-main-0"]/td[5]/div'))
WebUI.verifyMatch(actualText, '.*Cameros, Rachel.*', true)

WebUI.closeBrowser()

