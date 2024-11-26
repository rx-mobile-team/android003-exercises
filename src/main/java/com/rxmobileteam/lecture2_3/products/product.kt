package com.rxmobileteam.lecture2_3.products

enum class ProductCategory {
  LAPTOP, PHONE, HEADPHONES, SMART_WATCH, CAMERA,
}

data class Product(
  val id: String,
  val name: String,
  val price: Double,
  val category: ProductCategory,
  val favoriteCount: Int,
)

data class Order(
  val id: String,
  val products: List<Product>,
  val isDelivered: Boolean,
)

fun List<Product>.sortedByPriceAscendingThenByFavoriteCountDescending(): List<Product> =
  this.sortedWith(compareBy<Product> { it.price }.thenByDescending { it.favoriteCount })

fun List<Order>.getProductsSet(): Set<Product> = this.flatMap { it.products }.toSet()

fun List<Order>.getProductsList(): List<Product> = this.flatMap { it.products }

fun List<Order>.getDeliveredOrders(): List<Order> = this.filter { it.isDelivered }

fun List<Order>.getDeliveredProductsList(): List<Product> = this.getDeliveredOrders().flatMap { it.products }

fun List<Order>.partitionDeliveredAndNotDelivered(): Pair<List<Order>, List<Order>> = this.partition { it.isDelivered }

fun List<Order>.countOfEachProduct(): Map<Product, Int> = this.flatMap { it.products }.groupingBy { it }.eachCount()

fun Order.sumProductPrice(): Double = this.products.sumOf { it.price }

fun Order.getMaxPriceProduct(): Product = this.products.maxByOrNull { it.price }!!

fun Order.getMinPriceProduct(): Product = this.products.minByOrNull { it.price }!!

