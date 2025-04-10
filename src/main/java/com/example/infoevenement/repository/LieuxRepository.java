package com.example.infoevenement.repository;

import com.example.infoevenement.dao.Lieux;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LieuxRepository extends JpaRepository<Lieux, String> {

}
