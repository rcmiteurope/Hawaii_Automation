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

// grab the horizontal next button
WebElement weHorizontalNextButton = driver.findElement(By.xpath('//*[@id="horizontal_next_btn"]'))
TestObject toHorizontalNextButton = WebUI.convertWebElementToTestObject(weHorizontalNextButton)

// make sure next button is clickable
WebUI.verifyElementClickable(toHorizontalNextButton)

WebUI.closeBrowser()

