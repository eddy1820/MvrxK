package com.tw4gamers.mvrxk

import android.app.Activity
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.airbnb.mvrx.BaseMvRxActivity
import com.tw4gamers.mvrxk.MainActivity.TickersTabSource.Companion.available
import com.tw4gamers.mvrxk.view.fragment.AFragment
import com.tw4gamers.mvrxk.view.fragment.BFragment
import com.tw4gamers.mvrxk.view.fragment.CFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMvRxActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    initTabAndViewPager()
  }

  private fun initTabAndViewPager() {
    viewPager.adapter = TabViewPagerAdapter(this, supportFragmentManager)
    viewPager.offscreenPageLimit = available.count()
    tabLayout.setupWithViewPager(viewPager)
  }

  class TabViewPagerAdapter(
      private val activity: Activity,
      supportFragmentManager: FragmentManager
  ) : FragmentPagerAdapter(
      supportFragmentManager
  ) {
    override fun getItem(position: Int): Fragment =
        available[position].fragmentClass.newInstance()

    override fun getCount(): Int = available.count()
    override fun getPageTitle(position: Int): CharSequence? {
      return activity.getString(available[position].titleResId)
    }
  }

  enum class TickersTabSource(
      @param:StringRes val titleResId: Int,
      val fragmentClass: Class<out android.support.v4.app.Fragment>
  ) {

    A(
        R.string.a,
        AFragment::class.java
    ),
    B(
        R.string.b,
        BFragment::class.java
    ),
    C(
        R.string.c,
        CFragment::class.java
    );

    companion object {
      val available = listOf(
          A,
          B,
          C
      )
    }
  }
}
