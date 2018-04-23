package com.jcg.examples.model;

import lombok.Data;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    public long getId() {
        return id;
    }

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    @Column(name = "description")
    private String description;

    public String getDescription() {
        return description;
    }

    @Column(name = "price")
    private Integer price;

    public Integer getPrice() {
        return price;
    }

    public Item(long id, String name, String description, Integer price){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Item(){}

    @Override
    public String toString() {
        return getId() + " - " + getName();
    }


    @ManyToMany( fetch = FetchType.LAZY, mappedBy = "itemsList", cascade = CascadeType.PERSIST  )
    public List<Cart> Carts;

    @Override
    public boolean equals(Object anObject) {
        if (!(anObject instanceof Item)) {
            return false;
        }
        return (this.id == ((Item)anObject).id);
    }

    @Override
    public int hashCode(){
        return Math.toIntExact(id);
    }
}
