package com.boryans.validate

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class EmailValidationTests {

  @Test
  fun `return true when email is not empty`() {
    //Given
    val email = "emenge.butebershenge@gmail.com"
    //When
    val result = email.isValidEmail()
    //Then
    assertTrue(result)
  }

  @Test
  fun `return false when email is empty`() {
    //Given
    val email = ""
    //When
    val result = email.isValidEmail()
    //Then
    assertFalse(result)
  }

  @Test
  fun `return false when email is without @ char`() {
    //Given
    val email = "emenge.butebershengegmail.com"
    //When
    val result = email.isValidEmail()
    //Then
    assertFalse(result)
  }

  @Test
  fun `return false when email is without dot before com`() {
    //Given
    val email = "emenge.rshenge@gmailcom"
    //When
    val result = email.isValidEmail()
    //Then
    assertFalse(result)
  }

  @Test
  fun `return false when email is without com`() {
    //Given
    val email = "emenge.rshenge@gmail"
    //When
    val result = email.isValidEmail()
    //Then
    assertFalse(result)
  }


  @Test
  fun `return true when email is valid`() {
    //Given
    val email = "emenge.rshenge@yahoo.org"
    //When
    val result = email.isValidEmail()
    //Then
    assertTrue(result)
  }
}