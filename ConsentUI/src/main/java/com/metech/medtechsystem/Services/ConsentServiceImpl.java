package com.metech.medtechsystem.Services;


import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.metech.medtechsystem.Repository.ConsentRepository;
import com.metech.medtechsystem.modles.Consent;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConsentServiceImpl implements ConsentService{


    private ConsentRepository consentRepository;
 
    @Override
    public void addConsent(Consent consent){
        consentRepository.save(consent);
    }

    @Override
    public List<List<String>> getConsentByPatientId(Long id){
        List<Consent> consentList =  consentRepository.findAllByPatientId(id);
        List<List<String>> returnList = new ArrayList<>();
        for (Consent i : consentList){
            List<String> addList = new ArrayList<>();
            addList.add(i.getConsentTypeUrl());
            addList.add(i.getConsentName());
            returnList.add(addList);
        }
        System.out.println(returnList);
        return returnList;
    }

    
}