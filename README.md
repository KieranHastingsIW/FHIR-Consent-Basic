# FHIR CONSNET INTERCEPTOR ON HAPI-FHIR-JPA-STARTER WITH POSTGRES-DB

Requirements 
  - locally hosted version of POSTGRES
  - Maven

* Using the PSQL CLI Create Postgres database, take note of the name you call it.
* Connect the application to your database
  - Drill down into {Working DIR}\src\main\resources\application.yaml
  - add username password and DB name to the ENV variables in spring: dataSource: found on line 17
* In a Terminal of your choosing cd into this project and RUN `mvn jetty:run`
* Once FHIR is running using the browser of your choice look up `localhost:8080`, this will direct you to the FHIR GUI where you can add, search, update ,ect your resources.
* at the bottom of the home page you will see a text box that will accept bundles, with a transaction button to the left. paste any one of the bundles from the ResourceExample folder found in this repository and click transaction. 
* You should now have a handful of resource in your FHIR server that will be stored in your database, using the search functionality found by clicking on the resource you want to search by you should now be able to view all resources except those of type observability that are of observation type "Laboratory"
