package com.rxmobileteam.lecture1.factory;


import com.rxmobileteam.lecture1.data.ProductDao;
import com.rxmobileteam.lecture1.data.ProductInterface;
import com.rxmobileteam.lecture1.service.ProductService;

import org.jetbrains.annotations.NotNull;

/**
 * {@link ProductServiceFactory} is used to create an instance of {@link ProductService}
 */
public class ProductServiceFactory {

    /**
     * Create a new instance of {@link ProductService}
     *
     * @return ProductService
     */
    @NotNull
    public ProductService createProductService() {
        ProductInterface productDao = new ProductDao();
        return new ProductService(productDao);
    }
}
