import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')
WebUI.setViewPortSize(GlobalVariable.mobileWidth,GlobalVariable.mobileHeight)

//WebUI.authenticate(GlobalVariable.timecard_url, 'hawaii', 'hawaiircm', 0)

// navigate to website (monday has students)  
WebUI.navigateToUrl(GlobalVariable.timecard_url + "/login?cd=" + GlobalVariable.monday)

CustomKeywords.'timecard.login'(GlobalVariable.single_school_pin)

WebDriver driver = DriverFactory.getWebDriver()

// click on first student
driver.findElements(By.xpath('//div[@id="studentsListContainer"]/child::div'))[0].click()
	
// click the edit avatar pencil
WebUI.findWebElement(findTestObject('Object Repository/HTA/button_EditStudentAvatar')).click()

// make sure the header is "Choose Avatar"
WebUI.verifyTextPresent("Choose Avatar", false)

WebUI.closeBrowser()

