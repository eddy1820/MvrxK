package com.tw4gamers.mvrxk.view.witget


import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.airbnb.epoxy.SimpleEpoxyController
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.example.eddy.tkr.model.market.Ticker
import com.tw4gamers.mvrxk.R
import com.tw4gamers.mvrxk.viewmodel.AState
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.tickers_list_view.view.*


abstract class TickersListView @kotlin.jvm.JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {
  private val compositeDisposable = CompositeDisposable()
  private val onRefreshSubject = PublishSubject.create<Unit>()

  init {
    apply {
      LayoutInflater.from(context).inflate(R.layout.tickers_list_view, this)
      swipeRefreshLayout.setOnRefreshListener {
        onRefreshSubject.onNext(Unit)
      }
    }
  }

  fun onRefreshSubject(): Observable<Unit> = onRefreshSubject

  fun updateItem(tickers: List<Ticker>) {
    createItem(tickers)
    swipeRefreshLayout.isRefreshing = false
  }

  fun setPageState(state: AState) =
      when (state.request) {
        is Uninitialized -> {

        }
        is Success -> {
          createItem(state.tickers)
          failLayout.visibility = View.GONE
          loadingLayout.visibility = View.GONE
          swipeRefreshLayout.isRefreshing = false
        }
        is Loading -> {
          failLayout.visibility = View.GONE
          loadingLayout.visibility = View.VISIBLE
          swipeRefreshLayout.isRefreshing = false
        }
        is Fail -> {
          failLayout.visibility = View.VISIBLE
          loadingLayout.visibility = View.GONE
          swipeRefreshLayout.isRefreshing = false
        }
      }


  abstract fun createItem(tickers: List<Ticker>?)

  fun destroy() {
    compositeDisposable.dispose()
  }

}