package com.boryans.validate

import java.math.BigDecimal
import java.math.BigDecimal.ZERO


fun String?.isValidAmount(
  minimumAmount: BigDecimal? = null,
  maximumAmount: BigDecimal? = null
): Boolean {
  this ?: throw NothingToValidateException("Validation is not possible on null value")
  val amount = this.trim()
  if (amount.isBlank() || !amount.isNumber()) return false
  when {
    minimumAmount == null && maximumAmount == null -> return true
    minimumAmount != null && maximumAmount == null -> return amount.toBigDecimal() >= minimumAmount
    minimumAmount == null && maximumAmount != null -> return amount.toBigDecimal() <= maximumAmount
    minimumAmount != null && maximumAmount != null -> return amount.toBigDecimal() in minimumAmount..maximumAmount
  }
  return false
}

fun String.isNumber() = this.toBigDecimalOrNull() != null

fun String.isPositiveValue() = this.toBigDecimal() > ZERO

fun String.isNegativeValue() = this.toBigDecimal() < ZERO
