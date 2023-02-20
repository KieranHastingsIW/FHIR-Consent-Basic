# Basic HAPI FHIR server with a simple Consent Interceptor implementation 

Requirerments
    - Maven 3.8.6
    - Java 17 or later
    - Postman

* Clone this repo to the directory of your choice
* cd into the chosen directory and run `mvn exec:java -Dexec.mainClass="ca.uhn.fhir.letsbuild.server.RunServer"`in your terminal of choice.
* Use the `FHIR-Consent.postman_collection.json` in postman to POST, then GET consent object
* The response status from the POST method should be 201 created and the body should be the consnet resource type that was sent in the body with a new ID
* The response status from the GET method should be 200 OK and the body should be of the consent with the ID that was stated in the request parameter following Consent. 
* In the terminal you are running the FHIR server on you should see the responses METHOD NAME: startOperation, PATIENT ID AND DISPLAY{"reference":"Patient/1","display":"P. van de Heuvel"}, and METHOD NAME: willSeeResource.

More extensive versions of FHIR have validation on posting consent objects that reference patients that do not exist but this one does not, also the way I have done the FHIR to string to json conversion is probably very inefficient while also using another fhir context object, I think there will be a much better way of doing this. 


