package xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import xyz.gerardbosch.leboncoinfizzbuzz.application.ComputeFizzBuzzUseCase
import xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzgeneration.FizzBuzzGenerator

@Configuration
class ComputeFizzBuzzConfig {

  @Bean
  fun computeFizzBuzz(generator: FizzBuzzGenerator) =
    ComputeFizzBuzzUseCase(generator)

  @Bean
  fun fizzBuzzGenerator(
    @Value("\${app.fizzbuzz.start}") start: Int,
  ) =
    FizzBuzzGenerator(start = start)

}
