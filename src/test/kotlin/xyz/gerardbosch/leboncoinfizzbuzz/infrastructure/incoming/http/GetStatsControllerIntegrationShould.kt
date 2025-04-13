package xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.incoming.http

import io.kotest.core.spec.style.StringSpec
import org.junit.jupiter.api.Tag
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Tag("integration-test")
@DirtiesContext
class GetStatsControllerIntegrationShould(
  private val webTestClient: WebTestClient,
) : StringSpec({

  "respond with 204 NoContent when no fizzbuzz has been computed" {
    // Given - No "/fizzbuzz" requests has been made
    // When
    webTestClient.get()
      .uri("/fizzbuzz/stats/most-frequent")
      .exchange()
      // Then
      .expectStatus().isNoContent
      .expectBody().isEmpty
  }

  "respond 200 OK with the stats when at least one fizzbuzz has been computed" {

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

  "respond 200 OK with the stats of latest request when there's a draw in frequency" {
    // Given - Two fizzbuzz have been computed with different parameters
    webTestClient.post()
      .uri("/fizzbuzz")
      .contentType(APPLICATION_JSON)
      .bodyValue(FixturesFizzBuzzCompute.validReq().copy(limit = 30))
      .exchange()
      .expectStatus().isOk

    webTestClient.post()
      .uri("/fizzbuzz")
      .contentType(APPLICATION_JSON)
      .bodyValue(FixturesFizzBuzzCompute.validReq().copy(limit = 15))
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
            "limit": 15,
            "fizzNum": 3,
            "buzzNum": 5,
            "fizzText": "LeBon",
            "buzzText": "Coin"
          }
        }
      """.trimIndent())
  }

})
