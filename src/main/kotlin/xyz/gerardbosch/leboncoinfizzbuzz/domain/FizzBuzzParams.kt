package xyz.gerardbosch.leboncoinfizzbuzz.domain

import xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzgeneration.BuzzNum
import xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzgeneration.BuzzText
import xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzgeneration.FizzNum
import xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzgeneration.FizzText
import xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzgeneration.Limit

data class FizzBuzzParams(
  val limit: Limit,
  val fizzNum: FizzNum,
  val buzzNum: BuzzNum,
  val fizzText: FizzText,
  val buzzText: BuzzText,
)
