package com.openclassrom.mediscreen.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", updatable = false)
    private Integer id;

    @NotBlank(message="nom est obligatoire")
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotBlank(message="le prenom est obligatoire")
    @Column(name = "prenom", nullable = false)
    private String prenom;

    @NotBlank(message = "la date de naissance est obligatoire")
    @Column(name = "date_de_naissance", nullable = false)
    private String dateDeNaissance;

    @NotBlank(message = "le genre est obligatoire")
    @Column(name = "genre", nullable = false)
    private String genre;

    @NotBlank(message = "adresse postale est obligatoire")
    @Column(name = "addresse_postale", nullable = false)
    private String adressePostale;

    @NotBlank(message = "numero de telephone est obligatoire")
    @Column(name = "numero_de_telephone", nullable = false)
    private String numeroDeTelephone;

}
