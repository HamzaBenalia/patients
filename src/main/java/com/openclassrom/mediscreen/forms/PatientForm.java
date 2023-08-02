package com.openclassrom.mediscreen.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientForm {

    private String nom;

    private String prenom;

    private String numeroDeTelephone;

    private String adressePostale;

    private String genre;

    private String dateDeNaissance;
}
