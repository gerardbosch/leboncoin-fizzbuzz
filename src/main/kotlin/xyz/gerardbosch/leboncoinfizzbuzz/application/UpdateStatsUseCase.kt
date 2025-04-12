package xyz.gerardbosch.leboncoinfizzbuzz.application

import xyz.gerardbosch.leboncoinfizzbuzz.domain.FizzBuzzParams
import xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzstats.StatsRepository

class UpdateStatsUseCase(
  val repo: StatsRepository,
) {
  operator fun invoke(params: FizzBuzzParams) =
    repo.updateStats(params)
}
