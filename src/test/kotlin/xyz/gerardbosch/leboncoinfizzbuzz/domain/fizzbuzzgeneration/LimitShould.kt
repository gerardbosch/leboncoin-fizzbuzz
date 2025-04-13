package xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzgeneration

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Tag

@Tag("unit-test")
class LimitShould : StringSpec({

  // TODO candidate to showcase PBT

  "create a Limit when the value is bigger than 0" {
    // Given
    val value = 10
    // When
    val limit = Limit(value)
    // Then
    limit.value shouldBe value
  }

  "throw an exception when creating a Limit with a non-positive value" {
    // Given
    val value = 0
    // When / Then
    shouldThrow<IllegalArgumentException> { Limit(value) }
  }

})
