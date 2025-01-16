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

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url)

// Add authentication cookies
WebDriver driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', GlobalVariable.sc_auth_token)

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name))

WebUI.refresh()

// Check Horizontal Toggle
WebUI.check(new TestObject("dynamicObj").addProperty("xpath", ConditionType.EQUALS, "//*[@id='root']/main/div[2]/div[1]/div[1]/div/div"))
 

WebUI.selectOptionByValue(findTestObject('Object Repository/Vertical - Other Actions Dropdown/Page_Scheduler/select_Other ActionsAdd SchoolAdd StudentAd_8e5993'), 
    'add-workorder', true)

WebUI.click(findTestObject('Object Repository/Vertical - Other Actions Dropdown/Page_Scheduler/svg_Search_css-8mmkcg'))

WebUI.click(findTestObject('Object Repository/Vertical - Other Actions Dropdown/Page_Scheduler/div_Abe, Tyler'))

WebUI.click(findTestObject('Object Repository/Vertical - Other Actions Dropdown/Page_Scheduler/div_Search_css-19bb58m'))

WebUI.click(findTestObject('Object Repository/Vertical - Other Actions Dropdown/Page_Scheduler/div_Aiea High'))

WebUI.click(findTestObject('Object Repository/Vertical - Other Actions Dropdown/Page_Scheduler/input_Date Range_datepicker'))

WebUI.click(findTestObject('Object Repository/Vertical - Other Actions Dropdown/Page_Scheduler/button_25'))

WebUI.click(findTestObject('Object Repository/Vertical - Other Actions Dropdown/Page_Scheduler/button_26'))

WebUI.click(findTestObject('Object Repository/Vertical - Other Actions Dropdown/Page_Scheduler/div_Start Time_css-19bb58m'))

WebUI.click(findTestObject('Object Repository/Vertical - Other Actions Dropdown/Page_Scheduler/div_0600'))

WebUI.click(findTestObject('Object Repository/Vertical - Other Actions Dropdown/Page_Scheduler/div_End Time'))

WebUI.click(findTestObject('Object Repository/Vertical - Other Actions Dropdown/Page_Scheduler/div_0730'))

WebUI.click(findTestObject('Object Repository/Vertical - Other Actions Dropdown/Page_Scheduler/div_Start Time_css-19bb58m_1'))

WebUI.click(findTestObject('Object Repository/Vertical - Other Actions Dropdown/Page_Scheduler/div_0615'))

WebUI.click(findTestObject('Object Repository/Vertical - Other Actions Dropdown/Page_Scheduler/div_End Time_css-19bb58m'))

WebUI.click(findTestObject('Object Repository/Vertical - Other Actions Dropdown/Page_Scheduler/div_0930'))

WebUI.click(findTestObject('Object Repository/Vertical - Other Actions Dropdown/Page_Scheduler/button_Submit'))

// Close the browser
WebUI.closeBrowser()

