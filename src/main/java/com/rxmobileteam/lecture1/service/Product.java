package com.rxmobileteam.lecture1.service;


import org.jetbrains.annotations.NotNull;

public class Product {
    @NotNull
    private final String id;
    @NotNull
    private final String name;
    @NotNull
    private final String description;
    private final double price;

    public Product(
        @NotNull String id,
        @NotNull String name,
        @NotNull String description,
        double price
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @NotNull
    public String getId() {
        return id;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (Double.compare(product.price, price) != 0) return false;
        if (!id.equals(product.id)) return false;
        if (!name.equals(product.name)) return false;
        return description.equals(product.description);
    }

    @Override
    public int hashCode() {
        int result;
        result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + Double.hashCode(price);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", price=" + price +
            '}';
    }
}
