package com.sd.assig23.dto.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="restaurant")
public class Restaurant {

    @Id
    @Column(unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column()
    private String description;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "restaurant")
    private List<Food> foodList;



    public Restaurant(Long id, String name, String address, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
    }

    public Restaurant(String name, String address, String description) {
        this.name = name;
        this.address = address;
        this.description = description;
    }

    public Restaurant(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }
}