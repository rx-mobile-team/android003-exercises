package com.rxmobileteam.lecture1.data;

import com.rxmobileteam.lecture1.service.Product;

import java.util.Set;


public interface ProductInterface {
    boolean addProduct(Product product);
    Set<Product> findAll();
}
