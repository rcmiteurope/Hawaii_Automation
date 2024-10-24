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

// set the date filter dropdown "Next Week"
CustomKeywords.'timecardLogin.setOptionSelected'('date_filter_select', 'Next Week')

// set the student filter dropdown "Jay-Funaki, Nya"
CustomKeywords.'timecardLogin.setOptionSelected'('student_filter_select', 'Jay-Funaki, Nya')

// click the select-all-toggle checkbox
driver.findElement(By.xpath('//*[@id="select-all-toggle"]')).click()

// click the select-all-toggle dropdown
driver.findElement(By.xpath('//*[@id="actionmenu-handle"]')).click()

// click the Edit Provider menu option
WebElement weEditProvider = driver.findElement(By.xpath('//*[@id="provider-callout"]/following-sibling::button'))
//println weEditProvider.getTagName()
weEditProvider.click()
//TestObject toEditProvider = WebUI.convertWebElementToTestObject(weEditProvider)
//WebUI.mouseOver(toEditProvider)

// click the AB menu option
driver.findElement(By.xpath('//*[@id="change-provider-menu-AB"]')).click()


//// grab the student filter dropdown and select student Jay-Funaki, Nya
//WebElement weStudentFilterSelect = driver.findElement(By.xpath('//*[@id="student_filter_select"]'))
//TestObject toStudentFilterSelect = WebUI.convertWebElementToTestObject(weStudentFilterSelect)
//WebUI.selectOptionByLabel(toStudentFilterSelect, 'Jay-Funaki, Nya', false)

//WebUI.closeBrowser()
