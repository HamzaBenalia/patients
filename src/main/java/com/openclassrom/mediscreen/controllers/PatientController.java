package com.openclassrom.mediscreen.controllers;
import com.openclassrom.mediscreen.exception.PatientNotFoundException;
import com.openclassrom.mediscreen.model.Patient;
import com.openclassrom.mediscreen.services.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patient")
@Slf4j
public class PatientController {


    @Autowired
    private PatientService patientService;

    @Operation(
            description = "Create patient endPoint"
    )
    @PostMapping
    public Patient createPatient(@Valid @RequestBody Patient patient) {
        log.info("Création du patient: {}", patient);
        return patientService.savePatient(patient);
    }

    @Operation(
            description = "get the list of Patients endPoint"
    )
    @GetMapping
    public List<Patient> getPatients() {
        return patientService.listAll();
    }

    @Operation(
            description = "get one patient by id endPoint"
    )
    @GetMapping("/{id}")
    public Patient getPatient(@PathVariable Integer id) throws PatientNotFoundException {
        log.info("Lecture du patient: {}", id);
        return patientService.getPatient(id);
    }

    @Operation(
            description = "get patients by name endPoint"
    )
    @GetMapping("/find/{nom}")
    public List<Patient> getPatients(@PathVariable String nom) {
        return patientService.findByNom(nom);
    }

    @Operation(
            description = "update a patient by id endPoint"
    )
    @PostMapping("/update/{id}")
    public Patient updatePatient(@Valid @PathVariable Integer id, @RequestBody Patient patient) throws PatientNotFoundException {
        log.info("mise à jour du patient : {}", patient);
        return patientService.updatePatient(id, patient);
    }

    @Operation(
            description = "delete a patient by id endPoint"
    )
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        return patientService.deletePatient(id);
    }

    @Operation(
            description = "Get patients by their Ids"
    )

    @GetMapping("/findByPatientIds")
    public List<Patient> getPatientByIds(@RequestParam List<String> patientIds) {
        return patientService.getPatientByIds(patientIds);
    }

}
