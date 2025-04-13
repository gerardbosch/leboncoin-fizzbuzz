package xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.incoming.http

import io.kotest.core.spec.style.StringSpec
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.reactive.server.WebTestClient
import xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.incoming.http.FixturesFizzBuzzCompute.validReq

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
// TODO rename to IntegrationShould
class ComputeFizzBuzzControllerIntegrationTest(
  private val webTestClient: WebTestClient,
) : StringSpec({

  "respond with a correct FizzBuzz stream" {
    // Given
    val req = validReq()
    // When
    webTestClient.post()
      .uri("/fizzbuzz")
      .contentType(APPLICATION_JSON)
      .bodyValue(req)
      .exchange()
      // Then
      .expectStatus().isOk
      .expectHeader().contentType("text/csv;charset=UTF-8")
      .expectBody(String::class.java).isEqualTo("""
        1,2,LeBon,4,Coin,LeBon,7,8,LeBon,Coin,11,LeBon,13,14,LeBonCoin,16,17,LeBon,19,Coin,LeBon,22,23,LeBon,Coin
      """.trimIndent()
      )
  }

  "respond 400 when the request is invalid" {
    // Given
    val invalidReq = validReq().copy(limit = 0)
    // When
    webTestClient.post()
      .uri("/fizzbuzz")
      .contentType(APPLICATION_JSON)
      .bodyValue(invalidReq)
      .exchange()
      // Then
      .expectStatus().isBadRequest
      .expectBody().json(
        """
        {"message": "Limit must be greater than 0"}
      """.trimIndent()
      )
  }

  "respond 500 when the fizzbuzz computation fails" {
    // Given
    val invalidReq = validReq().copy(fizzNum = 0, buzzNum = 0)
    // When
    webTestClient.post()
      .uri("/fizzbuzz")
      .contentType(APPLICATION_JSON)
      .bodyValue(invalidReq)
      .exchange()
      // Then
      .expectStatus().is5xxServerError
      .expectBody().json(
        """
        {"message": "/ by zero"}
      """.trimIndent()
      )
  }

})

