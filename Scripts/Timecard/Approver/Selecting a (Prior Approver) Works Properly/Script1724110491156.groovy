import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')
WebUI.setViewPortSize(GlobalVariable.mobileWidth,GlobalVariable.mobileHeight)

WebUI.authenticate('http://20.55.96.239/login', 'hawaii', 'hawaiircm', 0)

WebUI.navigateToUrl('http://20.55.96.239/login')

CustomKeywords.'timecard.login'('987654')

WebDriver driver = DriverFactory.getWebDriver()

// find the nurses avatar
WebElement nurses_avatar = driver.findElement(By.xpath('//*[@id="root"]/main/div[1]/div[2]/a'))

// click the nurses avatar
nurses_avatar.click()

// click timecard approval
WebUI.click(findTestObject('Object Repository/HTA/a_TimecardApproval'))

// change to week ending July 27 because that has unapproved time for testing
WebUI.selectOptionByValue(findTestObject('Object Repository/HTA/select_WeekEnding'), 'Sat Jul 27 2024', true)

// click the Approve button
WebUI.click(findTestObject('Object Repository/HTA/button_Approve'))

// click the Approver's Name hyperlink to display the prior approvers list
WebUI.click(findTestObject('Object Repository/HTA/p_ApproversName'))

// click the first item on the list 
WebElement first_item = driver.findElement(By.xpath('//*[@id="root"]/main/div[2]/div[1]/div/p[1]'))
first_item.click()

// grab the approver's name
WebElement approversName = driver.findElement(By.xpath('//*[@id="root"]/main/div[3]/div[1]/div/input'))

// verify the approver's name = 'mike rubin'
WebUI.verifyEqual(approversName.getAttribute("value"), 'mike rubin')

// grab the approver's email address
WebElement approversEmailAddress = driver.findElement(By.xpath('//*[@id="root"]/main/div[3]/div[2]/div/input'))

// verify the approver's email address = 'mike.rubin@rcmt.com
WebUI.verifyEqual(approversEmailAddress.getAttribute("value"), 'mike.rubin@rcmt.com')

// grab the approver's phone number
WebElement approversPhoneNumber = driver.findElement(By.xpath('//*[@id="root"]/main/div[3]/div[3]/div/input'))

// verify the approver's phone number = '9082851239'
WebUI.verifyEqual(approversPhoneNumber.getAttribute("value"), '9082851239')

WebUI.closeBrowser()
