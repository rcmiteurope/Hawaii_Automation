import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

WebUI.openBrowser('')

WebUI.navigateToUrl((GlobalVariable.scheduler_url))

WebDriver driver = DriverFactory.getWebDriver()

// click the Outer Islands tab
WebElement weOuterIslandsTab = driver.findElement(By.xpath('//*[@id="tab-5"]'))
WebUI.click(WebUI.convertWebElementToTestObject(weOuterIslandsTab))

// grab the school filter dropdown element
WebElement weSchoolFilterSelect = driver.findElement(By.xpath('//*[@id="school_filter_select"]'))
TestObject toSchoolFilterSelect = WebUI.convertWebElementToTestObject(weSchoolFilterSelect)

// select the Iao school
WebUI.selectOptionByValue(toSchoolFilterSelect, '{"schoolName":"Iao","id":137}', false)

// grab the <table> with all the results
WebElement weMainTable = driver.findElement(By.xpath('//*[@id="root"]/main/div[3]/table'))

// grab all the <tr> rows in the <table>
List<WebElement> rows = weMainTable.findElements(By.xpath('descendant::tr'))

// loop threw each row
for (row in rows) {
	
	// grab all the cells <td> in the <row>
	List<WebElement> cells = row.findElements(By.xpath('child::td'))

	// loop through the rows
	if (cells.size() == 5) {
		
		// make sure each row matches
		WebUI.verifyMatch(cells[0].getText(), 'Iao', false)
		
	}
	
}

WebUI.closeBrowser()

