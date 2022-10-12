package com.musala.drones.entities;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
public class Medication {
    @Id
    @GeneratedValue
    private Long id;
    @Pattern(regexp = "^[\\w-]+$")
    @NotNull
    private String name;
    @NotNull
    private int weight;
    @NotNull
    @Pattern(regexp = "^[A-Z0-9_]*$", message = "only uppercase letters, numbers and underscore are allowed")
    private String code;
    @Nullable
    private String image;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy = "medications")
    private List<Drone> drones;

    public Medication() {
    }

    public Medication(Long id, String name, int weight, String code, String image) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
