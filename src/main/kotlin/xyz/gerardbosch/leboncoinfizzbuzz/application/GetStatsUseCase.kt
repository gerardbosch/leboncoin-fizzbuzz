package xyz.gerardbosch.leboncoinfizzbuzz.application

import xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzstats.StatsRepository

class GetStatsUseCase(
  private val repo: StatsRepository,
) {
  operator fun invoke() =
    repo.getMostFrequent()
}
