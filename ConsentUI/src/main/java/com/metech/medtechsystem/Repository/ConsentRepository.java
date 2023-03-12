package com.metech.medtechsystem.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metech.medtechsystem.modles.Consent;


public interface ConsentRepository extends JpaRepository<Consent, Long>{
    List<Consent> findAllByPatientId(Long patientId);
}
