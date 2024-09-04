import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')
WebUI.setViewPortSize(GlobalVariable.mobileWidth,GlobalVariable.mobileHeight)

WebUI.authenticate('http://20.55.96.239/login', 'hawaii', 'hawaiircm', 0)

WebUI.navigateToUrl('http://20.55.96.239/login')

CustomKeywords.'timecardLogin.login'('987654')

WebUI.click(findTestObject('Object Repository/HTA/img_NurseAvatarLarge'))

WebUI.click(findTestObject('Object Repository/HTA/a_TimecardApproval'))

'jump to a future week ending date as defined by the local variable future_week_ending'
WebUI.selectOptionByLabel(findTestObject('Object Repository/HTA/select_WeekEnding'), 
    future_week_ending, true)

'this should bring you back to the current Week Ending'
WebUI.click(findTestObject('Object Repository/HTA/a_Timecard'))

'confirm the Week Ending date equals the local variable current_week_ending'
WebUI.verifyOptionSelectedByLabel(findTestObject('HTA/select_WeekEnding'), 
    current_week_ending, false, 1)

WebDriver driver = DriverFactory.getWebDriver()

'grab the parent of the label Ready For Approval - that where the background color resides'
WebElement readyForApproval = driver.findElement(By.xpath('//h1[text()="Ready For Approval"]/parent::div'))

String className = readyForApproval.getAttribute('class')

'create local variable for class string'
WebUI.verifyEqual('border-2 border-black px-1 bg-[#8cbcf7]', "$className")

WebUI.closeBrowser()

