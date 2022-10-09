package com.example.recyclerviewmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewmvvm.databinding.ActivityMainBinding
import com.example.recyclerviewmvvm.model.UsersListener
import com.example.recyclerviewmvvm.model.UsersService

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UsersAdapter

    private val usersService: UsersService
        get() {
           return (applicationContext as App).usersService
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UsersAdapter()

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        usersService.addListener(usersListener)

    }

    override fun onDestroy() {
        super.onDestroy()
        usersService.deleteListener(usersListener)
    }

    private val usersListener: UsersListener = {
        adapter.users = it
    }
}