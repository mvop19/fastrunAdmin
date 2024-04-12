package com.example.adminfastrun

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminfastrun.adapter.OrderStatusAdapter
import com.example.adminfastrun.databinding.ActivityOrderStatusBinding
import com.example.adminfastrun.databinding.OrderStatusItemBinding

class OrderStatusActivity : AppCompatActivity() {
    private val binding :ActivityOrderStatusBinding by lazy {
        ActivityOrderStatusBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        val customerName = arrayListOf(
            "Matt Zapata",
            "Michael Pangelinan",
            "Dr. Lee"
        )
        val moneyStatus = arrayListOf(
            "received",
            "notReceived",
            "Pending"
        )
        val adapter = OrderStatusAdapter(customerName,moneyStatus)
        binding.orderStatusRecyclerView.adapter = adapter
        binding.orderStatusRecyclerView.layoutManager = LinearLayoutManager(this)


    }
}