# FHIR CONSNET INTERCEPTOR ON HAPI-FHIR-JPA-STARTER WITH POSTGRES-DB

Requirements 

* Using a terminal of your choice direct youself to this directory and run `docker-compose up --build`
* Once FHIR is running using the browser of your choice look up `localhost:8080`, this will direct you to the FHIR GUI where you can add, search, update ,ect your resources.
* at the bottom of the home page you will see a text box that will accept bundles, with a transaction button to the left. paste any one of the bundles from the ResourceExample folder found in this repository and click transaction. 
* Currently the consent interceptor is set up to block information that it doesnt need too, this will be adjusted later. But for now the transaction will throw a few errors but they do not effect the next steps. 

* You should now have a handful of resource in your FHIR server that will be stored in your database, using the search functionality found by clicking on the resource you want to search by you should now be able to view all resources except those of type observability that are of observation type "Laboratory"
