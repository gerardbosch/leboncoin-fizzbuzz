package xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.incoming.http.api

import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import reactor.core.publisher.Flux

interface ComputeFizzBuzzApi {

  @PostMapping("/fizzbuzz", consumes = [APPLICATION_JSON_VALUE], produces = ["text/csv"])
  fun getFizzBuzz(@RequestBody req: ComputeFizzBuzzReq): Flux<String>
}

// TODO Test and Add validation to the request
data class ComputeFizzBuzzReq(
  val limit: Int,
  val fizzNum: Int,
  val buzzNum: Int,
  val fizzText: String,
  val buzzText: String,
)
