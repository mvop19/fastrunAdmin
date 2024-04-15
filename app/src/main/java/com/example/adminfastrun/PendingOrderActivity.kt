package com.example.adminfastrun

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminfastrun.adapter.OrderStatusAdapter
import com.example.adminfastrun.adapter.PendingOrderAdapter
import com.example.adminfastrun.databinding.ActivityPendingOrderBinding
import com.example.adminfastrun.databinding.PendingOrdersItemBinding

class PendingOrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPendingOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPendingOrderBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.backButton.setOnClickListener{
            finish()
        }
        val orderedCustomerName = arrayListOf(
            "Matt Zapata",
            "Michael Pangelinan",
            "Dr. Lee"
        )
        val orderedQuantity = arrayListOf(
            "8",
            "6",
            "5"
        )
        val orderedFoodImage = arrayListOf(R.drawable.coffee,R.drawable.tea,R.drawable.frappe)
        val adapter = PendingOrderAdapter(orderedCustomerName,orderedQuantity,orderedFoodImage, this)
        binding.pendingOrderRecyclerView.adapter = adapter
        binding.pendingOrderRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}