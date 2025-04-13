package xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.incoming.http

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import xyz.gerardbosch.leboncoinfizzbuzz.application.GetStatsUseCase
import xyz.gerardbosch.leboncoinfizzbuzz.domain.fizzbuzzstats.Stats
import xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.incoming.http.api.GetStatsApi
import xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.incoming.http.api.GetStatsApi.StatsMostFreqResp
import xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.incoming.http.api.GetStatsApi.StatsMostFreqResp.RequestedData
import xyz.gerardbosch.leboncoinfizzbuzz.util.log

@RestController
class GetStatsController(
  private val getStats: GetStatsUseCase
) : GetStatsApi {

  override fun getMostFrequent(): ResponseEntity<StatsMostFreqResp> {

    log.info("Received request to get the most frequent stats")

    return getStats()
      .map(Stats::toRespBody)
      .fold(
        { ResponseEntity.noContent().build() },
        { ResponseEntity.ok(it) }
      )
  }
}

private fun Stats.toRespBody() =
  StatsMostFreqResp(
    hitCount = hitCount.toInt(),
    request = RequestedData(
      limit = params.limit.value,
      fizzNum = params.fizzNum.value,
      buzzNum = params.buzzNum.value,
      fizzText = params.fizzText.value,
      buzzText = params.buzzText.value,
    )
  )
