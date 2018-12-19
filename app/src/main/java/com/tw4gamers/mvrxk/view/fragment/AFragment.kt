package com.tw4gamers.mvrxk.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.epoxy.EpoxyController
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.example.eddy.tkr.util.addTo
import com.tw4gamers.mvrxk.R
import com.tw4gamers.mvrxk.viewmodel.AState
import com.tw4gamers.mvrxk.viewmodel.AViewModel
import kotlinx.android.synthetic.main.fragment_a.*


class AFragment : BaseFragment() {
  private val viewModel: AViewModel by fragmentViewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let {
    }
  }

  override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_a, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel.asyncSubscribe(AState::request, onSuccess = {
      Log.e(TAG, "A onSuccess:" + it.result.tickers[0].highest_bid)
      tickersListView.updateItem(it.result.tickers)
    },onFail = {
      Log.e(TAG,"onFail")
    })


    tickersListView.onRefreshSubject().subscribe {
      viewModel.getTickers()
    }.addTo(compositeDisposable)

  }

  override fun invalidate() {
    withState(viewModel) { state ->
      Log.e(TAG, "state:"+state.javaClass)
      tickersListView.setPageState(state)
    }
  }

  companion object {
    const val TAG = "==AFragment"
    @JvmStatic
    fun newInstance() =
        AFragment().apply {
          arguments = Bundle().apply {
          }
        }
  }
}
