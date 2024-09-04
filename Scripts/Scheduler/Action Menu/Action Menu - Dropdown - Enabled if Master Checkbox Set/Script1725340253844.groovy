import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')
WebUI.setViewPortSize(GlobalVariable.spreadsheetWidth,GlobalVariable.spreadsheetHeight)

// navigate to website (any dow is fine)  
WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

//WebElement weActionCheckbox = driver.findElement(By.xpath('//*[@id="root"]/main/div[3]/table/thead/tr/th[8]/span/input'))
//weActionCheckbox.click()

WebElement weActionDropdown = driver.findElement(By.xpath('//*[@id="root"]/main/div[3]/table/thead/tr/th[8]/span/span'))
weActionDropdown.click()

WebElement weActionMenu = driver.findElement(By.xpath('//*[@id=":re:"]'))
TestObject toActionMenu = WebUI.convertWebElementToTestObject(weActionMenu)

WebUI.verifyElementNotVisible(toActionMenu)

//WebUI.closeBrowser()