package com.rxmobileteam.lecture1.data;


import com.rxmobileteam.lecture1.service.Product;
import com.rxmobileteam.utils.ExerciseNotCompletedException;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * {@link ProductDao} represents a Data Access Object (DAO) for products.
 * The implementation is simplified, so it just uses {@link HashSet} to store.
 * <p>
 * todo: 1. Implement a method {@link ProductDao#addProduct(Product)} that store new product into the set
 * todo: 2. Implement a method {@link ProductDao#findAll(String)} that returns a set of all products
 */
public class ProductDao implements ProductInterface {
    private final Set<Product> products = new HashSet<>();

    /**
     * Stores a new product
     *
     * @param product a product to store
     * @return {@code true} if a product was stored, {@code false} otherwise
     */
    public boolean addProduct(@NotNull Product product) {
        boolean isCheck = products.add(product);
        if (!isCheck) {
            throw new ExerciseNotCompletedException();
        }
        return isCheck;
    }

    /**
     * Returns all stored products
     *
     * @return a set of all stored products
     */
    @NotNull
    public Set<Product> findAll(String query) {
        Set<Product> result = new HashSet<>();
        for (Product product : products) {
            if (product.getName().contains(query) || product.getDescription().contains(query)) {
                result.add(product);
            }
        }

        if (result.isEmpty()) {
            throw new ExerciseNotCompletedException();
        }

        return result;

    }

}
