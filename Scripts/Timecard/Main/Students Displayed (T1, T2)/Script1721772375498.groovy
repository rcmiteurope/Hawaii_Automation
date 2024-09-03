import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

WebUI.openBrowser('')

not_run: WebUI.authenticate('http://20.55.96.239/login', 'hawaii', 'hawaiircm', 2)

WebUI.navigateToUrl('http://172.212.97.100/login')

CustomKeywords.'timecardLogin.login'('973077')

WebDriver driver = DriverFactory.getWebDriver()

WebUI.click(findTestObject('Object Repository/Page_Hawaii Timecard/button_Tue'))

List students = driver.findElements(By.xpath('//div[@id="studentsListContainer"]/child::div'))

WebUI.verifyEqual(students.size(), 2)

WebElement student0 = students.get(0)

WebUI.verifyEqual(student0.getText(), 'T1')

WebElement student1 = students.get(1)

WebUI.verifyEqual(student1.getText(), 'T2')

WebUI.closeBrowser()

