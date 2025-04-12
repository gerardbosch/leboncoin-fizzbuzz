package xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzgeneration

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import xyz.gerardbosch.leboncoinfizzbuzz.domain.FizzBuzzParams

class FizzBuzzGenerator(
  val start: Int,
) {

  operator fun invoke(params: FizzBuzzParams): Flow<String> {

    val fizzNum = params.fizzNum.value
    val buzzNum = params.buzzNum.value
    val fizzText = params.fizzText.value
    val buzzText = params.buzzText.value

    // REVIEWER-NOTE: Using Flow for lazy and non-blocking processing. That avoids in-memory buffering (lazy stream) and
    //  blocking the event-loop thread (non-blocking). N.B. IntRange does not expand or evaluate when created.

    // TODO rename to generateToken
    fun fizzBuzzString(num: Int): String = when {
      num % fizzNum == 0 && num % buzzNum == 0 -> "${fizzText}${buzzText}"
      num % fizzNum == 0 -> fizzText
      num % buzzNum == 0 -> buzzText
      else -> num.toString()
    }

    return (start..params.limit.value).asFlow()
      .map(::fizzBuzzString)
      .flowOn(Dispatchers.Default) // offload computation to a CPU-bound thread pool
  }

}
