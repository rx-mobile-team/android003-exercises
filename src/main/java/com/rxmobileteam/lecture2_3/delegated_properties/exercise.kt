package com.rxmobileteam.lecture2_3.delegated_properties

import com.rxmobileteam.lecture2_3.delegated_properties.StringOperationDelegates.capitalized
import com.rxmobileteam.lecture2_3.delegated_properties.StringOperationDelegates.trimmed
import com.rxmobileteam.lecture2_3.delegated_properties.StringOperationDelegates.uppercase
import com.rxmobileteam.utils.ExerciseNotCompletedException
import java.util.*
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

object StringOperationDelegates {
  /**
   * Allows to store a string in uppercase
   */
  @JvmStatic
  fun uppercase(initial: String, locale: Locale = Locale.ROOT): ReadWriteProperty<Any?, String> =
    // TODO: Implement the delegate. Note: avoid unnecessary operations/computations as much as possible
    object : ReadWriteProperty<Any?, String> {
      private var uppercaseValue: String = initial

      // TODO: Implement the getValue
      override fun getValue(thisRef: Any?, property: KProperty<*>): String = uppercaseValue

      // TODO: Implement the setValue
      override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        uppercaseValue = value.uppercase(locale)
      }
    }

  /**
   * Allows to store a string without leading and trailing whitespaces
   */
  fun trimmed(initial: String): ReadWriteProperty<Any?, String> =
    // TODO: Implement the delegate. Note: avoid unnecessary operations/computations as much as possible
    object : ReadWriteProperty<Any?, String> {
      private var trimmedValue: String = initial

      // TODO: Implement the getValue
      override fun getValue(thisRef: Any?, property: KProperty<*>): String = trimmedValue

      // TODO: Implement the setValue
      override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        trimmedValue = value.trim()
      }
    }

  /**
   * Allows to store a string with the format: the first letter of the stored string and leave the rest lowercase.
   */
  fun capitalized(initial: String): ReadWriteProperty<Any?, String> =
    // TODO: Implement the delegate. Note: avoid unnecessary operations/computations as much as possible
    object : ReadWriteProperty<Any?, String> {
      private var value: String = initial

      // TODO: Implement the getValue
      override fun getValue(thisRef: Any?, property: KProperty<*>): String = value

      // TODO: Implement the setValue
      override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        this.value =
          value.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
      }
    }
}

private class MyUser {
  var name: String by uppercase(initial = "rx-mobile-team")
  var bio: String by trimmed(initial = "Good")
  var city: String by capitalized(initial = "hanoi")
}

fun main() {
  val user = MyUser()
  println("name is '${user.name}'")
  println("bio is '${user.bio}'")
  println("city is '${user.city}'")
  println("-".repeat(80))

  user.name = "RxMobileTeam"
  user.bio = "RxMobileTeam is a mobile full-stack development team.\n\n\n\n\n                 \n"
  user.city = "danang"

  println("After update:")
  println("name is '${user.name}'")
  println("bio is '${user.bio}'")
  println("city is '${user.city}'")
}
