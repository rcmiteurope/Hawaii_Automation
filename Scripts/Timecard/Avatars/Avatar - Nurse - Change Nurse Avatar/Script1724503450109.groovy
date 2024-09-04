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

// grab the avatar image element
WebElement image = WebUI.findWebElement(findTestObject('Object Repository/HTA/img_NurseAvatarSmall'))	// here

// grab the image filename from the 5th section of the image src (http://20.55.96.239/images/nurse-avatars/1314916808-05.jpg)
String current_filename = image.getAttribute('src').split('/')[5]

// click the nurses avatar
WebUI.click(findTestObject('Object Repository/HTA/img_NurseAvatarSmall'))

// alternate between -01 and -05 (selected randomly)
String future_filename = current_filename == '1314916808-01.jpg' ? '1314916808-06.jpg' : '1314916808-01.jpg'

// click the avatar edit (pencil) button
driver.findElement(By.xpath('//*[@id="editAvatar"]')).click()	// here

// find the avatar container that shows all the possible images
List<WebElement> avatars = driver.findElements(By.xpath('//div[@id="avatarsContainer"]/div'))

// loop thru the avatars until you find the one whose src = future_filename 
def WebElement avatarImage
for (int i = 0; i < avatars.size() - 1; i++) {
	avatarImage = avatars[i].findElement(By.xpath('child::img'))
	String src = avatarImage.getAttribute("src")
	if (src.indexOf(future_filename) > 0) break
}

// click on the avatar that matched
avatarImage.click()

// find the Success header
WebUI.delay(5)
WebElement successHeader = driver.findElement(By.xpath('//*[@id="swal2-title"]'))
WebUI.verifyEqual(successHeader.getText(), "Success")

// find the Success header
WebElement successOkButton = driver.findElement(By.xpath('/html/body/div[2]/div/div[6]/button[1]'))
successOkButton.click()

// grab the avatar image element 
image = WebUI.findWebElement(findTestObject('Object Repository/HTA/img_NurseAvatarMedium'))	// here

println future_filename
println image.getAttribute('src')

// grab the image filename from the 5th section of the image src (http://20.55.96.239/images/nurse-avatars/1314916808-05.jpg)
current_filename = image.getAttribute('src').split('/')[5]

println current_filename

// verify the current filename is as expected
WebUI.verifyEqual(current_filename, future_filename)

WebUI.closeBrowser()