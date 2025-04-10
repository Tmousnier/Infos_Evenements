package com.example.infoevenement.repository;

import com.example.infoevenement.dao.Evenement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EvenementRepository extends JpaRepository<Evenement, String>, QuerydslPredicateExecutor<Evenement> {

    @Query(nativeQuery = true, value = "SELECT * FROM evenement WHERE libelle LIKE CONCAT('%', :keyword, '%')")
    Page<Evenement> searchByLibelle(@Param("keyword") String keyword, Pageable pageable);

}