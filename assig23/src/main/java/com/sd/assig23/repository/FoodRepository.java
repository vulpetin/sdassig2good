package com.sd.assig23.repository;

import com.sd.assig23.dto.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food,Long> {
}
