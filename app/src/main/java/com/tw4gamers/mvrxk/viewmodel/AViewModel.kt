package com.tw4gamers.mvrxk.viewmodel

import android.support.v4.app.FragmentActivity
import android.util.Log
import com.airbnb.mvrx.*
import com.example.eddy.tkr.model.market.Ticker
import com.example.eddy.tkr.model.market.TickersDto
import com.tw4gamers.mvrxk.koin.service.MarketService
import org.koin.android.ext.android.inject


data class AState(
    val tickers: List<Ticker> = emptyList(),
    val request: Async<TickersDto> = Uninitialized
) : MvRxState

class AViewModel(initialState: AState,
                 private val marketService: MarketService) : MvRxViewModel<AState>(initialState) {
  companion object : MvRxViewModelFactory<AState> {
    @JvmStatic
    override fun create(activity: FragmentActivity, state: AState): BaseMvRxViewModel<AState> {
      val service: MarketService by activity.inject()
      return AViewModel(state, service)
    }
  }

  init {
    getTickers()
  }

  fun getTickers() = withState { state ->
    //        if (state.request is Loading) return@withState
    marketService.tickers().execute {
      copy(request = it, tickers = tickers + (it()?.result?.tickers ?: emptyList()))
    }
  }
}

