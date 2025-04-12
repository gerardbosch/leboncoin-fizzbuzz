package xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzstats

import arrow.core.Option

fun interface StatsRepository {

  /** Return None when no stats are available yet. */
  fun getMostFrequent(): Option<Stats>

  // TODO
//  fun updateStats(): Unit
}
