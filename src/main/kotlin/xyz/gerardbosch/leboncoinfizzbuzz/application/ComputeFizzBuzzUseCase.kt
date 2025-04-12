package xyz.gerardbosch.leboncoinfizzbuzz.application

import kotlinx.coroutines.flow.Flow
import xyz.gerardbosch.leboncoinfizzbuzz.domain.FizzBuzzParams
import xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzgeneration.FizzBuzzGenerator

class ComputeFizzBuzzUseCase(
  val updateStats: UpdateStatsUseCase,
  val generateFizzBuzz: FizzBuzzGenerator,
) {

  operator fun invoke(params: FizzBuzzParams): Flow<String> {

    updateStats(params)

    return generateFizzBuzz(params)
  }

}
