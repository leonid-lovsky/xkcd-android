package com.example.xkcd_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.core.Repository
import com.example.retrofit.RepositoryImpl

class MainActivity : AppCompatActivity() {
    private val repository: Repository = RepositoryImpl()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}