package com.sd.assig23.service;

import com.sd.assig23.dto.NewUserDTO;
import com.sd.assig23.dto.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Test
    void fromDTO() {
        UserService service = new UserService();
        NewUserDTO userDTO = new NewUserDTO("username",
                "email",
                "pass",
                "address");

        User user = service.fromDTO(userDTO);
        assertEquals(user.getUsername(),userDTO.getUsername());
        assertEquals(user.getEmail(),userDTO.getEmail());
        assertEquals(user.getAddress(), userDTO.getAddress());
        assertNotEquals(user.getPassword(),userDTO.getPassword());
    }


}