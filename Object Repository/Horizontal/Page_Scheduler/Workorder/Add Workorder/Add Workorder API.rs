<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>Add Workorder API</name>
   <tag></tag>
   <elementGuidId>e6008807-ee23-483f-862b-a80a14cd057e</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <smartLocatorEnabled>false</smartLocatorEnabled>
   <useRalativeImagePath>false</useRalativeImagePath>
   <authorizationRequest>
      <authorizationInfo>
         <entry>
            <key>bearerToken</key>
            <value>eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6IkVyaWNhLkJvcnJvbWVvQHJjbXQuY29tIiwidXNlcklEIjo4LCJuYW1lIjoiQm9ycm9tZW8sIEVyaWNhIiwiaWF0IjoxNzM2MzE2NTg4LCJleHAiOjE3NDE1MDA1ODh9.9zioj-qpOXS6lvUsP2374IlH0vuAuQc7lXISUEZYhKE</value>
         </entry>
      </authorizationInfo>
      <authorizationType>Bearer</authorizationType>
   </authorizationRequest>
   <autoUpdateContent>false</autoUpdateContent>
   <connectionTimeout>0</connectionTimeout>
   <followRedirects>true</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n    \&quot;typeID\&quot;: 2,\n    \&quot;studentID\&quot;: 1170,\n    \&quot;schoolID\&quot;: 8,\n    \&quot;districtID\&quot;: 7,\n    \&quot;scheduleType\&quot;: 5,\n    \&quot;startDate\&quot;: \&quot;2025-10-27\&quot;,\n  \t\&quot;endDate\&quot; : \&quot;2025-10-22\&quot;,\n    \&quot;unitType\&quot;: 1,\n    \&quot;notes\&quot;: \&quot;TEST INVALID END DATE\&quot;,\n    \&quot;supplemental\&quot;: \&quot;N\&quot;,\n    \&quot;details\&quot;: [\n        {\n            \&quot;dayName\&quot;: \&quot;Monday\&quot;,\n            \&quot;schedules\&quot;: []\n        },\n        {\n            \&quot;dayName\&quot;: \&quot;Tuesday\&quot;,\n            \&quot;schedules\&quot;: []\n        },\n        {\n            \&quot;dayName\&quot;: \&quot;Wednesday\&quot;,\n            \&quot;schedules\&quot;: []\n        },\n        {\n            \&quot;dayName\&quot;: \&quot;Thursday\&quot;,\n            \&quot;schedules\&quot;: []\n        },\n        {\n            \&quot;dayName\&quot;: \&quot;Friday\&quot;,\n            \&quot;schedules\&quot;: []\n        }\n    ],\n    \&quot;oldWorkOrderID\&quot;: null\n}&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
      <webElementGuid>5be016c8-4d42-448b-8421-831d63c84320</webElementGuid>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Authorization</name>
      <type>Main</type>
      <value>BearereyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6IkVyaWNhLkJvcnJvbWVvQHJjbXQuY29tIiwidXNlcklEIjo4LCJuYW1lIjoiQm9ycm9tZW8sIEVyaWNhIiwiaWF0IjoxNzM2MzE2NTg4LCJleHAiOjE3NDE1MDA1ODh9.9zioj-qpOXS6lvUsP2374IlH0vuAuQc7lXISUEZYhKE</value>
      <webElementGuid>26188c44-c476-4514-aa04-351b36c57d34</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>9.7.2</katalonVersion>
   <maxResponseSize>0</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <path></path>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>https://scheduler-qa.rcmt-timecard.com/api/workorders/add</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>0</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
