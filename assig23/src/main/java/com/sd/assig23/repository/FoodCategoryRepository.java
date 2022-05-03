package com.sd.assig23.repository;

import com.sd.assig23.dto.model.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory,Long> {
}
