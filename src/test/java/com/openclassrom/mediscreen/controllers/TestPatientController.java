package com.openclassrom.mediscreen.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrom.mediscreen.model.Patient;
import com.openclassrom.mediscreen.services.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class TestPatientController {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void createPatientTest() throws Exception {
        Patient patient = new Patient();
        patient.setNom("benalia");
        patient.setPrenom("hamza");
        patient.setId(1);
        patient.setGenre("Homme");
        patient.setAdressePostale("toulouse");
        patient.setNumeroDeTelephone("0766764619");
        patient.setDateDeNaissance("17/02/1995");

        when(patientService.savePatient(any(Patient.class))).thenReturn(patient);

        mockMvc.perform(MockMvcRequestBuilders.post("/patient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(patient)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(patient)));

        verify(patientService, times(1)).savePatient(any(Patient.class));
    }

    @Test
    void getPatientsTest() throws Exception {
        List<Patient> patients = new ArrayList<>();
        when(patientService.listAll()).thenReturn(patients);

        mockMvc.perform(MockMvcRequestBuilders.get("/patient")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(patients)));

        verify(patientService, times(1)).listAll();
    }

    @Test
    void getPatientTest() throws Exception {
        Integer id = 1;
        Patient patient = new Patient();
        when(patientService.getPatient(id)).thenReturn(patient);

        mockMvc.perform(MockMvcRequestBuilders.get("/patient/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(patient)));

        verify(patientService, times(1)).getPatient(id);
    }

    @Test
    void getPatientsByNomTest() throws Exception {
        String nom = "nomTest";
        List<Patient> patients = new ArrayList<>();
        when(patientService.findByNom(nom)).thenReturn(patients);

        mockMvc.perform(MockMvcRequestBuilders.get("/patient/find/" + nom)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(patients)));

        verify(patientService, times(1)).findByNom(nom);
    }

    @Test
    void updatePatientTest() throws Exception {
        Integer id = 1;
        Patient patient = new Patient();
        when(patientService.updatePatient(id, patient)).thenReturn(patient);

        mockMvc.perform(MockMvcRequestBuilders.post("/patient/update/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(patient)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(patient)));

        verify(patientService, times(1)).updatePatient(id, patient);
    }

    @Test
    void deletePatientTest() throws Exception {
        Integer id = 1;
        String result = "patient supprimé";
        when(patientService.deletePatient(id)).thenReturn(result);

        mockMvc.perform(MockMvcRequestBuilders.delete("/patient/delete/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(result));

        verify(patientService, times(1)).deletePatient(id);
    }


}
