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
import com.example.adminfastrun.model.OrderDetails
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class OrderStatusActivity : AppCompatActivity() {
    private val binding :ActivityOrderStatusBinding by lazy {
        ActivityOrderStatusBinding.inflate(layoutInflater)
    }
    private lateinit var database: FirebaseDatabase
    private var listOfCompleteOrderList:ArrayList<OrderDetails> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.backButton.setOnClickListener{
            finish()
        }

        // retrieve and display completed order
        retrieveCompleteOrderDetail()




    }

    private fun retrieveCompleteOrderDetail() {
        // initialize Firebase database
        database = FirebaseDatabase.getInstance()
        val completeOrderReference = database.reference.child("CompletedOrder")
            .orderByChild("currentTime")
        completeOrderReference.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                // clear the list before populating it with new data
                listOfCompleteOrderList.clear()


                for (orderSnapshot in snapshot.children){
                    val completeOrder = orderSnapshot.getValue(OrderDetails::class.java)
                    completeOrder?.let {
                        listOfCompleteOrderList.add(it)
                    }

                }
                // reverse the list to display latest order first
                listOfCompleteOrderList.reverse()

                setDataIntoRecyclerView()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun setDataIntoRecyclerView() {
        //initialization list to hold customer name and status
        val customerName = mutableListOf<String>()
        val moneyStatus = mutableListOf<Boolean>()

        for (order in listOfCompleteOrderList){
            order.userName?.let {
                customerName.add(it)

                moneyStatus.add(order.paymentReceived)
            }
            val adapter = OrderStatusAdapter(customerName,moneyStatus)
            binding.orderStatusRecyclerView.adapter = adapter
            binding.orderStatusRecyclerView.layoutManager = LinearLayoutManager(this)
        }
    }
}