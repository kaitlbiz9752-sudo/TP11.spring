package com.example.TP.__Spring_Data_REST.repositories;

import com.example.TP.__Spring_Data_REST.entities.Compte;
import com.example.TP.__Spring_Data_REST.entities.TypeCompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "comptes", collectionResourceRel = "comptes", itemResourceRel = "compte")
public interface CompteRepository extends JpaRepository<Compte, Long> {

    // /api/comptes/search/byType?t=EPARGNE
    @RestResource(path = "byType")
    List<Compte> findByType(@Param("t") TypeCompte type);

    // (optionnel) version paginée/triable :
    // Page<Compte> findByType(@Param("t") TypeCompte type, Pageable pageable);
}