import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
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

WebUI.openBrowser('')

WebUI.setViewPortSize(GlobalVariable.spreadsheetWidth, GlobalVariable.spreadsheetHeight)

// navigate to website (any dow is fine)  
WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

WebElement weHorizontal = driver.findElement(By.xpath('//*[@id="root"]/main/div[2]/div/div/label/div'))

TestObject toHorizontal = WebUI.convertWebElementToTestObject(weHorizontal)

WebUI.verifyElementClickable(toHorizontal)

WebUI.click(findTestObject('Page_Scheduler/div_Horizontal'))

WebUI.getText(findTestObject('Page_Scheduler/th_Student  School'))

