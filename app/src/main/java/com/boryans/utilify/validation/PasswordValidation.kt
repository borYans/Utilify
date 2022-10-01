package com.boryans.utilify.validation

import com.boryans.utilify.exception.NothingToValidateException

fun String.isLongEnough(passLength: Int) = length >= passLength
fun String.hasEnoughDigits(numOfDigits: Int) = count(Char::isDigit) >= numOfDigits
fun String.isMixedCase(shouldCheck: Boolean) = if (shouldCheck) any(Char::isLowerCase) && any(Char::isUpperCase) else true
fun String.hasSpecialChar(shouldCheck: Boolean) = if (shouldCheck) any { it in "!,+^" } else true


fun String?.isValidPassword(
  length: Int = 8,
  numberOfDigits: Int = 1,
  shouldIncludeMixedCase: Boolean = true,
  shouldIncludeSpecialChar: Boolean = true
): Boolean {
  this ?: throw NothingToValidateException("Validation is not possible on null value")
  if (isBlank()) return false
  return isLongEnough(length)
    && hasEnoughDigits(numberOfDigits)
    && isMixedCase(shouldIncludeMixedCase)
    && hasSpecialChar(shouldIncludeSpecialChar)
}