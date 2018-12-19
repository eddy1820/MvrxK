package com.tw4gamers.mvrxk.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.fragmentViewModel
import com.tw4gamers.mvrxk.R
import com.tw4gamers.mvrxk.viewmodel.CViewModel
import kotlinx.android.synthetic.main.fragment_c.*


class CFragment : BaseMvRxFragment() {
  private val viewModel by fragmentViewModel(CViewModel::class)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let {
    }
  }

  override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_c, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    btn.setOnClickListener {
      viewModel.setTitle(titleEdit.text.toString())
    }
  }

  override fun invalidate() {
    viewModel.subscribe { state ->
      tv.text = state.title
    }

  }


  companion object {
    @JvmStatic
    fun newInstance() =
        AFragment().apply {
          arguments = Bundle().apply {
          }
        }
  }
}
