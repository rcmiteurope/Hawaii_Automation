import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

WebUI.openBrowser('')

not_run: WebUI.authenticate('http://20.55.96.239/login', 'hawaii', 'hawaiircm', 0)

WebUI.navigateToUrl('http://172.212.97.100/login')

CustomKeywords.'timecardLogin.login'('973077')

WebDriver driver = DriverFactory.getWebDriver()

WebUI.click(findTestObject('Page_Hawaii Timecard/button_Tue30'))

List students = driver.findElements(By.xpath('//div[@id="studentsListContainer"]/child::div'))

WebUI.verifyEqual(students.size(), 1)

WebUI.closeBrowser()

