package com.tw4gamers.mvrxk

import android.support.multidex.MultiDexApplication
import com.tw4gamers.mvrxk.koin.koin.serviceModule
import org.koin.android.ext.android.startKoin

class MyApplication : MultiDexApplication() {


  override fun onCreate() {
    super.onCreate()
    startKoin(this, listOf(serviceModule))
  }


}