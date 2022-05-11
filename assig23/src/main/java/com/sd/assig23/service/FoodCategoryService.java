package com.sd.assig23.service;

import com.sd.assig23.dto.model.FoodCategory;
import com.sd.assig23.repository.FoodCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.logging.FileHandler;

@Service
public class FoodCategoryService {

    private java.util.logging.Logger logger;

    public FoodCategoryService(){
        logger =  java.util.logging.Logger.getLogger(this.getClass().getName());
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler(this.getClass().getName()+".log");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.addHandler(fileHandler);
    }

    @Autowired
    private FoodCategoryRepository repository;

    /**
     * Finds category by id
     *
     * @param id of the FoodCategory
     * @return FoodCategory entity
     */
    public FoodCategory findById(Long id){
        logger.info("got by id: "+id);
        return repository.getById(id);
    }
}
