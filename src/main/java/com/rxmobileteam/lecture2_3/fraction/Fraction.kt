package com.rxmobileteam.lecture2_3.fraction

import kotlin.math.roundToInt
import kotlin.math.roundToLong

class Fraction private constructor(
  val numerator: Int,
  val denominator: Int,
) : Comparable<Fraction> {

  val decimal: Double = numerator * 1.0 / denominator

  init {
    require(denominator != 0) {
      "Denominator must not be 0."
    }
  }

  private fun formatNegativeFraction(fraction: Fraction): Fraction {
    if (fraction.numerator < 0 && fraction.denominator < 0) {
      return Fraction(-fraction.numerator, -fraction.denominator)
    }
    if (fraction.denominator < 0) {
      return Fraction(-fraction.numerator, -fraction.denominator)
    }
    return Fraction(fraction.numerator, fraction.denominator)
  }

  //region unary operators
  operator fun unaryPlus(): Fraction = formatNegativeFraction(Fraction(this.numerator, this.denominator))

  operator fun unaryMinus(): Fraction = formatNegativeFraction(Fraction(-this.numerator, this.denominator))
  //endregion

  //region plus operators
  operator fun plus(other: Fraction): Fraction = formatNegativeFraction(
    Fraction(
      (this.numerator * other.denominator + this.denominator * other.numerator),
      this.denominator * other.denominator
    )
  )

  operator fun plus(other: Int): Fraction =
    formatNegativeFraction(Fraction(this.numerator + other * this.denominator, this.denominator))
  //endregion

  //region times operators
  operator fun times(other: Fraction): Fraction =
    formatNegativeFraction(Fraction(this.numerator * other.numerator, this.denominator * other.denominator))

  operator fun times(number: Int): Fraction =
    formatNegativeFraction(Fraction(this.numerator * number, this.denominator))
  //endregion

  override fun compareTo(other: Fraction): Int = this.numerator * other.denominator - other.numerator * this.denominator

  //region toString, hashCode, equals, copy
  override fun toString(): String {
    val fractionFormatted = formatNegativeFraction(this)
    return "${fractionFormatted.numerator}/${fractionFormatted.denominator}"
  }

  override fun hashCode(): Int = 31 * this.numerator + this.denominator

  override fun equals(other: Any?): Boolean {
    if (this === other) {
      return true
    }
    return if (other is Fraction) {
      this.numerator * other.denominator - other.numerator * this.denominator == 0
    } else {
      false
    }
  }

  fun copy(
    numerator: Int = this.numerator,
    denominator: Int = this.denominator
  ): Fraction = Fraction(numerator, denominator)
  //endregion

  companion object {
    @JvmStatic
    fun ofInt(number: Int): Fraction {
      return Fraction(number, 1)
    }

    @JvmStatic
    fun of(numerator: Int, denominator: Int): Fraction {
      return simplify(numerator, denominator)
    }

    private fun simplify(numerator: Int, denominator: Int): Fraction {
      val gcdValue = gcd(numerator, denominator)
      val simplifiedNumerator = numerator / gcdValue
      val simplifiedDenominator = denominator / gcdValue
      return if (simplifiedDenominator < 0) {
        Fraction(-simplifiedNumerator, -simplifiedDenominator)
      } else {
        Fraction(simplifiedNumerator, simplifiedDenominator)
      }
    }

    private fun gcd(a: Int, b: Int): Int {
      return if (b == 0) a else gcd(b, a % b)
    }
  }
}

infix fun Int.over(denominator: Int): Fraction = Fraction.of(this, denominator)

//region get extensions
operator fun Fraction.component1(): Int = numerator

operator fun Fraction.component2(): Int = denominator

operator fun Fraction.get(index: Int): Int = when (index) {
  0 -> numerator
  1 -> denominator
  else -> throw IndexOutOfBoundsException()
}
//endregion

//region to number extensions
fun Fraction.roundToInt(): Int = decimal.roundToInt()

fun Fraction.roundToLong(): Long = decimal.roundToLong()

fun Fraction.toFloat(): Float = decimal.toFloat()

fun Fraction.toDouble(): Double = decimal
//endregion

