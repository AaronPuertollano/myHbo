package com.esliceu.myHbo.service;

import com.esliceu.myHbo.filter.PasswordConverter;
import com.esliceu.myHbo.model.Usuari;
import com.esliceu.myHbo.repo.UsuariRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UsuariService {

    @Autowired
    UsuariRepo usuariRepo;

    public boolean validarLogin(String username, String password) {
        Optional<Usuari> usuariOpt = usuariRepo.findByNomUsuari(username);

        if (usuariOpt.isPresent()) {
            try {
                Usuari usuari = usuariOpt.get();
                String passwordHash = PasswordConverter.hashPassword(password);
                return passwordHash.equals(usuari.getContrasenya());
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Error al validar la contrasenya", e);
            }
        }

        return false;
    }

    public void registrar(String username, String password, String email) {

        if (usuariRepo.existsByNomUsuari(username)) {
            throw new RuntimeException("El nom d'usuari ja existeix");
        }

        if (usuariRepo.existsByCorreuElectronic(email)) {
            throw new RuntimeException("El correu electrònic ja existeix");
        }

        try {
            String passwordHash = PasswordConverter.hashPassword(password);

            Usuari usuari = new Usuari(username, passwordHash, email);
            usuariRepo.save(usuari);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al xifrar la contrasenya", e);
        }
    }
}
