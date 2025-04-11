package xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import xyz.gerardbosch.leboncoinfizzbuzz.application.ComputeFizzBuzzUseCase

@Configuration
class ComputeFizzBuzzConfig {

  @Bean
  fun computeFizzBuzz(
    @Value("\${app.fizzbuzz.start}") start: Int,
    @Value("\${app.fizzbuzz.delimiter}") delimiter: String,
  ) =
    ComputeFizzBuzzUseCase(start, delimiter)

}
