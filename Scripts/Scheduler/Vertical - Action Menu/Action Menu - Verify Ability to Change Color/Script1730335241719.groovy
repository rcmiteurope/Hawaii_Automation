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
import org.openqa.selenium.WebElement as WebElement
import org.openqa.selenium.By as By

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
WebUI.click(findTestObject('Object Repository/Action Menu/Page_Scheduler/input_Mon, 1125 0800 - 1415_master-checkbox_b9635f'))

WebUI.executeJavaScript('document.getElementById("actionmenu-handle").click();', null)

WebElement changeColor = driver.findElement(By.xpath('//*[@id="actionmenu-handle"]/div/span/span') // todo: change xpath
    )

TestObject toChangeColor = WebUI.convertWebElementToTestObject(changeColor)

WebUI.click(toChangeColor)

WebUI.mouseOver(toChangeColor)

WebUI.click(findTestObject('Object Repository/Action Menu/Page_Scheduler/div_Foreground_w-7 h-7 rounded-lg border-2 _9f7500_1'))

WebUI.click(findTestObject('Object Repository/Action Menu/Page_Scheduler/div_1_2'))

WebUI.click(findTestObject('Object Repository/Action Menu/Page_Scheduler/div_Background_w-7 h-7 rounded-lg border-2 _41ce99_1'))

WebUI.click(findTestObject('Object Repository/Action Menu/Page_Scheduler/div_1_2_3'))

WebUI.click(findTestObject('Object Repository/Action Menu/Page_Scheduler/button_Apply Changes'))

WebUI.closeBrowser()

