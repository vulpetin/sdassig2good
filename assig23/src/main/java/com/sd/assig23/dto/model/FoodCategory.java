package com.sd.assig23.dto.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="foodcategory")
public class FoodCategory {

    @Id
    @Column(unique = true,nullable = false)
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "foodCategory")
    private List<Food> foodList;

    public FoodCategory(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public FoodCategory(String name) {
        this.name = name;
    }

    public FoodCategory(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
