package com.esliceu.myHbo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuaris")
public class Usuari {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuari")
    private Integer id;

    @Column(name = "nom_usuari", nullable = false, unique = true, length = 50)
    private String nomUsuari;

    @Column(name = "contrasenya", nullable = false, length = 255)
    private String contrasenya;

    @Column(name = "correu_electronic", nullable = false, unique = true, length = 100)
    private String correuElectronic;


    public Usuari() {
    }

    public Usuari(String nomUsuari, String contrasenya, String correuElectronic) {
        this.nomUsuari = nomUsuari;
        this.contrasenya = contrasenya;
        this.correuElectronic = correuElectronic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomUsuari() {
        return nomUsuari;
    }

    public void setNomUsuari(String nomUsuari) {
        this.nomUsuari = nomUsuari;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getCorreuElectronic() {
        return correuElectronic;
    }

    public void setCorreuElectronic(String correuElectronic) {
        this.correuElectronic = correuElectronic;
    }
}
