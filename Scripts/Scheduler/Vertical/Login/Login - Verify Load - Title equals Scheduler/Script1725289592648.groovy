import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Cookie as Cookie

WebUI.openBrowser('')
WebUI.setViewPortSize(GlobalVariable.spreadsheetWidth,GlobalVariable.spreadsheetHeight)

//WebUI.authenticate(GlobalVariable.timecard_url, 'hawaii', 'hawaiircm', 0)

// navigate to website (any dow is fine)  
WebUI.navigateToUrl(GlobalVariable.scheduler_url)
WebDriver driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', GlobalVariable.sc_auth_token)

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name))

WebUI.refresh()

// grab the <Head><Title>
String windowTitle = WebUI.getWindowTitle()

// verify title equals 'scheduler'
WebUI.verifyMatch(windowTitle, "Scheduler", false)

WebUI.closeBrowser()