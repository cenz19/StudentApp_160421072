package com.vincentui.studentapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vincentui.studentapp.R
import com.vincentui.studentapp.databinding.ActivityMain2Binding
import com.vincentui.studentapp.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding:ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}