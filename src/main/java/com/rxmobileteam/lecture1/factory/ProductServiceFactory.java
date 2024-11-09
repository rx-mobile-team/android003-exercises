package com.rxmobileteam.lecture1.factory;

import com.rxmobileteam.lecture1.data.Dao;
import com.rxmobileteam.lecture1.service.ProductService;
import com.rxmobileteam.utils.ExerciseNotCompletedException;
import org.jetbrains.annotations.NotNull;

/**
 * {@link ProductServiceFactory} is used to create an instance of {@link ProductService}
 * <p>
 */
public class ProductServiceFactory {

    /**
     * Create a new instance of {@link ProductService}
     *
     * @return ProductService
     */
    @NotNull
    public ProductService createProductService(Dao dao) {
        return new ProductService(dao);
    }
}
