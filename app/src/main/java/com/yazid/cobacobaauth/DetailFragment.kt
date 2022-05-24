package com.yazid.cobacobaauth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = arguments
        if (bundle != null) {
            val args = HomeFragmentArgs.fromBundle(bundle)
            carId = args.carId
        }
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
}