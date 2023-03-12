package com.metech.medtechsystem.Services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

import com.metech.medtechsystem.modles.Consent;




public interface ConsentService {

        void addConsent(Consent consent);
        List<List<String>> getConsentByPatientId(Long id);

}
