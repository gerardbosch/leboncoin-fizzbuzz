package xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.incoming.http.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping

interface GetStatsApi {

  @GetMapping("fizzbuzz/stats/most-frequent")
  fun getMostFrequent(): ResponseEntity<StatsMostFreqResp>

  data class StatsMostFreqResp(
    val hitCount: Int,
    val request: RequestedData,
  ) {
    data class RequestedData(
      val limit: Int,
      val fizzNum: Int,
      val buzzNum: Int,
      val fizzText: String,
      val buzzText: String,
    )
  }

}
