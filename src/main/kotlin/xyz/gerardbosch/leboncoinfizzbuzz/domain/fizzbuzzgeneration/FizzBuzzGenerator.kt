package xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzgeneration

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class FizzBuzzGenerator(
  val start: Int,
) {

  operator fun invoke(
    limit: Limit,
    fizzNum: FizzNum,
    buzzNum: BuzzNum,
    fizzText: FizzText,
    buzzText: BuzzText,
  ): Flow<String> {

    // REVIEWER-NOTE: Using Flow for lazy and non-blocking processing. That avoids in-memory buffering (lazy stream) and
    //  blocking the event-loop thread (non-blocking). N.B. IntRange does not expand or evaluate when created.

    // TODO rename to generateToken
    fun fizzBuzzString(num: Int): String = when {
      num % fizzNum.value == 0 && num % buzzNum.value == 0 -> "${fizzText.value}${buzzText.value}"
      num % fizzNum.value == 0 -> fizzText.value
      num % buzzNum.value == 0 -> buzzText.value
      else -> num.toString()
    }

    return (start..limit.value).asFlow()
      .map(::fizzBuzzString)
      .flowOn(Dispatchers.Default) // offload computation to a CPU-bound thread pool
  }
}
