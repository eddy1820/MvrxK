package com.tw4gamers.mvrxk.view.itemview

import android.content.Context
import android.text.format.DateUtils
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.OnViewRecycled
import com.airbnb.epoxy.OnVisibilityChanged
import com.airbnb.epoxy.OnVisibilityStateChanged
import com.airbnb.epoxy.VisibilityState
import com.example.eddy.tkr.model.market.Ticker
import com.example.eddy.tkr.util.getSupportColor
import com.tw4gamers.mvrxk.R
import kotlinx.android.synthetic.main.item_tickers_view.view.*
import java.util.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class ItemTickersView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var ticker: Ticker

    init {
        inflate(context, R.layout.item_tickers_view, this)
        orientation = VERTICAL
    }

    private var isRed: Boolean = false

    @ModelProp
    fun bindData(ticker: Ticker) {
        this.ticker = ticker
    }

    @ModelProp
    fun selectColor(isRed: Boolean) {
        this.isRed = isRed
    }

    var listener: View.OnClickListener? = null
        @CallbackProp set


    @AfterPropsSet
    fun useProps() {
        setText()
        setTextColor()
    }

    private fun setText() {
        tradingPairIdLabel.text = ticker.trading_pair_id
        lastTradePriceLabel.text = ticker.last_trade_price
        highestBidlabel.text = ticker.highest_bid
        lowestAskLabel.text = ticker.lowest_ask
        timestampLabel.text = DateUtils.getRelativeTimeSpanString(
            ticker.timestamp,
            Date().time,
            0L,
            DateUtils.FORMAT_ABBREV_ALL
        ).toString()
    }

    private fun setTextColor() {
        tradingPairIdLabel.setTextColor(selectTextColor())
        lastTradePriceLabel.setTextColor(selectTextColor())
        highestBidlabel.setTextColor(selectTextColor())
        lowestAskLabel.setTextColor(selectTextColor())
        timestampLabel.setTextColor(selectTextColor())
    }

    private fun selectTextColor(): Int =
        resources.getSupportColor(if (isRed) R.color.material_red200 else R.color.material_green200)


    @OnVisibilityStateChanged
    fun onVisibilityStateChanged(
        @VisibilityState.Visibility visibilityState: Int
    ) {
        when (visibilityState) {
            VisibilityState.VISIBLE -> {
            }
            VisibilityState.INVISIBLE -> {
            }
            VisibilityState.FOCUSED_VISIBLE -> {
            }
            VisibilityState.UNFOCUSED_VISIBLE -> {
            }
            VisibilityState.FULL_IMPRESSION_VISIBLE -> {
            }
        }
    }

    @OnVisibilityChanged
    fun onVisibilityChanged(
        percentVisibleHeight: Float,
        percentVisibleWidth: Float,
        visibleHeight: Int,
        visibleWidth: Int
    ) {

    }

    @OnViewRecycled
    fun clear() {
    }


}
