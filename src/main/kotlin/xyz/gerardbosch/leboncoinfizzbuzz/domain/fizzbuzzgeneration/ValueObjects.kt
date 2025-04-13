package xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzgeneration

@JvmInline
value class Limit private constructor(val value: Int) {
  companion object {
    operator fun invoke(value: Int): Limit {
      require(value > 0) { "${Limit::class.simpleName} must be greater than 0" }
      return Limit(value)
    }
  }
}

@JvmInline
value class FizzNum private constructor(val value: Int) {
  companion object {
    operator fun invoke(value: Int): FizzNum {
      require(value > 0) { "${FizzNum::class.simpleName} must be greater than 0" }
      return FizzNum(value)
    }
  }
}

@JvmInline
value class BuzzNum private constructor(val value: Int) {
  companion object {
    operator fun invoke(value: Int): BuzzNum {
      require(value > 0) { "${BuzzNum::class.simpleName} must be greater than 0" }
      return BuzzNum(value)
    }
  }
}

@JvmInline
value class FizzText(val value: String)

@JvmInline
value class BuzzText(val value: String)
