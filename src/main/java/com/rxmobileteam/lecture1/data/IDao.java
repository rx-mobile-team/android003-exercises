package com.rxmobileteam.lecture1.data;

import com.rxmobileteam.lecture1.service.Product;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

public interface IDao {
    boolean add(@NotNull Product product);
    @NotNull Set<Product> findAll();
    @NotNull List<Product> findByQuery(String query);
}
