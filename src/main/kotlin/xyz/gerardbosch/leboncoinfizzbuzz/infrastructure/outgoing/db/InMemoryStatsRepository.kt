package xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.outgoing.db

import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import xyz.gerardbosch.leboncoinfizzbuzz.domain.FizzBuzzParams
import xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzstats.Stats
import xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzstats.StatsRepository

class InMemoryStatsRepository : StatsRepository {

  private data class MostFrequent(
    val count: UInt,
    val params: FizzBuzzParams,
  )

  // REVIEWER-NOTE: UInt enforces strong semantics (negatives not allowed)
  private val hitsByRequest = mutableMapOf<FizzBuzzParams, UInt>()
  private val lock = Any()

  private var mostFrequent: MostFrequent? = null


  override fun updateStats(params: FizzBuzzParams) {

    fun updateCounters(params: FizzBuzzParams): UInt =
      hitsByRequest.merge(params, 1u, UInt::plus)!!

    fun updateMostFrequent(params: FizzBuzzParams, hitCount: UInt) {
      if (mostFrequent == null || hitCount >= mostFrequent!!.count) {
        mostFrequent = MostFrequent(hitCount, params) // side effects: mutation
      }
    }

    synchronized(lock) {
      val hitCount = updateCounters(params)
      updateMostFrequent(params, hitCount)
    }
  }

  override fun getMostFrequent(): Option<Stats> =
    mostFrequent
      ?.let { Some(Stats(it.count, it.params)) }
      ?: None

}
