package com.nightsky.serverapi.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int amount;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(referencedColumnName = "id")
    private Weight weight;
    private double weight_value;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(referencedColumnName = "id")
    private Package_size package_size;
    private double length;
    private double width;
    private double height;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(name = "cargo_condition",
                joinColumns = { @JoinColumn(name = "cargo_id")},
                inverseJoinColumns = { @JoinColumn(name = "condition_id")})
    private Set<Special_condition> special_conditions = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(referencedColumnName = "id")
    private User user;

    public Cargo() {
    }

    public Cargo(int amount, Weight weight, double weight_value, double length, double width, double height, User user) {
        this.amount = amount;
        this.weight = weight;
        this.weight_value = weight_value;
        this.length = length;
        this.width = width;
        this.height = height;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public double getWeight_value() {
        return weight_value;
    }

    public void setWeight_value(double weight_value) {
        this.weight_value = weight_value;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Set<Special_condition> getSpecial_conditions() {
        return special_conditions;
    }

    public void setSpecial_conditions(Set<Special_condition> special_conditions) {
        this.special_conditions = special_conditions;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", amount=" + amount +
                ", weight=" + weight +
                ", weight_value=" + weight_value +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", special_conditions=" + special_conditions +
                ", user=" + user +
                '}';
    }

    public Package_size getPackage_size() {
        return package_size;
    }

    public void setPackage_size(Package_size package_size) {
        this.package_size = package_size;
    }
}
