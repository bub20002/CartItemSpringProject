package com.jcg.examples.model;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }

    private String name;

    public String getName() {
        return name;
    }

    private Integer capacity;

    public Integer getCapacity() {
        return capacity;
    }

    public Cart(long id, String name, Integer capacity){
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public Cart(){}

    @Override
    public String toString() {
        return getId() + " - " + getName();
    }

    @ManyToMany( fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST }  )
    @JoinTable(
            name = "itemtocart",
            joinColumns = {
                    @JoinColumn(name = "cartid",
                            nullable = false, updatable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "itemid",
                            nullable = false, updatable = false)
            }
    )
    public List<Item> itemsList = new LinkedList<>();

    @Override
    public boolean equals(Object anObject) {
        if (!(anObject instanceof Cart)) {
            return false;
        }
        return (this.id == ((Cart)anObject).id);
    }

    @Override
    public int hashCode(){
        return Math.toIntExact(id);
    }
}
