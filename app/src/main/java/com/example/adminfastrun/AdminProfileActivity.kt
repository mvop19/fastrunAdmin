package com.example.adminfastrun

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adminfastrun.databinding.ActivityAdminProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AdminProfileActivity : AppCompatActivity() {
    private val binding : ActivityAdminProfileBinding by lazy {
        ActivityAdminProfileBinding.inflate(layoutInflater)
    }
    private lateinit var auth:FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var adminReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        adminReference = database.reference.child("user")


        binding.backButton.setOnClickListener {
            finish()
        }
        binding.saveInfoButton.setOnClickListener {
            updateUserData()
        }
        binding.name.isEnabled= false
        binding.address.isEnabled= false
        binding.email.isEnabled= false
        binding.phone.isEnabled= false
        binding.password.isEnabled= false
        binding.saveInfoButton.isEnabled = false


        var isEnable = false
        binding.editButton.setOnClickListener {
            isEnable = ! isEnable
            binding.name.isEnabled= isEnable
            binding.address.isEnabled= isEnable
            binding.email.isEnabled= isEnable
            binding.phone.isEnabled= isEnable
            binding.password.isEnabled= isEnable
            if (isEnable){
                binding.name.requestFocus()
            }
        }

        retrieveUserData()
    }

    private fun updateUserData() {
        TODO("Not yet implemented")
    }

    private fun retrieveUserData() {
        val currentUserUid = auth.currentUser?.uid
        if (currentUserUid != null){
            val userReference = adminReference.child(currentUserUid)
            userReference.addListenerForSingleValueEvent(object  :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){
                        var ownerName = snapshot.child("name").getValue()
                        var email = snapshot.child("email").getValue()
                        var password = snapshot.child("password").getValue()
                        var address = snapshot.child("address").getValue()
                        var phone = snapshot.child("phone").getValue()
                        Log.d("TAG", "onDataChange: $ownerName ,")
                        setDatatoTextView(ownerName,email,password,address,phone)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }


    }

    private fun setDatatoTextView(
        ownerName: Any?,
        email: Any?,
        password: Any?,
        address: Any?,
        phone: Any?
    ) {
        binding.name.setText(ownerName.toString())
        binding.email.setText(email.toString())
        binding.password.setText(password.toString())
        binding.phone.setText(phone.toString())
        binding.address.setText(address.toString())
    }
}