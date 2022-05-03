package com.sd.assig23.dto;


public class FoodDTO {

    private Long id;

    private String name;

    private String description;

    private Float price;

    private String category;

    //private String restaurant;

    public FoodDTO(){}

    public FoodDTO(Long id, String name, String description, Float price, String foodCategory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = foodCategory;
    }

    public FoodDTO(String name, String description, Float price, String foodCategory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = foodCategory;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



}
