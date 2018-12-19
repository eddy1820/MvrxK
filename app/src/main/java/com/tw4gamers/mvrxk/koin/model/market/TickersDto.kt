package com.example.eddy.tkr.model.market

data class TickersDto(
    val result: Result,
    val success: Boolean
)

data class Result(
    val tickers: List<Ticker>
)

data class Ticker(
//    val 24h_high: String,
//    val 24h_low: String,
//    val 24h_open: String,
//    val 24h_volume: String,
    val highest_bid: String,
    val last_trade_price: String,
    val lowest_ask: String,
    val timestamp: Long,
    val trading_pair_id: String
)