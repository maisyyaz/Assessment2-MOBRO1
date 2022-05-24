package com.yazid.cobacobaauth.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.yazid.cobacobaauth.database.RoomDb
import com.yazid.cobacobaauth.database.entity.Car
import com.yazid.cobacobaauth.repository.CarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarViewModel(private val repository: CarRepository) : ViewModel() {

    var getAllCar: LiveData<List<Car>> = repository.getAllCar

    fun getOneCar(id: Long): LiveData<Car> {
        return repository.getOneCar(id)
    }

    fun insertCar(car: Car) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertCar(car)
    }

    fun updateCar(car: Car) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateCar(car)
    }

    fun deleteCar(car: Car) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteCar(car)
    }
}