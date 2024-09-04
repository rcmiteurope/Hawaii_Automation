import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')
WebUI.setViewPortSize(GlobalVariable.mobileWidth,GlobalVariable.mobileHeight)

//WebUI.authenticate(GlobalVariable.timecard_url, 'hawaii', 'hawaiircm', 0)

// navigate to website (any dow is fine)  
WebUI.navigateToUrl(GlobalVariable.timecard_url + "/login?cd=" + GlobalVariable.monday)

CustomKeywords.'timecard.login'(GlobalVariable.multi_school_pin)

WebDriver driver = DriverFactory.getWebDriver()

// grab the avatar image element
WebElement image = WebUI.findWebElement(findTestObject('Object Repository/HTA/img_NurseAvatarSmall'))	// here

// grab the image filename from the 5th section of the image src (http://20.55.96.239/images/nurse-avatars/1314916808-05.jpg)
String current_filename = image.getAttribute('src').split('/')[5]

// click the nurses avatar
WebUI.click(findTestObject('Object Repository/HTA/img_NurseAvatarSmall'))

// click the avatar edit (pencil) button
driver.findElement(By.xpath('//*[@id="editAvatar"]')).click()	// here

// make sure the header is "Choose Avatar"
WebUI.verifyTextPresent("Choose Avatar", false)

WebUI.closeBrowser()