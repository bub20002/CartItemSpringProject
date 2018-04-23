package com.jcg.examples.service;


import com.jcg.examples.model.Cart;
import com.jcg.examples.model.Item;

import java.util.List;


public interface  CartService {
    Cart findById(long id);

    List<Cart> selectAll();

    void save(Cart cart);

    void update(Cart cart);

    void deleteById(long id);

    List<Item> selectItemsByCartId(long id);

    void deleteItemById(long id, long id2);
}
