package com.sd.assig23.controller;

import com.sd.assig23.dto.FoodDTO;
import com.sd.assig23.dto.RestaurantDTO;
import com.sd.assig23.service.EmailService;
import com.sd.assig23.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class RestaurantController {

   private java.util.logging.Logger logger;
    public RestaurantController(){
        logger =  java.util.logging.Logger.getLogger(this.getClass().getName());
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler(this.getClass().getName()+ ".log");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.addHandler(fileHandler);
    }

    @Autowired
    private RestaurantService service;

    @Autowired
    private EmailService emailService;

    @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantDTO>> restaurants(){
        List<RestaurantDTO> restaurants = service.findAll();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Responded", "RestaurantController");

        logger.info("Restaurant List");
        return ResponseEntity.accepted().headers(httpHeaders).body(restaurants);
    }

    @GetMapping("/restaurants/menu")
    public ResponseEntity<List<FoodDTO>> menu(@RequestParam String id){

        System.out.println("menu : "+id);
        List<FoodDTO> menu = service.getMenu(Long.parseLong(id));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Responded", "RestaurantController");
        logger.info("menu viewed: "+id);
        return ResponseEntity.accepted().headers(httpHeaders).body(menu);
    }

    @PostMapping("/restaurants/add")
    public ResponseEntity addRestaurant(@RequestBody RestaurantDTO restaurantDTO){
        System.out.println(restaurantDTO.getName()+restaurantDTO.getId()+restaurantDTO.getAddress()+restaurantDTO.getDescription());
        emailService.sendMail(restaurantDTO);
        service.save(restaurantDTO);
        logger.info("new restaurant: "+restaurantDTO.getName());
        return ResponseEntity.noContent().build();

    }

}
