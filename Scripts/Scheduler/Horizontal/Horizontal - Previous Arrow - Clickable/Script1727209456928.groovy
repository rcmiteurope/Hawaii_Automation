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
driver.findElement(By.xpath('//*[@id="horizontal_toggle"]/following-sibling::label')).click()

// grab the horizontal previous button
WebElement weHorizontalPreviousButton = driver.findElement(By.xpath('//*[@id="horizontal_prev_btn"]'))
TestObject toHorizontalPreviousButton = WebUI.convertWebElementToTestObject(weHorizontalPreviousButton)

// make sure previous button is clickable
WebUI.verifyElementClickable(toHorizontalPreviousButton)

WebUI.closeBrowser()

