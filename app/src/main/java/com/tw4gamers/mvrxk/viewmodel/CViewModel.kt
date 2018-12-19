package com.tw4gamers.mvrxk.viewmodel

import android.util.Log
import com.airbnb.mvrx.MvRxState


data class CState(val title: String = "") : MvRxState

class CViewModel(initialState: CState) : MvRxViewModel<CState>(initialState) {
  fun setTitle(title: String) {
    withState { state ->
      Log.e("==", "CState")
    }
    setState {
      copy(title = title)
    }
  }

}