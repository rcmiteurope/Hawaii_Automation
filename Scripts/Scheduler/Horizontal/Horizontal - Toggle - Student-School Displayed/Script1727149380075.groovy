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

// click the <label for> the horizontal toggle
driver.findElement(By.xpath('//*[@id=":r0:"]/following-sibling::label')).click() 

// grab the list of horizontal column headings from global variables
List<String> horizontal_column_headings = GlobalVariable.horizontal_column_headings

// grab the list of horizontal column heading ids from global variables
List<String> horizontal_column_heading_ids = GlobalVariable.horizontal_column_heading_ids

// initialize variables
WebElement weHorizontalColumnHeading
TestObject toHorizontalColumnHeading

// loop through all columns
for (int i = 0; i < horizontal_column_headings.size(); i++) {
	
	// grab the text from the column's label
	weHorizontalColumnHeading = driver.findElement(By.xpath('//*[@id="' + horizontal_column_heading_ids[i] + '"]'))
	toHorizontalColumnHeading = WebUI.convertWebElementToTestObject(weHorizontalColumnHeading)
	horizontalColumnHeadingText = WebUI.getText(toHorizontalColumnHeading)
	
	// make sure the horizontal column heading text is the expected text
	WebUI.verifyMatch(horizontalColumnHeadingText, horizontal_column_headings[i], false)
	
}

WebUI.closeBrowser()

