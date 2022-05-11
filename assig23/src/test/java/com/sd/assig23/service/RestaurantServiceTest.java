package com.sd.assig23.service;

import com.sd.assig23.dto.RestaurantDTO;
import com.sd.assig23.dto.model.Restaurant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantServiceTest {

    @Test
    void fromDTO() {
        RestaurantService restaurantService = new RestaurantService();
        RestaurantDTO restaurantDTO = new RestaurantDTO("Name", "desc", "address");
        Restaurant restaurant = restaurantService.fromDTO(restaurantDTO);
        assertEquals(restaurantDTO.getName(),restaurant.getName());
        assertEquals(restaurantDTO.getAddress(),restaurant.getAddress());
        assertEquals(restaurantDTO.getDescription(),restaurant.getDescription());
    }

    @Test
    void toDTO() {
        RestaurantService restaurantService = new RestaurantService();
        Restaurant restaurant = new Restaurant("Name", "desc", "address");
        RestaurantDTO restaurantDTO = restaurantService.toDTO(restaurant);
        assertEquals(restaurantDTO.getName(),restaurant.getName());
        assertEquals(restaurantDTO.getAddress(),restaurant.getAddress());
        assertEquals(restaurantDTO.getDescription(),restaurant.getDescription());
    }

    @Test
    void toDTOList() {
        RestaurantService restaurantService = new RestaurantService();
        List<Restaurant> entityList = new ArrayList<>();
        entityList.add(new Restaurant("Name1", "desc1", "address1"));
        entityList.add(new Restaurant("Name2", "desc2", "address2"));
        entityList.add(new Restaurant("Name3", "desc3", "address3"));

        List<RestaurantDTO> dtoList = restaurantService.toDTOList(entityList);

        assertEquals(dtoList.size(),3);
        assertEquals(dtoList.get(0).getName(),"Name1");
    }
}