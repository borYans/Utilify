package com.boryans.validate

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
}