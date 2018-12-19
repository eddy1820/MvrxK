package com.tw4gamers.mvrxk.view.witget

import android.content.Context
import android.util.AttributeSet
import com.example.eddy.tkr.model.market.Ticker
import com.example.eddy.tkr.view.util.withModels
import com.tw4gamers.mvrxk.view.itemview.itemTickersView
import kotlinx.android.synthetic.main.tickers_list_view.view.*

class TickersBListView @kotlin.jvm.JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TickersListView(context, attrs, defStyleAttr) {
    override fun createItem(tickers: List<Ticker>?) {
        recyclerView.withModels {
            tickers?.forEachIndexed { index, ticker ->
                itemTickersView {
                    id(index)
                    bindData(ticker)
                    selectColor(false)
                }
            }
        }
    }
}