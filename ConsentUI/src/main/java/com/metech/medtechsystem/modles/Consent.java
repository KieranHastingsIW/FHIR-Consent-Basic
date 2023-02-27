package com.metech.medtechsystem.modles;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.UniqueConstraint;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name="CONSENT_TABLE", uniqueConstraints={@UniqueConstraint(columnNames = {"PATIENT_ID", "CONSENT_NAME", "CONSENT_TYPE_URL"})
}) 
public class Consent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONSENT_ID", nullable = false)
    private Long id;

    @Column(name = "PATIENT_ID", nullable = false)
    private Long patientId;

    @Column(name="CONSENT_TYPE_URL", nullable = false)
    private String consentTypeUrl;

    @Column(name="CONSENT_NAME", nullable = false)
    private String consentName;

    @Column(name="GIVEN", nullable = false)
    private boolean given;

 }
