package com.example.adminfastrun.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adminfastrun.databinding.ItemItemBinding
import com.example.adminfastrun.model.AllMenu
import com.google.firebase.database.DatabaseReference

class MenuItemAdapter(
    private val context: Context,
    private val menuList: ArrayList<AllMenu>,
    databaseReference: DatabaseReference,
    private val onDeleteClickListener:(positon: Int) -> Unit
) : RecyclerView.Adapter<MenuItemAdapter.AddItemViewHolder>() {

    private val itemQuantities = IntArray(menuList.size){1}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddItemViewHolder {
        val binding = ItemItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AddItemViewHolder(binding)
    }



    override fun onBindViewHolder(holder: AddItemViewHolder, position: Int) {
        holder.bind(position)
    }
    override fun getItemCount(): Int = menuList.size
    inner class AddItemViewHolder(private val binding : ItemItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantities[position]
                val menuItem = menuList[position]
                val uriString = menuItem.foodImage
                val uri = Uri.parse(uriString)
                foodnameTextView.text = menuItem.foodName
                priceTextView.text = menuItem.foodPrice
                Glide.with(context).load(uri).into(foodImageView)
                quantityTextView.text = quantity.toString()
                minusButton.setOnClickListener {
                    decreaseQuantity(position)


                }
                plusButton.setOnClickListener {
                    increaseQuantity(position)

                }
                deleteButton.setOnClickListener {
                    onDeleteClickListener(position)

                }
            }
        }
        private fun increaseQuantity(position: Int){
            if (itemQuantities[position]<10){
                itemQuantities[position]++
                binding.quantityTextView.text = itemQuantities[position].toString()
            }
        }
        private fun decreaseQuantity(position: Int){
            if (itemQuantities[position]>1){
                itemQuantities[position]--
                binding.quantityTextView.text = itemQuantities[position].toString()
            }
        }
        private fun deleteQuantity(position: Int) {
            menuList.removeAt(position)
            menuList.removeAt(position)
            menuList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,menuList.size)
        }
    }
}