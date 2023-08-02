package com.openclassrom.mediscreen.controllers;
import com.openclassrom.mediscreen.exception.PatientNotFoundException;
import com.openclassrom.mediscreen.model.Patient;
import com.openclassrom.mediscreen.services.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patient")
@Slf4j
public class PatientController {


    @Autowired
    private PatientService patientService;


    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        log.info("Création du patient: {}", patient);
        return patientService.savePatient(patient);
    }

    @GetMapping
    public List<Patient> getPatients() {
        return patientService.listAll();
    }

    @GetMapping("/{id}")
    public Patient getPatient(@PathVariable Integer id) throws PatientNotFoundException {
        log.info("Lecture du patient: {}", id);
        return patientService.getPatient(id);
    }

    @GetMapping("/find/{nom}")
    public List<Patient> getPatients(@PathVariable String nom) {
        return patientService.findByNom(nom);
    }

    @PutMapping("/update/{id}")
    public Patient updatePatient(@PathVariable Integer id, @RequestBody Patient patient) throws PatientNotFoundException {
        log.info("mise à jour du patient : {}", patient);
        return patientService.updatePatient(id, patient);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return patientService.deletePatient(id);
    }
}
