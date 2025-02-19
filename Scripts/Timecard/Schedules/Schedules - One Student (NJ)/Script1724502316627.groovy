import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')
WebUI.setViewPortSize(GlobalVariable.mobileWidth,GlobalVariable.mobileHeight)

//WebUI.authenticate(GlobalVariable.timecard_url, 'hawaii', 'hawaiircm', 0)

// monday's schedule has one student (NJ)
WebUI.navigateToUrl(GlobalVariable.timecard_url + "/login?cd=" + GlobalVariable.monday)

CustomKeywords.'timecard.login'(GlobalVariable.multi_school_pin)

WebDriver driver = DriverFactory.getWebDriver()

List students = driver.findElements(By.xpath('//div[@id="studentsListContainer"]/child::div'))

WebUI.verifyEqual(students.size(), 1)

WebElement student = students.get(0)

WebUI.verifyEqual(student.getText(), 'NJ')

WebUI.closeBrowser()

