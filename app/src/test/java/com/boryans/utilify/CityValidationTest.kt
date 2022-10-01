package com.boryans.utilify

import com.boryans.utilify.validation.isValidCity
import org.junit.Assert
import org.junit.Test

class CityValidationTest {

  @Test
  fun `return true if city with dot matches regex`() {
    //Given
    val city = "St. Catharines"
    //When
    val result = city.isValidCity()
    //Then
    Assert.assertTrue(result)
  }

  @Test
  fun `return true if city with dash matches regex`() {
    //Given
    val city = "Niagara-on-the-Lake"
    //When
    val result = city.isValidCity()
    //Then
    Assert.assertTrue(result)
  }

  @Test
  fun `return true if city with quation mark matches regex`() {
    //Given
    val city = "Provence-Alpes-Côte d'Azur"
    //When
    val result = city.isValidCity()
    //Then
    Assert.assertTrue(result)
  }

  @Test
  fun `return false if city is not matching regex`() {
    //Given
    val city = "Skopje$"
    val city2 = "Niagara--on-the-Lake"
    //When
    val result = city.isValidCity()
    val result2 = city2.isValidCity()
    //Then
    Assert.assertFalse(result)
    Assert.assertFalse(result2)
  }
}