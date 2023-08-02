package com.openclassrom.mediscreen.repositories;

import com.openclassrom.mediscreen.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientsRepository extends JpaRepository<Patient, Integer> {

    List<Patient> findByNom(String nom);
}

