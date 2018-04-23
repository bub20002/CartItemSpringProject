package com.jcg.examples.controller;

import com.jcg.examples.model.Cart;
import com.jcg.examples.model.Item;
import com.jcg.examples.service.CartService;
import com.jcg.examples.service.ItemService;
import lombok.experimental.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private CartService cartService;

    public MainController(){}

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/show_items", method = RequestMethod.GET)
    public ModelAndView showItems() {
        ModelAndView m = new ModelAndView("showItems");
        List<Item> itemsList;
        try {
            itemsList = itemService.selectAll();
        } catch (RuntimeException e) {
            m.addObject("error", e.getMessage());
            return m;
        }
        m.addObject("items",itemsList);
        return m;
    }

    @RequestMapping(value = "/item", method = RequestMethod.GET)
    public ModelAndView addItem() {
        return new ModelAndView("addItem");
    }

    @RequestMapping(value = "/item", method = RequestMethod.POST)
    public String addItem(Model m, @ModelAttribute("item") Item item) {
        try {
            itemService.save(item);
        } catch (RuntimeException e) {
            m.addAttribute("error", e.getMessage());
            return "addItem";
        }
        return "redirect:/show_items";
    }

    @RequestMapping(value = "/update_item/{id}", method = RequestMethod.GET)
    public ModelAndView updateItem(@PathVariable("id") long itemid) {
        ModelAndView m = new ModelAndView("updateItem");
        Item item = itemService.findById(itemid);
        m.addObject("item", item);
        return m;
    }

    @RequestMapping(value = "/update_item/{id}", method = RequestMethod.POST)
    public String submitUpdateItem(Model m, @ModelAttribute("item") Item item) {
        try {
            itemService.update(item);
        } catch (RuntimeException e) {
            m.addAttribute("error", e.getMessage());
            return "redirect:/show_items";
        }
        return "redirect:/show_items";
    }

    @RequestMapping(value = "/delete_item/{id}", method = RequestMethod.GET)
    public String deleteItemById(@PathVariable("id") long id ) {
        try {
            itemService.deleteById(id);
        } catch (RuntimeException e) {
            return "redirect:/show_items";
        }
        return "redirect:/show_items";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public ModelAndView addCart() {
        return new ModelAndView("addCart");
    }

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public String addCart(Model m, @ModelAttribute("cart") Cart cart) {
        try {
            cartService.save(cart);
        } catch (RuntimeException e) {
            m.addAttribute("error", e.getMessage());
            return "addCart";
        }
        return "redirect:/show_carts";
    }

//    @RequestMapping(value = "/update_cart/{id}", method = RequestMethod.GET)
//    public ModelAndView updateCart(@PathVariable("id") long cartid) {
//        ModelAndView m = new ModelAndView("updateCart");
//        Cart cart = cartService.findById(cartid);
//        m.addObject("cart", cart);
//        m.addObject("oldCart", cart);
//        return m;
//    }
//
//    @RequestMapping(value = "/update_cart/{id}", method = RequestMethod.POST)
//    public String submitUpdateCart(Model m, @ModelAttribute("cart") Cart cart, @ModelAttribute("oldCart") Cart oldCart) {
//        cart.itemsList.addAll(oldCart.itemsList);
//        try {
//            cartService.update(cart);
//        } catch (RuntimeException e) {
//            m.addAttribute("error", e.getMessage());
//            return "redirect:/show_carts";
//        }
//        return "redirect:/show_carts";
//    }

    @RequestMapping(value = "/delete_cart/{id}", method = RequestMethod.GET)
    public String deleteCartById(@PathVariable("id") long id ) {
        try {
            cartService.deleteById(id);
        } catch (RuntimeException e) {
            return "redirect:/show_carts";
        }
        return "redirect:/show_carts";
    }

    @RequestMapping(value = "/show_carts", method = RequestMethod.GET)
    public ModelAndView showCarts() {
        ModelAndView m = new ModelAndView("showCarts");
        List<Cart> cartsList;
        try {
            cartsList = cartService.selectAll();
        } catch (RuntimeException e) {
            m.addObject("error", e.getMessage());
            return m;
        }
        m.addObject("carts", cartsList);
        return m;
    }

    @RequestMapping(value = "/items_in_cart/{id}/add_item/{id2}", method = RequestMethod.GET)
    public String submitAddItemToCart(Model m, @PathVariable("id") long cartid, @PathVariable("id2") long itemid) {
        Cart cart = cartService.findById(cartid);
        cart.itemsList.add(itemService.findById(itemid));
        try {
            cartService.update(cart);
        } catch (RuntimeException e) {
            m.addAttribute("error", e.getMessage());
            return "redirect:/items_in_cart/" + cartid;
        }
        return "redirect:/items_in_cart/" + cartid;
    }

    @RequestMapping(value = "/items_in_cart/{id}/add_item", method = RequestMethod.GET)
    public ModelAndView addItemToCart(@PathVariable("id") long id) {
        ModelAndView m = new ModelAndView("addItemToCart");
        List<Item> itemsList;
        List<Item> fullItemsList;
        try {
            itemsList = cartService.findById(id).itemsList;
            fullItemsList = itemService.selectAll();
        } catch (RuntimeException e) {
            m.addObject("error", e.getMessage());
            return m;
        }
        fullItemsList.removeAll(itemsList);
        m.addObject("cart", cartService.findById(id));
        m.addObject("items", fullItemsList);
        return m;
    }

    @RequestMapping(value = "/items_in_cart/{id}", method = RequestMethod.GET)
    public ModelAndView itemsInCart(@PathVariable("id") long id) {
        ModelAndView m = new ModelAndView("itemsInCart");
        List<Item> items;
        try {
            items = cartService.selectItemsByCartId(id);
        } catch (RuntimeException e) {
            m.addObject("error", e.getMessage());
            return m;
        }
        m.addObject("items", items);
        m.addObject("cartId", id);
        return m;
    }

    @RequestMapping(value = "/items_in_cart/{id}/delete_item/{id2}", method = RequestMethod.GET)
    public String deleteItemInCart(@PathVariable("id") long cartid, @PathVariable("id2") long itemid) {
        try {
            cartService.deleteItemById(cartid, itemid);
        } catch (RuntimeException e) {
            return "redirect:/items_in_cart/" + cartid;
        }
        return "redirect:/items_in_cart/" + cartid;
    }
}
