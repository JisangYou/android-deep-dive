package com.example.livedata

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.livedata.databinding.ActivityMainBinding
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {

    private val model: NameViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        val nameObserver = Observer<String> { newName ->
            binding.tvName.text = newName
        }

        binding.btnName.setOnClickListener {
            val anotherName = "John Doe"
            model.currentName.value = anotherName
        }
        model.currentName.observe(this, nameObserver)

        StockLiveData.get("symbol").observe(this, { price: BigDecimal? ->
            // Update the UI.
            Log.d("jay","price")
        })
    }
}

