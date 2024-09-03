import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class timecardLogin {
	
	@Keyword
	def uid(String userid) {
		WebUI.click(findTestObject('Object Repository/Page_Hawaii Timecard/button_0'))
	}
	
	@Keyword
	def pwd(String password) {
		WebUI.click(findTestObject('Object Repository/Page_Hawaii Timecard/button_0'))
	}

	@Keyword
	def login(String pin) {
		for(int i = 0;i<pin.length();i++) {
			String button = pin[i]
			switch(button) {
				case "0":
					WebUI.click(findTestObject('Object Repository/Page_Hawaii Timecard/button_0'))
					break
				case "1":
					WebUI.click(findTestObject('Object Repository/Page_Hawaii Timecard/button_1'))
					break
				case "2":
					WebUI.click(findTestObject('Object Repository/Page_Hawaii Timecard/button_2'))
					break
				case "3":
					WebUI.click(findTestObject('Object Repository/Page_Hawaii Timecard/button_3'))
					break
				case "4":
					WebUI.click(findTestObject('Object Repository/Page_Hawaii Timecard/button_4'))
					break
				case "5":
					WebUI.click(findTestObject('Object Repository/Page_Hawaii Timecard/button_5'))
					break
				case "6":
					WebUI.click(findTestObject('Object Repository/Page_Hawaii Timecard/button_6'))
					break
				case "7":
					WebUI.click(findTestObject('Object Repository/Page_Hawaii Timecard/button_7'))
					break
				case "8":
					WebUI.click(findTestObject('Object Repository/Page_Hawaii Timecard/button_8'))
					break
				case "9":
					WebUI.click(findTestObject('Object Repository/Page_Hawaii Timecard/button_9'))
					break
			}
		}
		WebUI.click(findTestObject('Object Repository/Page_Hawaii Timecard/button_Login'))
	}
}
