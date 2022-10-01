package com.boryans.utilify

import com.boryans.utilify.exception.NothingToValidateException
import java.math.BigDecimal
import java.math.BigDecimal.ZERO


/**
 * Checks if amount is valid.
 * @param[minimumAmount] minimum amount you can set.
 * @param[maximumAmount] maximum amount you can set.
 * @return[Boolean] flag if amount is valid.
 */
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

/**
 * Checks if string is representation of a number.
 */
fun String.isNumber() = this.toBigDecimalOrNull() != null

/**
 * Checks if value is positive
 */
fun String.isPositiveValue() = this.toBigDecimal() > ZERO

/**
 * Checks if value is negative
 */
fun String.isNegativeValue() = this.toBigDecimal() < ZERO

/**
 * Checks if value is zero
 */
fun String.isZero() = this.toBigDecimal() == ZERO