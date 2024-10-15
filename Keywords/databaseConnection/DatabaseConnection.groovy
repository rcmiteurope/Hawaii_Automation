package databaseConnection

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

import groovy.sql.Sql


public class DatabaseConnection {

	static Sql connectToDatabase() {
		def dbUrl = 'jdbc:mysql://172.0.0.76:3306/client_services_qa' // Update accordingly
		def dbUser = 'rcmadm'
		def dbPassword = 'rcmtech123'
		def dbDriver = 'com.mysql.cj.jdbc.Driver'

		return Sql.newInstance(dbUrl, dbUser, dbPassword, dbDriver)
	}
	static void closeConnection(Sql sql) {
		if (sql != null) {
			sql.close()
		}
	}
}
