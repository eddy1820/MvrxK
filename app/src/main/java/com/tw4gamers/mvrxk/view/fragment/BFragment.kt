package com.tw4gamers.mvrxk.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.fragmentViewModel
import com.tw4gamers.mvrxk.R
import com.tw4gamers.mvrxk.viewmodel.AViewModel


class BFragment : BaseMvRxFragment() {
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
    return inflater.inflate(R.layout.fragment_b, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

  }

  override fun invalidate() {
  }

  companion object {
    const val TAG = "==BFragment"
    @JvmStatic
    fun newInstance() =
        AFragment().apply {
          arguments = Bundle().apply {
          }
        }
  }

  override fun onDestroy() {
    super.onDestroy()
  }
}
