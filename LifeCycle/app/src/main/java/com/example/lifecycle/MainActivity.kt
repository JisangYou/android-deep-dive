package com.example.lifecycle

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    // case 1 usecase
    private lateinit var myLocationListener: MyLocationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myLocationListener = MyLocationListener(this, lifecycle) {
            Log.d("jay", "onCreate : $localClassName")
        }
        myLocationListener.create()

        // case 2 usecase
        lifecycle.addObserver(MyObserver())
    }

    override fun onStart() {
        super.onStart()
        Log.d("jay", "onStart : $localClassName")
        myLocationListener.start()

    }

    override fun onStop() {
        super.onStop()
        Log.d("jay", "onStop : $localClassName")
        myLocationListener.stop()
    }
}