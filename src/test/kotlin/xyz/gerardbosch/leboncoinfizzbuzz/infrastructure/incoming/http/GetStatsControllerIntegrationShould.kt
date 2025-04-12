package xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.incoming.http

import io.kotest.core.spec.style.StringSpec
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GetStatsControllerIntegrationShould(
  private val webTestClient: WebTestClient,
) : StringSpec({

  "should respond with 204 NoContent when no fizzbuzz has been computed" {
    // Given - No "/fizzbuzz" requests has been made
    // When
    webTestClient.get()
      .uri("/fizzbuzz/stats/most-frequent")
      .exchange()
      // Then
      .expectStatus().isNoContent
      .expectBody().isEmpty
  }

  "should respond with the stats when at least one fizzbuzz has been computed" {

    // Given - A fizzbuzz has been computed
    webTestClient.post()
      .uri("/fizzbuzz")
      .contentType(APPLICATION_JSON)
      .bodyValue(FixturesFizzBuzzCompute.validReq())
      .exchange()
      .expectStatus().isOk

    // When
    webTestClient.get()
      .uri("/fizzbuzz/stats/most-frequent")
      .exchange()
      // Then
      .expectStatus().isOk
      .expectBody().json("""
        {
          "hitCount": 1,
          "request" : {
            "limit": 25,
            "fizzNum": 3,
            "buzzNum": 5,
            "fizzText": "LeBon",
            "buzzText": "Coin"
          }
        }
      """.trimIndent())
  }

})
