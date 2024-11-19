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
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.By

import org.openqa.selenium.Cookie

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url)

def driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6IkVyaWNhLkJvcnJvbWVvQHJjbXQuY29tIiwidXNlcklEIjo4LCJpYXQiOjE3MzE4NjExOTksImV4cCI6MTczMTk0NzU5OX0.QXNaXEWFidvJcgth3ij4mjy3MAHrRwv7buLh-2aaBhM')
driver.manage().addCookie(authCookie)

new Select(driver.findElement(By.xpath("//select[@id='school_filter_select']"))).selectByVisibleText("Aliiolani Elementary")
new Select(driver.findElement(By.xpath("//select[@id='student_filter_select']"))).selectByVisibleText("Avilla, Lincoln")

WebUI.click(findTestObject('Object Repository/Vertical - Locations Tab/Page_Scheduler/div_Central'))

assert new Select(driver.findElement(By.xpath("//select[@id='school_filter_select']"))).getFirstSelectedOption().getText() == "All Schools" : "School filter is not set to 'All Schools'"
assert new Select(driver.findElement(By.xpath("//select[@id='student_filter_select']"))).getFirstSelectedOption().getText() == "All Students" : "Student filter is not set to 'All Students'"

