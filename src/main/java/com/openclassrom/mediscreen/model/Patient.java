package com.openclassrom.mediscreen.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "date_de_naissance", nullable = false)
    private String dateDeNaissance;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "addresse_postale", nullable = false)
    private String adressePostale;

    @Column(name = "numero_de_telephone", nullable = false)
    private String numeroDeTelephone;

}
