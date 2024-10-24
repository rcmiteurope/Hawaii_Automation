import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.navigateToUrl((GlobalVariable.scheduler_url))

WebDriver driver = DriverFactory.getWebDriver()

// grab the <label for> the horizontal toggle
WebElement weHorizontalToggle = driver.findElement(By.xpath('//*[@id="horizontal_toggle"]/following-sibling::label'))
TestObject toHorizontalToggle = WebUI.convertWebElementToTestObject(weHorizontalToggle)

// grab the school filter dropdown and verify the selected option if 'All Schools'
CustomKeywords.'timecardLogin.verifyOptionSelected'('school_filter_select', 'All Schools')

//WebElement weSchoolFilterSelect = driver.findElement(By.xpath('//*[@id="school_filter_select"]'))
//TestObject toSchoolFilterSelect = WebUI.convertWebElementToTestObject(weSchoolFilterSelect)
//WebUI.verifyOptionSelectedByLabel(toSchoolFilterSelect, 'All Schools', false, 0)


// grab the student filter dropdown and verify the selected option if 'All Students'
WebElement weStudentFilterSelect = driver.findElement(By.xpath('//*[@id="student_filter_select"]'))
TestObject toStudentFilterSelect = WebUI.convertWebElementToTestObject(weStudentFilterSelect)
WebUI.verifyOptionSelectedByLabel(toStudentFilterSelect, 'All Students', false, 0)

// grab the date filter dropdown and verify the selected option if 'This Week'
WebElement weDateFilterSelect = driver.findElement(By.xpath('//*[@id="date_filter_select"]'))
TestObject toDateFilterSelect = WebUI.convertWebElementToTestObject(weDateFilterSelect)
WebUI.verifyOptionSelectedByLabel(toDateFilterSelect, 'All Providers', false, 0)

// grab the provider filter dropdown and verify the selected option if 'All Providers'
WebElement weProviderFilterSelect = driver.findElement(By.xpath('//*[@id="provider_filter_select"]'))
TestObject toProviderFilterSelect = WebUI.convertWebElementToTestObject(weProviderFilterSelect)
WebUI.verifyOptionSelectedByLabel(toProviderFilterSelect, 'All Providers', false, 0)
