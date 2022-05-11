package com.sd.assig23.service;

import com.sd.assig23.dto.LoginDTO;
import com.sd.assig23.dto.NewUserDTO;
import com.sd.assig23.dto.model.User;
import com.sd.assig23.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;

@Service
public class UserService {

    private java.util.logging.Logger logger;

    public UserService(){
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
    UserRepository repository;


    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * finds by username
     *
     * @param username of the user
     * @return User entity
     */
    public User findByUsername(String username){
        List<User> userList = repository.findAll();
        for(User user : userList){
            if(user.getUsername().equals(username)) {
                logger.info("Found!");
                return user;
            }
        }
        logger.info("Not Found!");
        return null;
    }

    /**
     * checks credentials against the database
     *
     * @param dto credentials to be checked
     * @return true if correct
     */
    public boolean checkPassword(LoginDTO dto){
        User user = findByUsername(dto.getUsername());
        if (user == null)
            return false;
        logger.info("checking pass for "+dto.getUsername());
        return passwordEncoder.matches(dto.getPassword(),user.getPassword());

    }


    /**
     * Converts from DTO to entity
     *
     * @param dto to be converted
     * @return the entity
     */
    public User fromDTO (NewUserDTO dto){
        logger.info("fromdto: "+ dto);
        return new User(
                dto.getUsername(),
                dto.getEmail(),
                passwordEncoder.encode(dto.getPassword()),
                dto.getAddress()
        );
    }

    /**
     * test method because MYSQL is dumb
     * only way to create an admin currently
     */
    public void saveAdmin(){

        User user = fromDTO(new NewUserDTO("a", "a","a", "a"));
        user.setAdmin(true);
        repository.save(user);
    }

    /**
     * Saves to repository
     *
     * @param user to be saved
     */
    public void save(User user){
        repository.save(user);
        logger.info("Saved: "+ user.getUsername());
    }
}
