import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Cookie as Cookie
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import org.openqa.selenium.Keys as Keys
import org.testng.Assert
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Calendar

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url)

WebDriver driver = DriverFactory.getWebDriver()

Cookie authCookie = new Cookie('sc_auth_token', GlobalVariable.sc_auth_token)

driver.manage().addCookie(authCookie)

driver.manage().addCookie(new Cookie('user_email', GlobalVariable.user_email))

driver.manage().addCookie(new Cookie('user_name', GlobalVariable.user_name))

WebUI.refresh()

// Check Horizontal Toggle
WebUI.check(new TestObject("dynamicObj").addProperty("xpath", ConditionType.EQUALS, "//*[@id='root']/main/div[2]/div[1]/div[1]/div/div"))
 
// Locate the dropdown using the XPath
WebElement dropdown = driver.findElement(By.id('date_filter_select'))

dropdown.click()

WebElement dateOption = dropdown.findElement(By.xpath("//option[text()='Next Week']"))
dateOption.click()

WebUI.delay(5)

Calendar cal = Calendar.getInstance()
cal.set(Calendar.HOUR_OF_DAY, 0)
cal.set(Calendar.MINUTE, 0)
cal.set(Calendar.SECOND, 0)
cal.set(Calendar.MILLISECOND, 0)

while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
	cal.add(Calendar.DAY_OF_YEAR, 1)
}
Date nextMonday = cal.getTime()

Calendar calEnd = (Calendar) cal.clone()
calEnd.add(Calendar.DAY_OF_YEAR, 4)
Date nextFriday = calEnd.getTime()

List<WebElement> col3Cells = driver.findElements(By.xpath("//table[@id='vertical-table']/tbody/tr/td[3]"))

SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy")
int currentYear = Calendar.getInstance().get(Calendar.YEAR)

for (WebElement cell : col3Cells) {
	String cellText = cell.getText().trim()
	String datePartAfterComma = cellText.split(",")[1].trim()
	String mmddFromCell = datePartAfterComma.split(" ")[0].trim()
	String fullDateStr = mmddFromCell + "/" + currentYear

	Date cellDate = sdf.parse(fullDateStr)

	boolean isOnOrAfterNextMon = !cellDate.before(nextMonday)
	boolean isOnOrBeforeNextFri = !cellDate.after(nextFriday)
	boolean isWithinWeek = isOnOrAfterNextMon && isOnOrBeforeNextFri

	Assert.assertTrue(
		isWithinWeek,
		"Date " + fullDateStr + " is not between next Monday ("
		+ sdf.format(nextMonday) + ") and Friday ("
		+ sdf.format(nextFriday) + ")."
	)
}

// Close the browser
WebUI.closeBrowser()


