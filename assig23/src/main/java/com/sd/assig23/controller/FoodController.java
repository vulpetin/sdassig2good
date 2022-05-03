package com.sd.assig23.controller;

import com.sd.assig23.dto.FoodDTO;
import com.sd.assig23.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.logging.FileHandler;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class FoodController {

    public FoodController (){
        logger =  java.util.logging.Logger.getLogger(this.getClass().getName());
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("UserController.log");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.addHandler(fileHandler);
    }

    @Autowired
    FoodService service;

    private java.util.logging.Logger logger;
    @PostMapping("/restaurants/addfood")
    public ResponseEntity addFood(@RequestParam String id, @RequestBody FoodDTO dto){
        System.out.println(dto.getName()+dto.getCategory()+dto.getDescription()+dto.getPrice()+dto.getId()+id);
        service.save(service.FromDto(dto,id));
        return ResponseEntity.noContent().build();
    }
}
