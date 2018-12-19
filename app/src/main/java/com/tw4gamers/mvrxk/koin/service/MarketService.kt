package com.tw4gamers.mvrxk.koin.service

import com.example.eddy.tkr.model.market.TickersDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface MarketService {
  @GET("v1/market/tickers")
  fun tickers(): Single<TickersDto>
}


