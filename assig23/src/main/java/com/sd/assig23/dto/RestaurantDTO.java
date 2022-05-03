package com.sd.assig23.dto;

public class RestaurantDTO {

    private Long id;

    private String name;

    private String description;

    private String address;

    public RestaurantDTO() { }

    public RestaurantDTO(Long id, String name, String description, String address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
    }

    public RestaurantDTO(String name, String description, String address) {
        this.name = name;
        this.description = description;
        this.address = address;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



}
