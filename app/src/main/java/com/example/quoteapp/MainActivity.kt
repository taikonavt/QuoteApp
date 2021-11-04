package com.example.quoteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coreapi.network.MoexApi
import com.example.quoteapp.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}