package com.jcg.examples.service;

import com.jcg.examples.model.Item;

import java.util.List;

public interface ItemService {
    Item findById(long id);

    List<Item> selectAll();

    void save(Item item);

    void update(Item item);

    void deleteById(long id);
}
