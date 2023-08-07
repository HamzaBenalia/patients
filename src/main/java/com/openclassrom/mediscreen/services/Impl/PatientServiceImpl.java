package com.openclassrom.mediscreen.services.Impl;

import com.openclassrom.mediscreen.exception.PatientNotFoundException;
import com.openclassrom.mediscreen.model.Patient;
import com.openclassrom.mediscreen.repositories.PatientsRepository;
import com.openclassrom.mediscreen.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientsRepository patientsRepository;

    // Créer un nouveau patient
    public Patient savePatient(Patient patient) {
        return patientsRepository.save(patient);
    }

    // Obtenir une liste de tous les patients
    public List<Patient> listAll() {
        return patientsRepository.findAll();
    }

    // Obtenir un patient spécifique par son ID
    public Patient getPatient(Integer id) throws PatientNotFoundException {
        return patientsRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient non trouvé avec l'ID : " + id));
    }

    @Override
    public List<Patient> findByNom(String nom) {
        return patientsRepository.findByNom(nom);
    }

    // Mettre à jour les informations d'un patient spécifique
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

    // Supprimer un patient spécifique
    public String deletePatient(Integer id) {
        patientsRepository.deleteById(id);
        return "patient supprimé";
    }
}
