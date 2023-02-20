package ca.uhn.fhir.letsbuild.security;

import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Consent;
import org.hl7.fhir.r4.model.HumanName;
import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.*;
import org.json.*;


import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.server.exceptions.BaseServerResponseException;


import ca.uhn.fhir.rest.server.interceptor.consent.ConsentOutcome;
import ca.uhn.fhir.rest.server.interceptor.consent.IConsentContextServices;
import ca.uhn.fhir.rest.server.interceptor.consent.IConsentService;


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
      // In this basic example, we will filter out lab results so that they
      // are never disclosed to the user. A real interceptor might do something
      // more nuanced.


      if (theResource instanceof Patient) {
         Patient patient = (Patient)theResource;
         HumanName humanName = patient.getNameFirstRep();
         System.out.println("METHOD NAME: canSeeResource");
         System.out.println(humanName.getGivenAsSingleString());
         System.out.println(humanName.getFamily());
      }
      if (theResource instanceof Consent) {
         return ConsentOutcome.REJECT;
         // FhirContext ctx = FhirContext.forR4();
         // IParser parser = ctx.newJsonParser();
         // Consent consent = (Consent)theResource;
         // //fhir to string parser passed to a string to JSON Object parser
         // JSONObject jsonObject = new JSONObject(parser.encodeResourceToString(consent));
         // System.out.println("PATIENT ID AND DISPLAY" + jsonObject.get("patient"));
      }

      // Otherwise, allow the
      return ConsentOutcome.PROCEED;
   }

   /**
   * Modify resources that are being shown to the user
   */
   @Override
   public ConsentOutcome willSeeResource(RequestDetails theRequestDetails, IBaseResource theResource, IConsentContextServices theContextServices) {
      // Don't return the subject for Observation resources
      System.out.println("METHOD NAME: willSeeResource");
      if (theResource instanceof Patient) {
         Patient patient = (Patient)theResource;
         patient.setAddress(null);
         patient.setGender(null);
         patient.setContact(null);
         patient.setTelecom(null);
         patient.setBirthDate(null);
         patient.setDeceased(null);
      }
      if (theResource instanceof Observation) {
         Observation obs = (Observation)theResource;
         obs.setSubject(null);
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






