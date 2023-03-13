# FHIR CONSENT INTERCEPTOR ON HAPI-FHIR-JPA-STARTER WITH POSTGRES-DB
## Requirements
* Docker for Desktop
## Build and Run FHIR Server
* Using a DOS terminal direct yourself to this directory and run `docker-compose up --build`
* Once FHIR is running using the browser of your choice look up localhost:8080, this will direct you to the FHIR GUI where you can add, search, update, ect your resources.
* At the bottom of the home page, you will see a text box that will accept bundles, with a transaction button to the left. paste any one of the bundles from the ResourceExample folder found in this repository and click transaction.
* You should now have a handful of resource in your FHIR server that will be stored in your database, using the search functionality found by clicking on the Observation tab located in the Left-hand side nav bar (Ordered alphabetically), you will now be able to search for observation resources but will not be permitted to see observations that are of observation-category 'laboratory'.

## Consent Manager
* Currently a stand alone MS with its own database, also built when running the above docker compose file.
* In later state will be imbedded into the fhir server.
* In current state runs on localhost:9090, if it does not run on first try select guifhir in docker desktop and run again.
* In current state content from guifhir Consent manager does not affect what can be seen but some of the code that makes this happen can be seen in the consent interceptor class in the hapi fhir server.

## Diagram explaining how the resources are remved from the returned payload.
![Diagram](https://github.com/KieranHastingsIW/FHIR-Consent-Basic/blob/main/diagram.png)


## FHIR LEARNINGS
### startOperation
* Entry point of interceptor, all requests will trigger at the beginning.
### canSeeResource
* Method used to decide whether you see a resource at all.
* should not modify appearance of resource, should be efficient seeing as it will run once for every resource requested in read or search.
* If Searching for all observations the consent interceptor will call startOperation once, then call canSeeResource for each Observation that it finds,
### willSeeResource
* Method used to define how a resource will be displayed to you, will make modifications and remove information based on consent specification (when this part is implemented properly, currently is hard codded to remove gender)