fun main() {
  // Creation
  println("1/2: ${Fraction.of(1, 2)}")
  println("2/3: ${Fraction.of(2, 3)}")
  println("8: ${Fraction.ofInt(8)}")
  println("2/4: ${2 over 4}")
  println("-".repeat(80))

  // Unary operators
  println("+2/4: ${+Fraction.of(2, 4)}")
  println("-2/6: ${-Fraction.of(2, 6)}")
  println("-".repeat(80))

  // Plus operators
  println("1/2 + 2/3: ${Fraction.of(1, 2) + Fraction.of(2, 3)}")
  println("1/2 + 1: ${Fraction.of(1, 2) + 1}")
  println("-".repeat(80))

  // Times operators
  println("1/2 * 2/3: ${Fraction.of(1, 2) * Fraction.of(2, 3)}")
  println("1/2 * 2: ${Fraction.of(1, 2) * 2}")
  println("-".repeat(80))

  // compareTo
  println("3/2 > 2/2: ${Fraction.of(3, 2) > Fraction.of(2, 2)}")
  println("1/2 <= 2/4: ${Fraction.of(1, 2) <= Fraction.of(2, 4)}")
  println("4/6 >= 2/3: ${Fraction.of(4, 6) >= Fraction.of(2, 3)}")
  println("-".repeat(80))

  // hashCode
  println("hashCode 1/2 == 2/4: ${Fraction.of(1, 2).hashCode() == Fraction.of(2, 4).hashCode()}")
  println("hashCode 1/2 == 1/2: ${Fraction.of(1, 2).hashCode() == Fraction.of(1, 2).hashCode()}")
  println("hashCode 1/3 == 3/5: ${Fraction.of(1, 3).hashCode() == Fraction.of(3, 5).hashCode()}")
  println("-".repeat(80))

  // equals
  println("1/2 == 2/4: ${Fraction.of(1, 2) == Fraction.of(2, 4)}")
  println("1/2 == 1/2: ${Fraction.of(1, 2) == Fraction.of(1, 2)}")
  println("1/3 == 3/5: ${Fraction.of(1, 3) == Fraction.of(3, 5)}")
  println("-".repeat(80))

  // Copy
  println("Copy 1/2: ${Fraction.of(1, 2).copy()}")
  println("Copy 1/2 with numerator 2: ${Fraction.of(1, 2).copy(numerator = 2)}")
  println("Copy 1/2 with denominator 3: ${Fraction.of(1, 2).copy(denominator = 3)}")
  println("Copy 1/2 with numerator 2 and denominator 3: ${Fraction.of(1, 2).copy(numerator = 2, denominator = 3)}")
  println("-".repeat(80))

  // Component1, Component2 operators
  val (numerator, denominator) = Fraction.of(1, 2)
  println("Component1, Component2 of 1/2: $numerator, $denominator")
  val (numerator2, _) = Fraction.of(10, 30)
  println("Component1 of 10/30: $numerator2")
  val (_, denominator2) = Fraction.of(10, 79)
  println("Component2 of 10/79: $denominator2")
  println("-".repeat(80))

  // Get operator
  println("Get 0 of 1/2: ${Fraction.of(1, 2)[0]}")
  println("Get 1 of 1/2: ${Fraction.of(1, 2)[1]}")
  println("Get 2 of 1/2: ${runCatching { Fraction.of(1, 2)[2] }}") // Should print "Failure(...)"
  println("-".repeat(80))

  // toInt, toLong, toFloat, toDouble
  println("toInt 1/2: ${Fraction.of(1, 2).roundToInt()}")
  println("toLong 1/2: ${Fraction.of(1, 2).roundToLong()}")
  println("toFloat 1/2: ${Fraction.of(1, 2).toFloat()}")
  println("toDouble 1/2: ${Fraction.of(1, 2).toDouble()}")
  println("-".repeat(80))

  // Range
  // Because we implemented Comparable<Fraction>, we can use Fraction in ranges
  val range = Fraction.of(1, 2)..Fraction.of(2, 3)
  println("1/2 in range 1/2..2/3: ${Fraction.of(1, 2) in range}") // "in" operator is "contains"
  println("2/3 in range 1/2..2/3: ${Fraction.of(2, 3) in range}")
  println("7/12 in range 1/2..2/3: ${Fraction.of(7, 12) in range}")
  println("-".repeat(80))
}
