package com.yazid.cobacobaauth.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yazid.cobacobaauth.database.RoomDb
import com.yazid.cobacobaauth.database.dao.UserDao
import com.yazid.cobacobaauth.database.entity.User
import com.yazid.cobacobaauth.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    var getAllUser: LiveData<List<User>> = repository.getAllUser

    fun getOneUser(id: Long): LiveData<User> {
        return repository.getOneUser(id)
    }

    fun getUserByUsername(username: String): LiveData<User> {
        return repository.getUserByUsername(username)
    }

    fun insertUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertUser(user)
    }

    fun updateUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateUser(user)
    }
}