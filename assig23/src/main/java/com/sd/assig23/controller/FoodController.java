package com.sd.assig23.controller;

import com.sd.assig23.dto.FoodDTO;
import com.sd.assig23.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class FoodController {

    @Autowired
    FoodService service;

    @PostMapping("/restaurants/addfood")
    public ResponseEntity addFood(@RequestParam String id, @RequestBody FoodDTO dto){
        System.out.println(dto.getName()+dto.getCategory()+dto.getDescription()+dto.getPrice()+dto.getId()+id);
        service.save(service.FromDto(dto,id));
        return ResponseEntity.noContent().build();
    }
}
