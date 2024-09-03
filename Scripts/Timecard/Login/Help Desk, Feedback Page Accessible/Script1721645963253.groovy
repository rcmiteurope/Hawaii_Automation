import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')

WebUI.authenticate('http://20.55.96.239/login', 'hawaii', 'hawaiircm', 0)

WebUI.navigateToUrl('http://20.55.96.239/login')

helpdesk = findTestObject('Object Repository/Page_Hawaii Timecard/a_Help Desk')

WebElement element = WebUI.findWebElements(helpdesk, 10).get(0)

String href = element.getAttribute('href')

WebUI.verifyLinksAccessible([href])

WebUI.closeBrowser()

