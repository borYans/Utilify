package com.boryans.utilify.validation

import com.boryans.utilify.constants.CITY_REGEX
import com.boryans.utilify.constants.EMAIL_REGEX
import com.boryans.utilify.exception.NothingToValidateException


fun String?.isValidEmail(): Boolean {
  this ?: throw NothingToValidateException("Validation is not possible on null value")
  if (isBlank()) return false
  return this.trim().let { Regex(com.boryans.utilify.constants.EMAIL_REGEX).matches(it) }
}

fun String?.isValidCity(): Boolean {
  this ?: throw NothingToValidateException("Validation is not possible on null value")
  if (isBlank()) return false
  return this.trim().let { Regex(com.boryans.utilify.constants.CITY_REGEX).matches(it) }
}

fun String?.isValidAge(
  from: Int,
  to: Int
): Boolean {
  this ?: throw NothingToValidateException("Validation is not possible on null value")
  if(isBlank()) return false
  val age = this.trim().toInt()
  return age in from..to
}