package com.rxmobileteam.lecture1.data;

import com.rxmobileteam.lecture1.service.Product;
import com.rxmobileteam.utils.ExerciseNotCompletedException;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * {@link ProductDao} represents a Data Access Object (DAO) for products.
 * The implementation is simplified, so it just uses {@link HashSet} to store.
 * <p>
 */
public class ProductDao implements Dao {
    private final Set<Product> products = new HashSet<>();

    /**
     * Stores a new product
     *
     * @param product a product to store
     * @return {@code true} if a product was stored, {@code false} otherwise
     */
    @Override
    public boolean add(@NotNull Product product) {
        return products.add(product);
    }

    /**
     * Returns all stored products
     *
     * @return a set of all stored products
     */
    @Override
    public @NotNull Set<Product> findAll() {
        return products;
    }

    @Override
    public @NotNull List<Product> query(String query) {
        return products.stream()
            .filter(it -> it.getName().toLowerCase().contains(query.toLowerCase())
                || it.getDescription().toLowerCase().contains(query.toLowerCase()))
            .toList();
    }

}
