import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.navigateToUrl(GlobalVariable.scheduler_url)

// Store the actual text for comparison
String actualText = WebUI.getText(new TestObject().addProperty("xpath", ConditionType.EQUALS, '//*[@id="tr-main-9"]/td[5]/div'))

// Check if the actual text matches the expected text
if (actualText == 'Waiting on Orders') 
{
    WebUI.click(new TestObject().addProperty("id", ConditionType.EQUALS, "master-checkbox-toggle159408"))
	
    WebUI.executeJavaScript('document.getElementById("actionmenu-handle").click();', null)
	
    WebUI.executeJavaScript('document.getElementById("waiting-on-orders").click();', null)
	
    WebUI.verifyElementText(new TestObject().addProperty("xpath", ConditionType.EQUALS, '//*[@id="tr-main-13"]/td[5]/div'), 'Waiting on Orders')
		
} else {
    // If the text does not match, execute the else block
    WebUI.click(new TestObject().addProperty("id", ConditionType.EQUALS, "master-checkbox-toggle164248"))
	
    WebUI.executeJavaScript('document.getElementById("actionmenu-handle").click();', null)
	
    WebUI.executeJavaScript('document.getElementById("waiting-on-orders").click();', null)
	
    WebUI.verifyElementText(new TestObject().addProperty("xpath", ConditionType.EQUALS, '//*[@id="tr-main-9"]/td[5]/div'), 'Waiting on Orders')
		
}

WebUI.closeBrowser()
