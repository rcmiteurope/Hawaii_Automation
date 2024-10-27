// Create a sorted version for comparison (descending order)
List<String> sortedDesc = new ArrayList<>(schoolNames)
sortedDesc.sort(Collections.reverseOrder())

// Log the school names and the expected sorted order for debugging
WebUI.comment('School Names: ' + schoolNames.toString())
WebUI.comment('Expected Descending Order: ' + sortedDesc.toString())

// Check if the table is already sorted in descending order
if (schoolNames == sortedDesc) {
    WebUI.comment('✅ The table is already sorted in descending order.')
} else {
    WebUI.comment('❌ The table is not sorted in descending order.')

    // Click to sort the table in descending order
    WebUI.click(findTestObject('Object Repository/Vertical Table/Page_Scheduler/svg'))

    // Add a short delay to allow the sorting to complete
    WebUI.delay(1)

    // Extract the text again to check the new order
    schoolElements = WebUI.findWebElements(
        new TestObject().addProperty('xpath', ConditionType.EQUALS, '//*[@id="vertical-table"]/tbody/tr/td[1]'), 
        10)

    schoolNames = schoolElements.collect { it.getText().trim() }

    // Verify the table is now sorted in descending order
    WebUI.comment('School Names after clicking SVG: ' + schoolNames.toString())

    assert schoolNames == sortedDesc : '❌ The table is not sorted in descending order.'

    WebUI.comment('✅ The table is correctly sorted in descending order after clicking the SVG.')
}
