package com.example.livedata

import android.util.Log
import java.math.BigDecimal

class StockManager(symbol: String) {
    fun requestPriceUpdates(listener: (BigDecimal) -> Unit) {
        Log.d("jay", "requestPriceUpdates")
    }

    fun removeUpdates(listener: (BigDecimal) -> Unit) {
        Log.d("jay", "removeUpdates")
    }

}
