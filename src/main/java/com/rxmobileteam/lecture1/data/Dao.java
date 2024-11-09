package com.rxmobileteam.lecture1.data;

import com.rxmobileteam.lecture1.service.Product;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

/**
 * Created by @rikka on: 9/11/24
 */

public interface Dao {
    boolean add(@NotNull Product product);

    @NotNull Set<Product> findAll();

    @NotNull List<Product> query(String query);
}
