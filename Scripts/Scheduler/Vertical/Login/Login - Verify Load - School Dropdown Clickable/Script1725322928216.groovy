import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Cookie as Cookie

WebUI.openBrowser('')
WebUI.setViewPortSize(GlobalVariable.spreadsheetWidth,GlobalVariable.spreadsheetHeight)

//WebUI.authenticate(GlobalVariable.timecard_url, 'hawaii', 'hawaiircm', 0)

// navigate to website (any dow is fine)  
WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6IkVyaWNhLkJvcnJvbWVvQHJjbXQuY29tIiwidXNlcklEIjo4LCJpYXQiOjE3MzE4NjExOTksImV4cCI6MTczMTk0NzU5OX0.QXNaXEWFidvJcgth3ij4mjy3MAHrRwv7buLh-2aaBhM')

driver.manage().addCookie(authCookie)

WebUI.refresh()


WebElement weSchoolDropdown = driver.findElement(By.xpath("//*[@id='school_filter_select']"))
TestObject toSchoolDropdown = WebUI.convertWebElementToTestObject(weSchoolDropdown)

WebUI.verifyElementClickable(toSchoolDropdown)

WebUI.closeBrowser()