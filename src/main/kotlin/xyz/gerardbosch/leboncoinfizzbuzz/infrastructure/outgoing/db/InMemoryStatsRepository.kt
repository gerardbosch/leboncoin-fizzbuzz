package xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.outgoing.db

import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import xyz.gerardbosch.leboncoinfizzbuzz.domain.FizzBuzzParams
import xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzstats.Stats
import xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzstats.StatsRepository

class InMemoryStatsRepository : StatsRepository {

  // REVIEWER-NOTE: UInt enforces strong semantics (negatives not allowed)
  private val hitsByRequest = mutableMapOf<FizzBuzzParams, UInt>()

  private var mostFrequent: Option<Pair<FizzBuzzParams, UInt>> = None

  override fun getMostFrequent(): Option<Stats> =
    mostFrequent.fold(
      { None },
      { Some(Stats(it.second, it.first)) }
    )

}
