package com.example.infoevenement.repository;

import com.example.infoevenement.dao.Periode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodeRepository extends JpaRepository<Periode, String> {
}
