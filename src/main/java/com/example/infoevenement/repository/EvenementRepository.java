package com.example.infoevenement.repository;

import com.example.infoevenement.dao.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementRepository extends JpaRepository<Evenement, String> {

}