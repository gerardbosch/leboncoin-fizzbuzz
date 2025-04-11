package xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.incoming.http

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.reactive.server.WebTestClient
import xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.incoming.http.api.ComputeFizzBuzzReq

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class ComputeFizzBuzzControllerIntegrationTest(

  private val webTestClient: WebTestClient
) {

  @Test
  fun `should respond with a correct FizzBuzz stream`() {
    // Given
    val req = ComputeFizzBuzzReq(
      limit = 25,
      fizzNum = 3,
      buzzNum = 5,
      fizzText = "LeBon",
      buzzText = "Coin",
    )
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
        12LeBon4CoinLeBon78LeBonCoin11LeBon1314LeBonCoin1617LeBon19CoinLeBon2223LeBonCoin
      """.trimIndent())
  }

}
