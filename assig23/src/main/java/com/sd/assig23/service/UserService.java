package com.sd.assig23.service;

import com.sd.assig23.dto.LoginDTO;
import com.sd.assig23.dto.NewUserDTO;
import com.sd.assig23.dto.model.User;
import com.sd.assig23.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public User findByUsername(String username){
        List<User> userList = repository.findAll();
        for(User user : userList){
            if(user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public boolean checkPassword(LoginDTO dto){
        User user = findByUsername(dto.getUsername());
        if (user == null)
            return false;

        return user.getPassword().equals(dto.getPassword());
    }

    public User findByEmail(String email){
        List<User> userList = repository.findAll();
        for(User user : userList){
            if(user.getEmail().equals(email))
                return user;
        }
        return null;
    }

    public User fromDTO (NewUserDTO dto){
        return new User(
                dto.getUsername(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getAddress()
        );
    }

    public void save(User user){
        repository.save(user);
    }
}
