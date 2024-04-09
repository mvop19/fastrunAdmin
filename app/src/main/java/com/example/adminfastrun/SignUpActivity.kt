package com.example.adminfastrun

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adminfastrun.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.createButton.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        binding.alreadyHaveAccountButton.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        val locationList = arrayOf("Yigo", "Dededo", "Mangilao", "Tumon")
        val adapter = ArrayAdapter(this, R.layout.simple_list_item_1, locationList)
        val autoCompleteTextView = binding.listofLocation
        autoCompleteTextView.setAdapter(adapter)

    }
}