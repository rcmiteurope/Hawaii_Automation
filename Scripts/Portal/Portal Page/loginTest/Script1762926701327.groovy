import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
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

WebUI.navigateToUrl('https://hawaiitimecardportal.ciamlogin.com/b06d6b0d-a841-4060-b160-dbb747b34658/oauth2/v2.0/authorize?client_id=9500a993-d741-42e8-b77f-ecc8330a1cfa&scope=openid%20profile%20email%20api%3A%2F%2F9500a993-d741-42e8-b77f-ecc8330a1cfa%2Faccess_as_user%20offline_access&redirect_uri=https%3A%2F%2Ftimecard-portal-qa.rcmt-timecard.com%2Flogin&client-request-id=019a76b5-79f4-7ff2-8192-98ffb3a72665&response_mode=fragment&client_info=1&nonce=019a76b5-7a4f-7beb-bec5-f28f6a8b0181&state=eyJpZCI6IjAxOWE3NmI1LTdhMDEtN2M3Mi1iMTg1LTBkZDZjZWY4Yzk3MSIsIm1ldGEiOnsiaW50ZXJhY3Rpb25UeXBlIjoicmVkaXJlY3QifX0%3D&x-client-SKU=msal.js.browser&x-client-VER=4.21.0&response_type=code&code_challenge=8WyNvFeZ8W6uDi9WrcK4yBIEBCri_4JSsZtlb64G774&code_challenge_method=S256')

WebUI.setText(findTestObject('Object Repository/TestLogin/Page_Sign in to your account/input_Sign in to access Hawaii Timecard Por_5df149'), 
    'jirome.bautista@rcmt.com')

WebUI.click(findTestObject('Object Repository/TestLogin/Page_Sign in to your account/input_Sign in to access Hawaii Timecard Por_43c40a'))

WebUI.setEncryptedText(findTestObject('Object Repository/TestLogin/Page_Sign in to your account/input_Enter password_passwd'), 
    'U83JPl/FLVSDiOSBikDkoA==')

WebUI.click(findTestObject('Object Repository/TestLogin/Page_Sign in to your account/input_Sign in to access Hawaii Timecard Por_43c40a'))

WebUI.click(findTestObject('Object Repository/TestLogin/Page_Sign in to your account/span_Dont show this again'))

WebUI.click(findTestObject('Object Repository/TestLogin/Page_Sign in to your account/input_Sign in to access Hawaii Timecard Por_43c40a'))

WebUI.closeBrowser()

