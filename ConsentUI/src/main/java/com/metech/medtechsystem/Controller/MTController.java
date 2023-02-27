package com.metech.medtechsystem.Controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.metech.medtechsystem.Services.ConsentService;
import com.metech.medtechsystem.modles.Consent;


import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MTController {

    ConsentService consentService;
    

    /// GUI COMPONENTS
    @GetMapping("/")
    public String getMedTech1(Model model){
        model.addAttribute("consent", new Consent());
        return "SystemHomePage";
    }

    @PostMapping("/addConsent")
    public String postMedTech1(@ModelAttribute Consent consent, Model model) throws MalformedURLException, IOException{
        consentService.addConsent(consent);
        model.addAttribute("consent", new Consent());
        return "SystemHomePage";
    }


    /// API COMPONENTS
    @GetMapping("/getPatientConsent/{id}")
    public ResponseEntity<List<List<String>>> getConsent(@PathVariable Long id){
        System.out.println(consentService.getConsentByPatientId(id));
        List<List<String>> PCList = consentService.getConsentByPatientId(id);
        for (List<String> c : PCList ){
            System.out.println(c);
        }
        return new ResponseEntity<>(consentService.getConsentByPatientId(id),HttpStatus.OK);
    }


}
