import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword

import internal.GlobalVariable

Random random = new Random()

WebUI.openBrowser('')

WebUI.navigateToUrl((GlobalVariable.scheduler_url))

WebDriver driver = DriverFactory.getWebDriver()

// set the date filter dropdown "Next Week"
CustomKeywords.'timecardLogin.setOptionSelectedByLabel'('date_filter_select', 'Next Week')

// set the student filter dropdown to random student
WebElement weStudentDropdown = driver.findElement(By.xpath('//*[@id="student_filter_select"]'))
TestObject toStudentDropdown = WebUI.convertWebElementToTestObject(weStudentDropdown)
numberOfStudents = WebUI.getNumberOfTotalOption(toStudentDropdown)
// debugging code... println numberOfStudents
CustomKeywords.'timecardLogin.setOptionSelectedByIndex'('student_filter_select', random.nextInt(numberOfStudents))

// click the select-all-toggle checkbox
driver.findElement(By.xpath('//*[@id="select-all-toggle"]')).click()

// click the select-all-toggle dropdown
driver.findElement(By.xpath('//*[@id="actionmenu-handle"]')).click()

// click the Edit Provider menu option
WebElement weEditProvider = driver.findElement(By.xpath('//*[@id="provider-callout"]/following-sibling::button'))	// todo: change xpath
TestObject toEditProvider = WebUI.convertWebElementToTestObject(weEditProvider)
WebUI.mouseOver(toEditProvider)	// mouseover must proceed click
WebUI.click(toEditProvider)

// click the AB menu option
driver.findElement(By.xpath('//*[@id="change-provider-menu-AB"]')).click()

// grab a random provider and hold onto their name
WebElement weRandomProvider = driver.findElement(By.xpath('//*[@id="providerlist' + random.nextInt(30) + '"]'))
randomProviderName = weRandomProvider.getText()

// click on the random provider to select them
weRandomProvider.click()

// validate provider 
WebElement weMainTable = driver.findElement(By.xpath('//*[@id="root"]/main/div[5]/table'))

// grab all the <tr> rows in the <table>
List<WebElement> rows = weMainTable.findElements(By.xpath('descendant::tr'))

//debugging code... println rows.size()

// loop through each row (skipping [0] heading and [1] blank)
for (int i=2; i<rows.size(); i++) {
	
	// grab all the cells <td> in the <row>
	List<WebElement> cells = rows[i].findElements(By.xpath('child::td'))
	
	// debugging code... println cells[2].getText()

	// make sure each row matches
	WebUI.verifyMatch(cells[4].getText(), randomProviderName, false)
	
}

WebUI.closeBrowser()
