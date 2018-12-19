package com.tw4gamers.mvrxk.view.fragment

import com.airbnb.mvrx.BaseMvRxFragment
import io.reactivex.disposables.CompositeDisposable


abstract class BaseFragment : BaseMvRxFragment() {
  val compositeDisposable = CompositeDisposable()
}