package com.sd.assig23.service;

import com.sd.assig23.dto.RestaurantDTO;
import com.sd.assig23.dto.model.Restaurant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EmailServiceTest {

    @Test
    void sendMail() {

        RestaurantDTO dto = Mockito.mock(RestaurantDTO.class);


        EmailService emailService = new EmailService();

        when(dto.toString()).thenReturn("Mockito  Test");

        emailService.sendMail(dto);
    }
}