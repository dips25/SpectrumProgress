package com.anim.spectrumprogress

import android.content.Context
import android.util.DisplayMetrics

object Utils {

    fun getPxFromDp(c: Context, dp:Int):Int {

        val scaleFactor = c.resources.displayMetrics.densityDpi/DisplayMetrics.DENSITY_DEFAULT

        return dp*scaleFactor;

    }
}