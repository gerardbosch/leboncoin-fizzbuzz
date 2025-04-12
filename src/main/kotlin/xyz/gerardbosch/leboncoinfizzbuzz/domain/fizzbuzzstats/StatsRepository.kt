package xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzstats

import arrow.core.Option
import xyz.gerardbosch.leboncoinfizzbuzz.domain.FizzBuzzParams

interface StatsRepository {

  fun updateStats(params: FizzBuzzParams)

  /** Return None when no stats are available yet. */
  fun getMostFrequent(): Option<Stats>
}
