package com.sd.assig23.service;

import com.sd.assig23.dto.FoodDTO;
import com.sd.assig23.dto.model.Food;
import com.sd.assig23.dto.model.FoodCategory;
import com.sd.assig23.dto.model.Restaurant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class FoodServiceTest {


    @Spy
    Food foodEntity = new Food();

    @Test
    void toDTO() {

        FoodCategory mockFoodCategory = mock(FoodCategory.class);

        FoodService service = new FoodService();

        foodEntity.setName("A Name");
        //Mockito.verify(mockFoodEntity).setName("A Name");

        when(mockFoodCategory.getName()).thenReturn("CategoryName");
        foodEntity.setFoodCategory(mockFoodCategory);

        FoodDTO foodDTO = service.toDTO(foodEntity);
        assertEquals("A Name", foodEntity.getName());

        assertEquals("A Name",foodDTO.getName());

    }



    @Spy
    FoodDTO foodDTO = new FoodDTO();

    @Test
    void fromDto() {

        FoodCategoryService foodCategoryService = Mockito.mock(FoodCategoryService.class);
        RestaurantService restaurantService = Mockito.mock(RestaurantService.class);
        when(foodCategoryService.findById(1L)).thenReturn(new FoodCategory());
        when(restaurantService.findById(1L)).thenReturn(new Restaurant());

        FoodService foodService = new FoodService(foodCategoryService,restaurantService);
        foodDTO = new FoodDTO(
                "Name",
                "Description",
                55F,
                "1"
        );


        Food foodEntity = foodService.FromDto(foodDTO, "1");

        assertEquals(foodEntity.getName(),foodDTO.getName());


    }

    @Test
    void save() {
    }

    @Test
    void toDTOList() {

        Restaurant mockRestaurant = Mockito.mock(Restaurant.class);
        FoodCategory mockFoodCategory = Mockito.mock(FoodCategory.class);
        when(mockFoodCategory.getName()).thenReturn("FoodCategoryTest");

        FoodService service = new FoodService();

        List<Food> entityList = new ArrayList<>();

        entityList.add(new Food("food1", "description1", 1F, mockFoodCategory, mockRestaurant));
        entityList.add(new Food("food2", "description2", 2F, mockFoodCategory, mockRestaurant));
        entityList.add(new Food("food3", "description3", 3F, mockFoodCategory, mockRestaurant));

        List<FoodDTO> dtoList = service.toDTOList(entityList);

        assertEquals(dtoList.size(),3);
        assertEquals(dtoList.get(0).getCategory(), "FoodCategoryTest");
    }
}