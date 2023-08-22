package com.openclassrom.mediscreen.services.Impl;

import com.openclassrom.mediscreen.exception.PatientNotFoundException;
import com.openclassrom.mediscreen.model.Patient;
import com.openclassrom.mediscreen.repositories.PatientsRepository;
import com.openclassrom.mediscreen.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientsRepository patientsRepository;

    /**
     * This method is for saving a patient to a DB
     * @param patient
     * @return
     */
    public Patient savePatient(Patient patient) {
        return patientsRepository.save(patient);
    }

    /**
     * This method is for getting the list of all patients saved in a Db
     * @return
     */
    public List<Patient> listAll() {
        return patientsRepository.findAll();
    }

    /**
     * This method is for getting one patient via it's id
     * @param id
     * @return
     * @throws PatientNotFoundException
     */
    public Patient getPatient(Integer id) throws PatientNotFoundException {
        return patientsRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient non trouvé avec l'ID : " + id));
    }

    /**
     * This method is for getting a list o patients via a name
     * @param nom
     * @return
     */
    @Override
    public List<Patient> findByNom(String nom) {
        return patientsRepository.findByNom(nom);
    }

    /**
     * This method is for updating a patient via it's id
     * @param id
     * @param patientDetails
     * @return
     * @throws PatientNotFoundException
     */
    public Patient updatePatient(Integer id, Patient patientDetails) throws PatientNotFoundException {
        return patientsRepository.findById(id)
                .map(patient -> {
                    patient.setNom(patientDetails.getNom());
                    patient.setPrenom(patientDetails.getPrenom());
                    patient.setGenre(patientDetails.getGenre());
                    patient.setAdressePostale(patientDetails.getAdressePostale());
                    patient.setNumeroDeTelephone(patientDetails.getNumeroDeTelephone());
                    return patientsRepository.save(patient);
                }).orElseThrow(() -> new PatientNotFoundException("Patient non trouvé avec l'ID : " + id));

    }

    /**
     * This method is for deleting a patient based on it's id
     * @param id
     * @return
     */
    public String deletePatient(Integer id) {
        patientsRepository.deleteById(id);
        return "patient supprimé";
    }

    /**
     * This method if for getting a list of patients based on a list of id
     * @param patientIds
     * @return
     */
    @Override
    public List<Patient> getPatientByIds(List<String> patientIds) {
        List<Integer> patients = patientIds.stream().map(patient -> Integer.valueOf(patient)).collect(Collectors.toList());
        return patientsRepository.findByIdsIn(patients);
    }
}
