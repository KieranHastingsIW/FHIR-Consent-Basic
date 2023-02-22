
package ca.uhn.fhir.letsbuild.server;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.annotation.RequiredParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.param.ReferenceParam;
import ca.uhn.fhir.rest.server.provider.HashMapResourceProvider;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Consent;

import java.util.List;
import java.util.stream.Collectors;

public class SearchableConsentHashMapResourceProvider  extends HashMapResourceProvider<Consent> {

	public SearchableConsentHashMapResourceProvider(FhirContext theFhirContext) {
		super(theFhirContext, Consent.class);
	}


	@Search
	public List<IBaseResource> searchByPatient(@RequiredParam(name="patientId") ReferenceParam theParam) {
		String patientId = theParam.getIdPart();
		return super
			.getStoredResources()
			.stream()
			.filter(t-> patientId.equals(t.getPatient().getReferenceElement().getIdPart()))
			.collect(Collectors.toList());

	}

	

}
