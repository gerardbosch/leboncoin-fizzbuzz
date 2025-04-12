package xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.incoming.http

import xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.incoming.http.api.ComputeFizzBuzzReq

object FixturesFizzBuzzCompute {

  fun validReq() = ComputeFizzBuzzReq(
    limit = 25,
    fizzNum = 3,
    buzzNum = 5,
    fizzText = "LeBon",
    buzzText = "Coin",
  )

  fun invalidReq() =
    validReq().copy(limit = 0)

}

