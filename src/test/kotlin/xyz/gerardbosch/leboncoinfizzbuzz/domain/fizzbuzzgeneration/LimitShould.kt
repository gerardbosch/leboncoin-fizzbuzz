package xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzgeneration

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll
import org.junit.jupiter.api.Tag

@Tag("unit-test")
class LimitShould : StringSpec({

  fun positiveInts(): Arb<Int> =
    Arb.int(1..Int.MAX_VALUE)

  fun negativeIntsWithZero(): Arb<Int> =
    Arb.int(Int.MIN_VALUE..0)


  "create a Limit for all positive integers" {
    checkAll(positiveInts()) { value ->
      val limit = Limit(value)
      limit.value shouldBe value
    }
  }

  "throw an exception when creating a Limit for all non-positive values including zero" {
    checkAll(negativeIntsWithZero()) { value ->
      shouldThrow<IllegalArgumentException> { Limit(value) }
    }
  }

})
