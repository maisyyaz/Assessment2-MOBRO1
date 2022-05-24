package com.yazid.cobacobaauth.repository

import androidx.lifecycle.LiveData
import com.yazid.cobacobaauth.database.dao.CarDao
import com.yazid.cobacobaauth.database.entity.Car

class CarRepository(private val dao: CarDao) {

    val getAllCar: LiveData<List<Car>> = dao.getAllCar()

    fun getOneCar(id: Long): LiveData<Car> {
        return dao.getOneCar(id)
    }

    suspend fun insertCar(car: Car) {
        dao.insertCar(car)
    }

    suspend fun updateCar(car: Car) {
        dao.updateCar(car)
    }

    suspend fun deleteCar(car: Car) {
        dao.deleteCar(car)
    }
}