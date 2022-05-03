package com.sd.assig23.service;

import com.sd.assig23.dto.FoodDTO;
import com.sd.assig23.dto.RestaurantDTO;
import com.sd.assig23.dto.model.Restaurant;
import com.sd.assig23.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository repository;

    public List<RestaurantDTO> findAll(){
        return toDTOList(repository.findAll());
    }

    public List<FoodDTO> getMenu(Long id){
        FoodService foodService = new FoodService();
        Restaurant restaurant = findById(id);
        return foodService.toDTOList(restaurant.getFoodList());
    }

    public Restaurant fromDTO(RestaurantDTO dto){
        return new Restaurant(
                dto.getName(),
                dto.getAddress(),
                dto.getDescription()
        );
    }

    public RestaurantDTO toDTO (Restaurant restaurant){
        return new RestaurantDTO(restaurant.getId(),restaurant.getName(),restaurant.getDescription(),restaurant.getAddress());
    }

    public List<RestaurantDTO> toDTOList (List<Restaurant> restaurantList){
        List<RestaurantDTO> dtoList = new ArrayList<>();
        for(Restaurant restaurant : restaurantList){
            dtoList.add(toDTO(restaurant));
        }
        return dtoList;
    }

    public Restaurant findById(Long id){
        return repository.getById(id);
    }

    public void save(RestaurantDTO dto){
        repository.save(fromDTO(dto));
    }
}
