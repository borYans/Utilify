package com.boryans.utilify

import com.boryans.utilify.validation.isValidAge
import org.junit.Assert
import org.junit.Test

class AgeValidationTest {

  @Test
  fun `return true when age is in valid range`() {
    //Given
    val age = "4"
    //When
    val result = age.isValidAge(2, 4)
    //Then
    Assert.assertTrue(result)
  }

  @Test
  fun `return true when age is not in valid range`() {
    //Given
    val age = "1"
    //When
    val result = age.isValidAge(2, 4)
    //Then
    Assert.assertFalse(result)
  }
}