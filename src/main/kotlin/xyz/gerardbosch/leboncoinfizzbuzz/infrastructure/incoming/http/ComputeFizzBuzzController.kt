package xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.incoming.http

import kotlinx.coroutines.reactor.asFlux
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import xyz.gerardbosch.leboncoinfizzbuzz.application.ComputeFizzBuzzUseCase
import xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.incoming.http.api.ComputeFizzBuzzApi
import xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.incoming.http.api.ComputeFizzBuzzReq
import xyz.gerardbosch.leboncoinfizzbuzz.util.log

@RestController
class ComputeFizzBuzzController(
  private val computeFizzBuzz: ComputeFizzBuzzUseCase,
) : ComputeFizzBuzzApi {

  override fun getFizzBuzz(req: ComputeFizzBuzzReq): Flux<String> {
    log.info("Received request: $req")

    return computeFizzBuzz(req.toCmd())
      .asFlux()
  }
}

private fun ComputeFizzBuzzReq.toCmd(): ComputeFizzBuzzUseCase.Cmd {
  return ComputeFizzBuzzUseCase.Cmd(
    limit = limit,
    fizzNum = fizzNum,
    buzzNum = buzzNum,
    fizzText = fizzText,
    buzzText = buzzText,
  )
}
