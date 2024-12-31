package com.rxmobileteam.lecture1.factory;

import com.rxmobileteam.lecture1.data.IDao;
import com.rxmobileteam.lecture1.service.ProductService;
import com.rxmobileteam.utils.ExerciseNotCompletedException;

import org.jetbrains.annotations.NotNull;

/**
 * {@link ProductServiceFactory} is used to create an instance of {@link ProductService}
 * <p>
 * TODO: 1. Implement method {@link ProductServiceFactory#createProductService()}
 */
public class ProductServiceFactory {

    /**
     * Create a new instance of {@link ProductService}
     *
     * @return ProductService
     */
    @NotNull
    public ProductService createProductService(IDao dao) {
        return new ProductService(dao);
    }
}
