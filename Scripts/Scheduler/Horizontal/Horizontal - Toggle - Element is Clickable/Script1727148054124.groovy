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
WebElement weHorizontalToggle = driver.findElement(By.xpath('//*[@id=":r0:"]/following-sibling::label'))
TestObject toHorizontalToggle = WebUI.convertWebElementToTestObject(weHorizontalToggle)

// make sure the toggle is clickable
WebUI.verifyElementClickable(toHorizontalToggle)

WebUI.closeBrowser()