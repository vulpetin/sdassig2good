package com.sd.assig23.service;

import com.sd.assig23.dto.FoodDTO;
import com.sd.assig23.dto.model.Food;
import com.sd.assig23.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService {

    @Autowired
    FoodRepository repository;

    @Autowired
    FoodCategoryService categoryService;

    @Autowired
    RestaurantService restaurantService;

    public FoodDTO toDTO (Food food){

        return new FoodDTO(food.getId(), food.getName(),food.getDescription(),food.getPrice(),food.getFoodCategory().getName());
    }

    public Food FromDto (FoodDTO dto, String restaurantId){

        return new Food(
                dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
                categoryService.findById(Long.parseLong(dto.getCategory())),
                restaurantService.findById(Long.parseLong(restaurantId))
        );
    }

    public void save(Food food){
        repository.save(food);
    }

    public List<FoodDTO> toDTOList (List<Food> foodList){
        List<FoodDTO> dtoList = new ArrayList<FoodDTO>() ;
        for (Food food : foodList){
            dtoList.add(toDTO(food));
        }
        return dtoList;

    }

}
