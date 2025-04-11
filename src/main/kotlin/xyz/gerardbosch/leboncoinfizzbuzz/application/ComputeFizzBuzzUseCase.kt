package xyz.gerardbosch.leboncoinfizzbuzz.application

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class ComputeFizzBuzzUseCase(val start: Int) {

  // TODO move business logic to a domain service
  operator fun invoke(cmd: Cmd): Flow<String> {

    // REVIEWER-NOTE: Using Flow for lazy and non-blocking processing. That avoids in-memory buffering (lazy stream) and
    //  blocking the event-loop thread (non-blocking).

    return (start..cmd.limit).asFlow()
      .map { num ->
        // TODO extract
        when {
          num % cmd.fizzNum == 0 && num % cmd.buzzNum == 0 -> "${cmd.fizzText}${cmd.buzzText}"
          num % cmd.fizzNum == 0 -> cmd.fizzText
          num % cmd.buzzNum == 0 -> cmd.buzzText
          else -> num.toString()
        }
      }
      .flowOn(Dispatchers.Default) // offload computation to a CPU-bound thread pool
  }

  data class Cmd(
    val limit: Int,
    val fizzNum: Int,
    val buzzNum: Int,
    val fizzText: String,
    val buzzText: String,
  )
}
