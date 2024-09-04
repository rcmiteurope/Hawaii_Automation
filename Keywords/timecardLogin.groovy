import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class timecard {

	def WebDriver driver = DriverFactory.getWebDriver()

	@Keyword
	def isApproved(String dow) {
		WebElement bubble = WebUI.findWebElement(findTestObject('Object Repository/HTA/button_' + dow.substring(0,3).toLowerCase()))
		def int numberOfSiblings = bubble.findElements(By.xpath("following-sibling::*")).size()
		boolean rc = (numberOfSiblings != 0)
		return rc
	}

	@Keyword
	def isNotApproved(String dow) {
		WebElement bubble = WebUI.findWebElement(findTestObject('Object Repository/HTA/button_' + dow.substring(0,3).toLowerCase()))
		def int numberOfSiblings = bubble.findElements(By.xpath("following-sibling::*")).size()
		boolean rc = (numberOfSiblings == 0)
		return rc
	}

	@Keyword
	def isAbsent() {
		WebElement weAbsentBtn = driver.findElement(By.xpath('//*[@id="absentBtn"]'))
		TestObject toAbsentBtn = WebUI.convertWebElementToTestObject(weAbsentBtn)
		String buttonClass = weAbsentBtn.getAttribute('class')
		return buttonClass.contains('text-white')
	}

	@Keyword
	def isPresent() {
		WebElement wePresentBtn = driver.findElement(By.xpath('//*[@id="presentBtn"]'))
		TestObject toPresentBtn = WebUI.convertWebElementToTestObject(wePresentBtn)
		String buttonClass = wePresentBtn.getAttribute('class')
		return buttonClass.contains('text-white')
	}

	@Keyword
	def clearAbsent() {
		if (this.isAbsent()) {
			WebElement weAbsentReason = driver.findElement(By.xpath('//*[@id="root"]/div/main/main/div[4]/div/div[2]/select'))
			TestObject toAbsentReason = WebUI.convertWebElementToTestObject(weAbsentReason)
			WebUI.selectOptionByLabel(toAbsentReason, 'Select...', false)
		}
	}

	@Keyword
	def clearPresent() {
		if (this.isPresent()) {
			WebElement weStartTime = driver.findElement(By.xpath('//*[@id="btn-TimeIn"]'))
			TestObject toStartTime = WebUI.convertWebElementToTestObject(weStartTime)
			WebUI.selectOptionByValue(toStartTime, '00:00', false)
		}
	}

	@Keyword
	def setPresentTimes(String startTime, String endTime) {
		WebElement weStartTime = driver.findElement(By.xpath('//*[@id="btn-TimeIn"]'))
		TestObject toStartTime = WebUI.convertWebElementToTestObject(weStartTime)

		WebElement weEndTime = driver.findElement(By.xpath('//*[@id="btn-TimeOut"]'))
		TestObject toEndTime = WebUI.convertWebElementToTestObject(weEndTime)

		WebUI.selectOptionByValue(toStartTime, '00:00', false)
		if (startTime.length()) WebUI.selectOptionByValue(toStartTime, startTime, false)
		if (endTime.length()) WebUI.selectOptionByValue(toEndTime, endTime, false)
	}

	@Keyword
	def setAbsentReason(String absentReason) {
		WebElement weAbsentReason = driver.findElement(By.xpath('//*[@id="root"]/div/main/main/div[4]/div/div[2]/select'))
		TestObject toAbsentReason = WebUI.convertWebElementToTestObject(weAbsentReason)
		WebUI.selectOptionByLabel(toAbsentReason, absentReason, false)
	}

	@Keyword
	def setToPresent() {
		if (this.isAbsent()) {
			this.clearAbsent()
			driver.findElement(By.xpath('//*[@id="presentBtn"]')).click()
		}
	}

	@Keyword
	def setToAbsent() {
		if (this.isPresent()) {
			this.clearPresent()
			driver.findElement(By.xpath('//*[@id="absentBtn"]')).click()
		}
	}

	@Keyword
	def fromDOW(String dow) {
		switch (dow) {
			case 'Sunday':
			case 'Sun':
				return GlobalVariable.sunday
			case 'Monday':
			case 'Mon':
				return GlobalVariable.monday
			case 'Tuesday':
			case 'Tue':
				return GlobalVariable.tuesday
			case 'Wednesday':
			case 'Wed':
				return GlobalVariable.wednesday
			case 'Thursday':
			case 'Thu':
				return GlobalVariable.thursday
			case 'Friday':
			case 'Fri':
				return GlobalVariable.friday
			case 'Saturday':
			case 'Sat':
				return GlobalVariable.saturday
		}
	}

	@Keyword
	def dowToString = { Integer dow ->
		def String[] strDays = [
			'Sunday',
			'Monday',
			'Tuesday',
			'Wednesday',
			'Thursday',
			'Friday',
			'Saturday'
		]
		return strDays[dow]
	}

	@Keyword
	def dayOfWeek(date) {
		Calendar calendar = Calendar.getInstance()
		calendar.setTime(date)
		return calendar.get(Calendar.DAY_OF_WEEK) - 1 // Sunday = 0 ... Saturday = 6
	}

	@Keyword
	def uid(String userid) {
		WebUI.click(findTestObject('Object Repository/HTA/button_0'))
	}

	@Keyword
	def pwd(String password) {
		WebUI.click(findTestObject('Object Repository/HTA/button_0'))
	}

	@Keyword
	def login(String pin) {
		for(int i = 0;i<pin.length();i++) {
			String button = pin[i]
			switch(button) {
				case "0":
					WebUI.click(findTestObject('Object Repository/HTA/button_0'))
					break
				case "1":
					WebUI.click(findTestObject('Object Repository/HTA/button_1'))
					break
				case "2":
					WebUI.click(findTestObject('Object Repository/HTA/button_2'))
					break
				case "3":
					WebUI.click(findTestObject('Object Repository/HTA/button_3'))
					break
				case "4":
					WebUI.click(findTestObject('Object Repository/HTA/button_4'))
					break
				case "5":
					WebUI.click(findTestObject('Object Repository/HTA/button_5'))
					break
				case "6":
					WebUI.click(findTestObject('Object Repository/HTA/button_6'))
					break
				case "7":
					WebUI.click(findTestObject('Object Repository/HTA/button_7'))
					break
				case "8":
					WebUI.click(findTestObject('Object Repository/HTA/button_8'))
					break
				case "9":
					WebUI.click(findTestObject('Object Repository/HTA/button_9'))
					break
			}
		}
		WebUI.click(findTestObject('Object Repository/HTA/button_Login'))
	}
}
