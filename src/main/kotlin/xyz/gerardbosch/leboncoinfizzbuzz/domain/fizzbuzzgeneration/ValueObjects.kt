package xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzgeneration

@JvmInline
value class Limit private constructor(val value: Int) {

  companion object {
    operator fun invoke(value: Int): Limit {
      require(value > 0) { "Limit must be greater than 0" }
      return Limit(value)
    }
  }
}

@JvmInline
value class FizzNum(val value: Int)

@JvmInline
value class BuzzNum(val value: Int)

@JvmInline
value class FizzText(val value: String)

@JvmInline
value class BuzzText(val value: String)
