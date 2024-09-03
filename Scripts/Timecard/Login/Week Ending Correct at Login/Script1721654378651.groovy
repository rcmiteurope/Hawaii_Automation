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

WebUI.navigateToUrl('http://20.55.96.239/login')

CustomKeywords.'timecardLogin.login'('973077')

def date = new Date()

Calendar calendar = Calendar.getInstance()

calendar.setTime(date)

def day = calendar.get(Calendar.DAY_OF_WEEK // Sunday = 1 ... Saturday = 7 
    )

if (day == 7) {
    we_date = we_date.plus(7 // Saturday is actually Monday
        )
}

def we_date = date.plus(7 - day)

we_date = we_date.format('MMMM d, yyyy')

WebUI.verifyOptionSelectedByLabel(findTestObject('Object Repository/Page_Hawaii Timecard/select_Week Ending'), we_date, 
    false, 5)

WebUI.closeBrowser()

