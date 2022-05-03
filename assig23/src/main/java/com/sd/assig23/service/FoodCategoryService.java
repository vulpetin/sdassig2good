package com.sd.assig23.service;

import com.sd.assig23.dto.model.FoodCategory;
import com.sd.assig23.repository.FoodCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodCategoryService {

    @Autowired
    private FoodCategoryRepository repository;

    public FoodCategory findById(Long id){
        return repository.getById(id);
    }
}
