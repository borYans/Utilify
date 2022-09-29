package com.boryans.validate

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidationFormTest {

  @Test
  fun `return true if password validation has default values set`() {
    //Given
    val password = "Emenge#2!Butebershenge"
    //When
    val isValidated = password.isValidPassword()
    //Then
    assertTrue(isValidated)
  }

  @Test
  fun `return true if password validation without mixed case value set`() {
    //Given
    val password = "menge#2!utebershenge"
    //When
    val isValidated = password.isValidPassword(shouldIncludeMixedCase = false)
    //Then
    assertTrue(isValidated)
  }

  @Test
  fun `return true if password validation without special character case value set`() {
    //Given
    val password = "menge2AGutebershenge"
    //When
    val isValidated = password.isValidPassword(shouldIncludeSpecialChar = false)
    //Then
    assertTrue(isValidated)
  }

  @Test
  fun `return true if password validation without special character and mixed case values set`() {
    //Given
    val password = "menge2!@~!utebershenge"
    //When
    val isValidated = password.isValidPassword(
        shouldIncludeSpecialChar = false,
        shouldIncludeMixedCase = false
      )
    //Then
    assertTrue(isValidated)
  }

  @Test
  fun `return false if password validation without special character and with mixed case values set`() {
    //Given
    val password = "menge2!@~!utebershenge"
    //When
    val isValidated = password.isValidPassword(
      shouldIncludeSpecialChar = false,
      shouldIncludeMixedCase = true
    )
    //Then
    assertFalse(isValidated)
  }

  @Test
  fun `return true if password validation without special character and with mixed case values set`() {
    //Given
    val password = "AAAAAAAAAAAAA123AaA"
    //When
    val isValidated = password.isValidPassword(
      shouldIncludeSpecialChar = false,
      shouldIncludeMixedCase = true
    )
    //Then
    assertTrue(isValidated)
  }

  @Test
  fun `return false if password validation with special character and without mixed case values set`() {
    //Given
    val password = "menge2utebershenge"
    //When
    val isValidated = password.isValidPassword(
      shouldIncludeSpecialChar = true,
      shouldIncludeMixedCase = false
    )
    //Then
    assertFalse(isValidated)
  }

  @Test
  fun `return true if password validation with special character and without mixed case values set`() {
    //Given
    val password = "aaaaaa!@aaa123aaa"
    //When
    val isValidated = password.isValidPassword(
      shouldIncludeSpecialChar = true,
      shouldIncludeMixedCase = false
    )
    //Then
    assertTrue(isValidated)
  }

  @Test
  fun `return false if passwords is less than default minimum length of 8 chars`() {
    //Given
    val password = "@#123Ab"
    //When
    val isValidated = password.isValidPassword(
      shouldIncludeSpecialChar = false,
      shouldIncludeMixedCase = true
    )
    //Then
    assertFalse(isValidated)
  }
}