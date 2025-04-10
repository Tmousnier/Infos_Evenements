package com.example.infoevenement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.infoevenement.dao.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> findByName(String libelle);

}
