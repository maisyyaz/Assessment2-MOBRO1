package com.yazid.cobacobaauth.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yazid.cobacobaauth.R
import com.yazid.cobacobaauth.database.RoomDb
import com.yazid.cobacobaauth.database.entity.Car
import com.yazid.cobacobaauth.databinding.FragmentHomeBinding
import com.yazid.cobacobaauth.repository.CarRepository
import com.yazid.cobacobaauth.ui.adapter.CarAdapter
import com.yazid.cobacobaauth.viewmodel.CarViewModel
import com.yazid.cobacobaauth.viewmodel.factory.CarViewModelFactory

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var carAdapter: CarAdapter
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
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initCarAdapter()
        initButton()
    }

    private fun initButton() {
        binding.floatingAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }
    }

    private fun initCarAdapter() {
        carAdapter = CarAdapter()
        with(binding.recyclerView) {
            adapter = carAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
        }
        viewModel.getAllCar.observe(viewLifecycleOwner) {
            binding.emptyView.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
            carAdapter.submitList(it)
        }
    }
}