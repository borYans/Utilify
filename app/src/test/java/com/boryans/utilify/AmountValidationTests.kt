package com.boryans.utilify

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AmountValidationTests {

  @Test
  fun `return true when amount is a number`() {
    //Given
    val amount = "1234"
    //When
    val result = amount.isValidAmount()
    //Then
    assertTrue(result)
  }

  @Test
  fun `return true when amount is not a number`() {
    //Given
    val amount = "1234Abc"
    //When
    val result = amount.isValidAmount()
    //Then
    assertFalse(result)
  }

  @Test
  fun `return true if amount has no minimum and maximum`() {
    //Given
    val amount = "123456789"
    //When
    val result = amount.isValidAmount()
    //Then
    assertTrue(result)
  }

  @Test
  fun `return true if amount is below maximum`() {
    //Given
    val amount = "123456789"
    //When
    val result = amount.isValidAmount(maximumAmount = 22345688888.toBigDecimal())
    //Then
    assertTrue(result)
  }

  @Test
  fun `return false if amount is above maximum`() {
    //Given
    val amount = "123456789"
    //When
    val result = amount.isValidAmount(maximumAmount = 123456.toBigDecimal())
    //Then
    assertFalse(result)
  }

  @Test
  fun `return true if amount is above minimum`() {
    //Given
    val amount = "123456789"
    //When
    val result = amount.isValidAmount(minimumAmount = 123.toBigDecimal())
    //Then
    assertTrue(result)
  }

  @Test
  fun `return false if amount is below minimum`() {
    //Given
    val amount = "1234"
    //When
    val result = amount.isValidAmount(minimumAmount = 12345.toBigDecimal())
    //Then
    assertFalse(result)
  }

  @Test
  fun `return true when amount is in correct range of min and max`() {
    //Given
    val amount = "123455"
    //When
    val result = amount.isValidAmount(minimumAmount = 12345.toBigDecimal(), maximumAmount = 123456.toBigDecimal())
    //Then
    assertTrue(result)
  }

  @Test
  fun `return false when amount is in incorrect range of min and max`() {
    //Given
    val amount = "12345789"
    //When
    val result = amount.isValidAmount(minimumAmount = 12345.toBigDecimal(), maximumAmount = 123456.toBigDecimal())
    //Then
    assertFalse(result)
  }

  @Test
  fun `return true when amount is positive decimal`() {
    //Given
    val amount = "12.345"
    //When
    val result = amount.isValidAmount()
    //Then
    assertTrue(result)
  }

  @Test
  fun `return true when value is positive`() {
    //Given
    val amount = "12.345"
    //When
    val result = amount.isPositiveValue()
    //Then
    assertTrue(result)
  }

  @Test
  fun `return false when value is negative`() {
    //Given
    val amount = "-12.345"
    //When
    val result = amount.isPositiveValue()
    //Then
    assertFalse(result)
  }

  @Test
  fun `return true when value is negative`() {
    //Given
    val amount = "-12.345"
    //When
    val result = amount.isNegativeValue()
    //Then
    assertTrue(result)
  }

  @Test
  fun `return false when value is positive`() {
    //Given
    val amount = "12.345"
    //When
    val result = amount.isNegativeValue()
    //Then
    assertFalse(result)
  }

  }