package xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import xyz.gerardbosch.leboncoinfizzbuzz.application.ComputeFizzBuzzUseCase
import xyz.gerardbosch.leboncoinfizzbuzz.application.UpdateStatsUseCase
import xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzgeneration.FizzBuzzGenerator

@Configuration
class ComputeFizzBuzzConfig {

  @Bean
  fun computeFizzBuzzUseCase(
    updateStatsUseCase: UpdateStatsUseCase,
    generator: FizzBuzzGenerator,
  ) =
    ComputeFizzBuzzUseCase(updateStatsUseCase, generator)

  @Bean
  fun fizzBuzzGenerator(
    @Value("\${app.fizzbuzz.start}") start: Int,
  ) =
    FizzBuzzGenerator(start = start)

}
