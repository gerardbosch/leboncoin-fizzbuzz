package xyz.gerardbosch.leboncoinfizzbuzz.application

import kotlinx.coroutines.flow.Flow
import xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzgeneration.BuzzNum
import xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzgeneration.BuzzText
import xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzgeneration.FizzBuzzGenerator
import xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzgeneration.FizzNum
import xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzgeneration.FizzText
import xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzgeneration.Limit

class ComputeFizzBuzzUseCase(
  val generateFizzBuzz: FizzBuzzGenerator,
) {

  operator fun invoke(cmd: Cmd): Flow<String> {

    // TODO Update statistics with the request

    return generateFizzBuzz(cmd.limit, cmd.fizzNum, cmd.buzzNum, cmd.fizzText, cmd.buzzText)
  }

  data class Cmd(
    val limit: Limit,
    val fizzNum: FizzNum,
    val buzzNum: BuzzNum,
    val fizzText: FizzText,
    val buzzText: BuzzText,
  )
}
