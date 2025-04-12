package xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.incoming.http

import xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.incoming.http.api.ComputeFizzBuzzApi


object FixturesFizzBuzzCompute {

  fun validReq() = ComputeFizzBuzzApi.ComputeFizzBuzzReq(
    limit = 25,
    fizzNum = 3,
    buzzNum = 5,
    fizzText = "LeBon",
    buzzText = "Coin",
  )

}

