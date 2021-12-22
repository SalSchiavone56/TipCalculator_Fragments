package com.schiavone.honorsmobileapps.tipcalculator_fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.schiavone.honorsmobileapps.tipcalculator_fragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding :ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}