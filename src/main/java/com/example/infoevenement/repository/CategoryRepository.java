package com.example.infoevenement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.infoevenement.dao.Category;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
}
