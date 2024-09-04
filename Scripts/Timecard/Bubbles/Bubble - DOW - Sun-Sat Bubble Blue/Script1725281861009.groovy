import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')
WebUI.setViewPortSize(GlobalVariable.mobileWidth,GlobalVariable.mobileHeight)

//WebUI.authenticate(GlobalVariable.timecard_url, 'hawaii', 'hawaiircm', 0)

WebDriver driver = DriverFactory.getWebDriver()


for (int dow=0; dow<=6; dow++) {
	
	// convert dow into text (Sunday .. Saturday)
	String dayOfWeek = CustomKeywords.'timecard.dowToString'(dow)	

	// convert Sunday .. Saturday into global variable
	String dowFromGlobalVariable = CustomKeywords.'timecard.fromDOW'(dayOfWeek)

	// navigate to url with global variable
	WebUI.navigateToUrl((GlobalVariable.timecard_url + '/login?cd=') + dowFromGlobalVariable)

	// login
	CustomKeywords.'timecard.login'(GlobalVariable.single_school_pin)	

	def WebElement bubble
	
	// treat sunday (0) and saturady (6) as monday (1)
	if (dow == 0 || dow == 6) {
		
		// grab the monday bubble
		bubble = driver.findElement(By.xpath('//*[@id="daysContainer"]/div[1]/button'))
		
	} else {

		// on monday - friday, grab that day's bubble
		bubble = driver.findElement(By.xpath('//*[@id="daysContainer"]/div[' + dow + ']/button'))

	}

	// grab the class
	String className = bubble.getAttribute('class')
	
	// look for the background color in the class
	Integer offset = className.indexOf('bg-[#cadef9]')
	
	// 	'offset = -1 indicates color not found in className'
	WebUI.verifyNotEqual(offset, -1)

}
	 
WebUI.closeBrowser()
