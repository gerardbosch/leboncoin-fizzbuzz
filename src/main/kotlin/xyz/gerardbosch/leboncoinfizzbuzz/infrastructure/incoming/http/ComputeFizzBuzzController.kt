package xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.incoming.http

import kotlinx.coroutines.reactor.asFlux
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import xyz.gerardbosch.leboncoinfizzbuzz.application.ComputeFizzBuzzUseCase
import xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzgeneration.BuzzNum
import xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzgeneration.BuzzText
import xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzgeneration.FizzNum
import xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzgeneration.FizzText
import xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzgeneration.Limit
import xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.incoming.http.api.ComputeFizzBuzzApi
import xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.incoming.http.api.ComputeFizzBuzzApi.ComputeFizzBuzzReq
import xyz.gerardbosch.leboncoinfizzbuzz.util.intersperse
import xyz.gerardbosch.leboncoinfizzbuzz.util.log

@RestController
class ComputeFizzBuzzController(
  @Value("\${app.fizzbuzz.delimiter}") private val delimiter: String,
  private val computeFizzBuzz: ComputeFizzBuzzUseCase,
) : ComputeFizzBuzzApi {

  override fun getFizzBuzz(req: ComputeFizzBuzzReq): Flux<String> {
    log.info("Received request to compute FizzBuzz: $req")

    return computeFizzBuzz(req.toCmd())
      .intersperse(delimiter)
      .asFlux()
  }
}

private fun ComputeFizzBuzzReq.toCmd(): ComputeFizzBuzzUseCase.Cmd {
  return ComputeFizzBuzzUseCase.Cmd(
    limit = Limit(limit),
    fizzNum = FizzNum(fizzNum),
    buzzNum = BuzzNum(buzzNum),
    fizzText = FizzText(fizzText),
    buzzText = BuzzText(buzzText),
  )
}
