package com.example.recyclerviewmvvm

import android.app.Application
import com.example.recyclerviewmvvm.model.UsersService

class App: Application() {
    val usersService = UsersService()
}