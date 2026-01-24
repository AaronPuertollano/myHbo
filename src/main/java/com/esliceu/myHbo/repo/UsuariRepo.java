package com.esliceu.myHbo.repo;

import com.esliceu.myHbo.model.Person;
import com.esliceu.myHbo.model.Usuari;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuariRepo extends JpaRepository<Usuari, Integer> {

    Optional<Usuari> findByNomUsuari(String nomUsuari);

    boolean existsByNomUsuari(String nomUsuari);

    boolean existsByCorreuElectronic(String correuElectronic);
}
