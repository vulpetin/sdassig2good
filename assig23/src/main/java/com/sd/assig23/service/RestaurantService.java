package com.sd.assig23.service;

import com.sd.assig23.dto.FoodDTO;
import com.sd.assig23.dto.RestaurantDTO;
import com.sd.assig23.dto.model.Restaurant;
import com.sd.assig23.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;

@Service
public class RestaurantService {

    private java.util.logging.Logger logger;

    public RestaurantService(){
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
    private RestaurantRepository repository;

    /**
     * resturns all RestaurantDTOs
     *
     * @return list of all RestaurantDTOs
     */
    public List<RestaurantDTO> findAll(){
        logger.info("found all");
        return toDTOList(repository.findAll());
    }

    /**
     * returns the food list of a restaurant
     *
     * @param id of the restaurant
     * @return food list
     */
    public List<FoodDTO> getMenu(Long id){
        FoodService foodService = new FoodService();
        Restaurant restaurant = findById(id);
        logger.info("menu for: "+id);
        return foodService.toDTOList(restaurant.getFoodList());
    }

    /**
     * Converts from DTO to entity
     *
     * @param dto to be converted
     * @return the entity
     */
    public Restaurant fromDTO(RestaurantDTO dto){
        logger.info("fromDTO: "+ dto.getName());
        return new Restaurant(
                dto.getName(),
                dto.getAddress(),
                dto.getDescription()
        );
    }

    /**
     * Converts from Entity to Dto
     *
     * @param restaurant to be converted
     * @return the DTO
     */
    public RestaurantDTO toDTO (Restaurant restaurant){
        logger.info("toDTO: " + restaurant.getName());
        return new RestaurantDTO(restaurant.getId(),restaurant.getName(),restaurant.getDescription(),restaurant.getAddress());
    }

    /**
     * Converts an entity list to DTOs
     *
     * @param restaurantList List of food entities
     * @return a list of DTOs
     */
    public List<RestaurantDTO> toDTOList (List<Restaurant> restaurantList){
        List<RestaurantDTO> dtoList = new ArrayList<>();
        for(Restaurant restaurant : restaurantList){
            dtoList.add(toDTO(restaurant));
        }
        logger.info("toDTOList of size: "+dtoList.size());
        return dtoList;
    }

    /**
     * Finds Restaurant by id
     *
     * @param id of the Restaurant
     * @return Restaurant entity
     */
    public Restaurant findById(Long id){
        logger.info("findById: "+id);
        return repository.getById(id);
    }

    /**
     * Saves to repository
     *
     * @param dto to be saved
     */
    public void save(RestaurantDTO dto){
        repository.save(fromDTO(dto));
        logger.info("saved: "+dto.getName());
    }
}
