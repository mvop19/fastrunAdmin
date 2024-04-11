package com.example.adminfastrun

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminfastrun.adapter.AddItemAdapter
import com.example.adminfastrun.databinding.ActivityAllItemBinding

class AllItemActivity : AppCompatActivity() {
    private val binding : ActivityAllItemBinding by lazy {
        ActivityAllItemBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        val menuFoodName = listOf("Coffee", "Tea", "Frappe", "Item","Item", "Item")
        val menuItemPrice = listOf("$5", "$6", "$7", "$10", "$10", "$10")
        val menuImage = listOf(
            R.drawable.coffee,
            R.drawable.tea,
            R.drawable.frappe,
            R.drawable.frappe,
            R.drawable.frappe,
            R.drawable.frappe)
        binding.backButton.setOnClickListener {
            finish()
        }
        val adapter = AddItemAdapter(
            ArrayList(menuFoodName),
            ArrayList(menuItemPrice),
            ArrayList(menuImage))
        binding.MenuRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.MenuRecyclerView.adapter = adapter
    }
}