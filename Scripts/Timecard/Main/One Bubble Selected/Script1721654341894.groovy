import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
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
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

not_run: WebUI.authenticate('http://20.55.96.239/login', 'hawaii', 'hawaiircm', 0)

WebUI.navigateToUrl('http://172.212.97.100/login')

CustomKeywords.'timecardLogin.login'('973077')

def date = new Date()

Calendar calendar = Calendar.getInstance()

calendar.setTime(date)

def day = calendar.get(Calendar.DAY_OF_WEEK) - 1 // Sunday = 0 ... Saturday = 6

if ((day == 0) || (day == 6)) {
    day = 1 // if testing on Saturday or Sunday, selected bubble must be Monday
}

WebDriver driver = DriverFactory.getWebDriver()

WebElement bubble = driver.findElement(By.xpath(('//*[@id="daysContainer"]/div[' + day) + ']/button'))

String className = bubble.getAttribute('class')

Integer offset = className.indexOf('bg-[#cadef9]')

'offset = -1 indicates color not found in className'
WebUI.verifyNotEqual(offset, -1)

WebUI.closeBrowser()

