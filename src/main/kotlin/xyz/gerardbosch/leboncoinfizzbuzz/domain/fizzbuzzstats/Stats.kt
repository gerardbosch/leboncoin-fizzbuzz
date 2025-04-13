package xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzstats

import xyz.gerardbosch.leboncoinfizzbuzz.domain.FizzBuzzParams

data class Stats(
  val hitCount: UInt,
  val params: FizzBuzzParams,
)
