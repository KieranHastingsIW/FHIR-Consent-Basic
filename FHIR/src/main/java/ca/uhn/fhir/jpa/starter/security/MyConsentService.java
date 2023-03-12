package ca.uhn.fhir.jpa.starter.security;

import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import java.io.IOException;

// import ca.uhn.fhir.jpa.starter.GUI.UIAPI;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.server.exceptions.BaseServerResponseException;
import ca.uhn.fhir.rest.server.interceptor.consent.ConsentOutcome;
import ca.uhn.fhir.rest.server.interceptor.consent.IConsentContextServices;
import ca.uhn.fhir.rest.server.interceptor.consent.IConsentService;
import java.util.List;

public class MyConsentService implements IConsentService {
   /**
   * Invoked once at the start of every request
   */
   @Override
   public ConsentOutcome startOperation(RequestDetails theRequestDetails, IConsentContextServices theContextServices) {
      // This means that all requests should flow through the consent service
      // This has performance implications - If you know that some requests
      // don't need consent checking it is a good idea to return
      // ConsentOutcome.AUTHORIZED instead for those requests.
      System.out.println("METHOD NAME: startOperation");
      return ConsentOutcome.PROCEED;
   }
   /**
   * Can a given resource be returned to the user?
   */
   @Override
   public ConsentOutcome canSeeResource(RequestDetails theRequestDetails, IBaseResource theResource, IConsentContextServices theContextServices) {
      System.out.println("METHOD NAME: canSeeResource");
      // In this basic example, we will filter out lab results so that they
      // are never disclosed to the user. A real interceptor might do something
      // more nuanced.
      // Removes lab results from 
      if (theResource instanceof Observation) {
         Observation obs = (Observation)theResource;
         if (obs.getCategoryFirstRep().hasCoding("http://terminology.hl7.org/CodeSystem/observation-category", "laboratory")) {
            return ConsentOutcome.REJECT;
         }
      }
      // Otherwise, allow the
      return ConsentOutcome.PROCEED;
   } 
   /**
   * Modify resources that are being shown to the user
   */
   @Override
   public ConsentOutcome willSeeResource(RequestDetails theRequestDetails, IBaseResource theResource, IConsentContextServices theContextServices){
      // Don't return the subject for Observation resources
      System.out.println("METHOD NAME: willSeeResource");
      // Code to check if resource is PAtient and pass patient ID to the Consent manager
      // Where it is to return all consnet objetcs that the patient has submitted
      // through theGUI 
      // ************************************************************************
         // UIAPI uiapi = new UIAPI();
         // if (theResource instanceof Observation) {
         // Patient patient = ((Patient) theResource.getPatientIdPart()); ????????? 
         // // List<List<String>> consentList = uiapi.getPatientConsent(patient.getId()); }
         // if (theResource instanceof Patient) {
         //       Patient patient = ((Patient) theResource);
         //       try{
         //       List<List<String>> consentList = uiapi.getPatientConsent(patient.getIdPart()); 
         //       for (List<String> conUrl : consentList){
         //          switch(conUrl.get(1)){
         //             case "Gender":
         //                System.out.print("Gender");
         //                break;
         //          }
         //       }
         //       } catch (Exception e){System.out.println("IOException");}
      // ************************************************************************
      if (theResource instanceof Observation) {
         Observation obs = (Observation)theResource;
            if (obs.getCategoryFirstRep().hasCoding("http://terminology.hl7.org/CodeSystem/observation-category", "laboratory")) {
               return ConsentOutcome.REJECT;
            }
      }
      return ConsentOutcome.AUTHORIZED;
   }

   @Override
   public void completeOperationSuccess(RequestDetails theRequestDetails, IConsentContextServices theContextServices) {
      // We could write an audit trail entry in here
   }

   @Override
   public void completeOperationFailure(RequestDetails theRequestDetails, BaseServerResponseException theException, IConsentContextServices theContextServices) {
      // We could write an audit trail entry in here
   }


}
