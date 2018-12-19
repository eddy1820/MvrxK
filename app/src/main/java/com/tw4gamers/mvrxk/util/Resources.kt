package com.example.eddy.tkr.util

import android.content.res.Resources
import android.os.Build
import android.support.annotation.ColorRes


@Suppress("DEPRECATION")
fun Resources.getSupportColor(@ColorRes id: Int): Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    this.getColor(id, null)
} else {
    this.getColor(id)
}