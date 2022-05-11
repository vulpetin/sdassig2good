package com.sd.assig23.service;

import com.sd.assig23.dto.FoodDTO;
import com.sd.assig23.dto.model.Food;
import com.sd.assig23.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;


@Service
public class FoodService {

    private java.util.logging.Logger logger;

    public FoodService (){
        logger =  java.util.logging.Logger.getLogger(this.getClass().getName());
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler(this.getClass().getName()+".log");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.addHandler(fileHandler);
    }

    public FoodService(FoodCategoryService categoryService, RestaurantService restaurantService) {
        super();
        this.categoryService = categoryService;
        this.restaurantService = restaurantService;
    }


    @Autowired
    FoodRepository repository;

    @Autowired
    FoodCategoryService categoryService;

    @Autowired
    RestaurantService restaurantService;


    /**
     * Converts from Entity to Dto
     *
     * @param food to be converted
     * @return the DTO
     */
    public FoodDTO toDTO (Food food){
        logger.info("toDTO: "+food.getName());

        return new FoodDTO(
                food.getId(),
                food.getName(),
                food.getDescription(),
                food.getPrice(),
                food.getFoodCategory().getName()
        );
    }

    /**
     * Converts from DTO to entity
     *
     * @param dto to be converted
     * @param restaurantId id (String) of the restaurant it belongs to
     * @return the entity
     */
    public Food FromDto (FoodDTO dto, String restaurantId){

        logger.info("fromDTO: "+dto.getName());
        return new Food(
                dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
                categoryService.findById(Long.parseLong(dto.getCategory())),
                restaurantService.findById(Long.parseLong(restaurantId))
        );
    }

    /**
     * Saves to repository
     *
     * @param food to be saved
     */
    public void save(Food food){
        logger.info("saved: "+food.getName());
        repository.save(food);
    }

    /**
     * Converts an entity list to DTOs
     *
     * @param foodList List of food entities
     * @return a list of DTOs
     */
    public List<FoodDTO> toDTOList (List<Food> foodList){
        List<FoodDTO> dtoList = new ArrayList<FoodDTO>() ;
        for (Food food : foodList){
            dtoList.add(toDTO(food));
        }
        logger.info("list of size: "+dtoList.size());
        return dtoList;

    }

}
