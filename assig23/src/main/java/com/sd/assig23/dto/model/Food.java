package com.sd.assig23.dto.model;

import javax.persistence.*;

@Entity
@Table(name = "food")
public class Food {

    @Id
    @Column(unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column()
    private String description;

    @Column(nullable = false)
    private Float price;

    @ManyToOne
    @JoinColumn
    private FoodCategory foodCategory;

    @ManyToOne
    @JoinColumn
    private Restaurant restaurant;

    public Food(
            Long id,
            String name,
            String description,
            Float price,
            FoodCategory foodCategory,
            Restaurant restaurant
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.foodCategory = foodCategory;
        this.restaurant = restaurant;
    }

    public Food(
            String name,
            String description,
            Float price,
            FoodCategory foodCategory,
            Restaurant restaurant
    ) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.foodCategory = foodCategory;
        this.restaurant = restaurant;
    }

    public Food(){}

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

    public FoodCategory getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(FoodCategory foodCategory) {
        this.foodCategory = foodCategory;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
