import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
 
WebUI.openBrowser('')
 
WebUI.setViewPortSize(GlobalVariable.spreadsheetWidth, GlobalVariable.spreadsheetHeight)
 
WebDriver driver = DriverFactory.getWebDriver()
 
// navigate to website (any dow is fine)  
WebUI.navigateToUrl(GlobalVariable.scheduler_url)
 
// grab the list of islands from global variables
List<String> islands = GlobalVariable.islands
 
// get list of locations
List<String> locations = driver.findElements(By.xpath('//*[@id="locations"]/div/div/div'))
 
Boolean missing = false
 
// compare list from global variables to list from website
for (def location : locations) {
    // find location in list of locations
    int indexOfLocation = islands.indexOf(location.getText())
 
    // throw an error if the island isn't found
    if (indexOfLocation == -1) {
        // throw the error
        KeywordUtil.markFailed(('********* Island ' + location.getText()) + ' is missing ************')
 
        missing = true // pop location off island stack
    } else {
        islands.remove(indexOfLocation)
    }
}
 
// verify there are no more islands left in the list
WebUI.verifyEqual(islands.size(), 0)
 
WebUI.closeBrowser()