package xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzgeneration

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.flow.toList
import org.junit.jupiter.api.Tag
import xyz.gerardbosch.leboncoinfizzbuzz.domain.FizzBuzzParams

@Tag("unit-test")
class FizzBuzzGeneratorShould : StringSpec({

  // TODO Add a test for invalid parameter start=0

  "generate a correct FizzBuzz sequence" {
    // Given
    val generator = FizzBuzzGenerator(start = 1)

    // When
    val result = generator(FizzBuzzParams(
      Limit(15),
      FizzNum(3),
      BuzzNum(5),
      FizzText("LeBon"),
      BuzzText("Coin"),
    )).toList()

    // Then
    result shouldBe listOf(
      "1",
      "2",
      "LeBon",
      "4",
      "Coin",
      "LeBon",
      "7",
      "8",
      "LeBon",
      "Coin",
      "11",
      "LeBon",
      "13",
      "14",
      "LeBonCoin",
    )
  }

})
