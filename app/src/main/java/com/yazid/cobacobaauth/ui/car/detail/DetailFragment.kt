package com.yazid.cobacobaauth.ui.car.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.yazid.cobacobaauth.R
import com.yazid.cobacobaauth.database.RoomDb
import com.yazid.cobacobaauth.databinding.FragmentDetailBinding
import com.yazid.cobacobaauth.repository.CarRepository
import com.yazid.cobacobaauth.ui.home.HomeFragmentArgs
import com.yazid.cobacobaauth.viewmodel.CarViewModel
import com.yazid.cobacobaauth.viewmodel.factory.CarViewModelFactory

class DetailFragment : Fragment() {

    private var carId: Long = -1
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: CarViewModel by lazy {
        val db = RoomDb.getInstance(requireContext())
        val repo = CarRepository(db.carDao)
        val factory = CarViewModelFactory(repo)
        ViewModelProvider(this, factory)[CarViewModel::class.java]
    }
    private val args: HomeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        carId = args.carId
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.getOneCar(carId).observe(requireActivity()) {
            if (it == null) return@observe
            val carTypeArray: Array<String> = resources.getStringArray(R.array.car_type)

            binding.edtEditCarName.setText(it.name)
            binding.edtEditCarPrice.setText(it.price.toString())
            binding.spinnerEditCarType.setSelection(carTypeArray.indexOf(carTypeArray.first { elem -> elem == it.type }))
            Log.d("tes", "id: " + it.id + ", nama mobil: " + it.name)
        }
    }
}