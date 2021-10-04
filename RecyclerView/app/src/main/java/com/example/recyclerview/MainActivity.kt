package com.example.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        adapter = Adapter()
        binding.rv.adapter = adapter



        binding.btn.setOnClickListener {
            setDummyData()
            update()
        }
    }

    private fun setDummyData() {
        for (i in 0..20) {
            adapter.itemDataList.add(ItemData("Jay $i"))
        }
    }

    private fun update() {
        adapter.update()
    }
}