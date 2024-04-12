package com.example.adminfastrun.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adminfastrun.databinding.OrderStatusItemBinding

class OrderStatusAdapter(private val customerNames:ArrayList<String>,
    private val moneyStatus:ArrayList<String>
    ) : RecyclerView.Adapter<OrderStatusAdapter.OrderStatusViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderStatusViewHolder {
        val binding = OrderStatusItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OrderStatusViewHolder(binding)
    }



    override fun onBindViewHolder(holder: OrderStatusViewHolder, position: Int) {
        holder.bind(position)
    }
    override fun getItemCount(): Int = customerNames.size
    inner class OrderStatusViewHolder(private val binding: OrderStatusItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                customerName.text = customerNames[position]
                statusMoney.text = moneyStatus[position]
                val colorMap = mapOf(
                    "received" to Color.GREEN,
                    "notReceived" to Color.RED,
                    "Pending" to Color.GRAY
                )
                statusMoney.setTextColor(colorMap[moneyStatus[position]]?:Color.BLACK)
                statusColor.backgroundTintList = ColorStateList.valueOf(colorMap[moneyStatus[position]]?:Color.BLACK)

            }
        }

    }
}