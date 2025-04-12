package xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import xyz.gerardbosch.leboncoinfizzbuzz.application.GetStatsUseCase
import xyz.gerardbosch.leboncoinfizzbuzz.application.UpdateStatsUseCase
import xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzstats.StatsRepository
import xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.outgoing.db.InMemoryStatsRepository

@Configuration
class StatsConfig {

  @Bean
  fun updateStatsUseCase(repo: StatsRepository) =
    UpdateStatsUseCase(repo)

  @Bean
  fun getStatsUseCase(repo: StatsRepository) =
    GetStatsUseCase(repo)

  @Bean
  fun statsRepository(): StatsRepository =
    InMemoryStatsRepository()

}
