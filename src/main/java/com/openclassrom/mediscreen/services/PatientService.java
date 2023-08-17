package com.openclassrom.mediscreen.services;


import com.openclassrom.mediscreen.exception.PatientNotFoundException;
import com.openclassrom.mediscreen.model.Patient;

import java.util.List;

public interface PatientService {

    Patient savePatient(Patient patient);


    List<Patient> listAll();

    Patient getPatient(Integer id) throws PatientNotFoundException;

    List<Patient> findByNom(String nom);

    Patient updatePatient(Integer id, Patient patient) throws PatientNotFoundException;

    String deletePatient(Integer id);

    List<Patient> getPatientByIds(List<String> patientIds);

}
