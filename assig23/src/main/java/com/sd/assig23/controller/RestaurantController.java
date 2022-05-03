package com.sd.assig23.controller;

import com.sd.assig23.dto.FoodDTO;
import com.sd.assig23.dto.RestaurantDTO;
import com.sd.assig23.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class RestaurantController {


    @Autowired
    private RestaurantService service;


    @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantDTO>> restaurants(){
        List<RestaurantDTO> restaurants = service.findAll();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Responded", "RestaurantController");
        return ResponseEntity.accepted().headers(httpHeaders).body(restaurants);
    }

    @GetMapping("/restaurants/menu")
    public ResponseEntity<List<FoodDTO>> menu(@RequestParam String id){

        System.out.println("menu : "+id);
        List<FoodDTO> menu = service.getMenu(Long.parseLong(id));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Responded", "RestaurantController");
        return ResponseEntity.accepted().headers(httpHeaders).body(menu);
    }

    @PostMapping("/restaurants/add")
    public ResponseEntity addRestaurant(@RequestBody RestaurantDTO restaurantDTO){
        System.out.println(restaurantDTO.getName()+restaurantDTO.getId()+restaurantDTO.getAddress()+restaurantDTO.getDescription());
        service.save(restaurantDTO);
        return ResponseEntity.noContent().build();
    }


//    @GetMapping("/restaurantstest")
//    public ResponseEntity<List<Restaurant>> restauranstTest(){
//        List<Restaurant> restaurants = new ArrayList<>();
//
//
//
//        restaurants.add(new Restaurant("abcd", "Somewhere", "A Reastaurant"));
//        restaurants.add(new Restaurant("b", "Somewhere", "A Reastaurant"));
//        restaurants.add(new Restaurant("c", "Somewhere", "A Reastaurant"));
//        restaurants.add(new Restaurant("d", "Somewhere", "A Reastaurant"));
//        restaurants.add(new Restaurant("e", "Somewhere", "A Reastaurant"));
//
//        service.save(new Restaurant("b", "Somewhere", "A Reastaurant"));
//        service.save(new Restaurant("c", "Somewhere", "A Reastaurant"));
//        service.save(new Restaurant("d", "Somewhere", "A Reastaurant"));
//        service.save(new Restaurant("e", "Somewhere", "A Reastaurant"));
//
//        restaurants = service.findAll();
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Responded", "RestaurantController");
//        return ResponseEntity.accepted().headers(httpHeaders).body(restaurants);
//    }
}
