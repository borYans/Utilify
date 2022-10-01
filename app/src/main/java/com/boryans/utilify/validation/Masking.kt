package com.boryans.utilify.validation

import androidx.core.text.isDigitsOnly

/**
 * Masks the card number in:
 * long format "**** **** **** [last 4 card number digits]".
 * Short format "**** [last 4 card number digits]".
 *
 * @param cardNumber [String] The number to be masked.
 * @param longFormat [Boolean] If true the number is masked in Long format, otherwise the number is masked in Short format.
 * @return The masked number.
 */
fun maskNumber(cardNumber: String, longFormat: Boolean): String {
  var result: String
  if (cardNumber.isNotEmpty()) {
    result = cardNumber.replace(" ", "")
    val length = result.length
    if (length > 3 && result.isDigitsOnly()) {
      result = result.substring(length - 4, length)
      if (longFormat) {
        return String.format("**** **** **** %s", result)
      }
      return String.format("**** %s", result)
    }
  }
  return ""
}