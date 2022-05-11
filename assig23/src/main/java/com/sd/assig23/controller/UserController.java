package com.sd.assig23.controller;

import com.sd.assig23.dto.LoginDTO;
import com.sd.assig23.dto.NewUserDTO;
import com.sd.assig23.dto.model.Token;
import com.sd.assig23.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.logging.FileHandler;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
    @Autowired
    private UserService service;

    private java.util.logging.Logger logger;
    public UserController(){
        logger =  java.util.logging.Logger.getLogger(this.getClass().getName());
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler(this.getClass().getName()+".log");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.addHandler(fileHandler);
    }

    @PostMapping("/users/add")
    public ResponseEntity addUser(@RequestBody NewUserDTO dto){
        System.out.println(dto.getUsername()+dto.getAddress()+dto.getEmail()+dto.getPassword());
        service.save(service.fromDTO(dto));
        logger.info("added user: "+dto.getUsername());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/users/login")
    public ResponseEntity login(@RequestBody LoginDTO dto){
        System.out.println(dto.getUsername()+dto.getPassword());

        if(service.checkPassword(dto)) {
            System.out.println("YAY PASS GRANTED");
            logger.info("logged in!");
            if (service.findByUsername(dto.getUsername()).isAdmin())
                return ResponseEntity.ok(new Token("admin"));
            return ResponseEntity.ok(new Token("test123"));

        }
        else
            return ResponseEntity.badRequest().build();
    }

    @GetMapping("/users/debugAdmin")
    public void addAdmin(){
       service.saveAdmin();

    }


}
