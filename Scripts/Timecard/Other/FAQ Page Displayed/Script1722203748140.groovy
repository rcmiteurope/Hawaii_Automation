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

WebUI.navigateToUrl(GlobalVariable.timecard_url)

CustomKeywords.'timecard.login'(GlobalVariable.multi_school_pin)

WebDriver driver = DriverFactory.getWebDriver()
	
// click the nurses avatar
WebUI.click(findTestObject('Object Repository/HTA/img_NurseAvatarSmall'))

// click faq
WebUI.click(findTestObject('Object Repository/HTA/a_FAQ (Frequently Asked Questions)'))

// get page's header
String faq = WebUI.getText(findTestObject('Object Repository/HTA/h3_FAQ'))

// make sure the header is "FAQ"
WebUI.verifyEqual(faq, 'FAQ')

WebUI.closeBrowser()

