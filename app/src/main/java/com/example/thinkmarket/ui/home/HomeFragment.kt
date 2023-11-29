package com.example.thinkmarket.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.thinkmarket.R
import com.example.thinkmarket.databinding.FragmentHomeBinding
import com.google.android.material.navigation.NavigationView


class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProvider(this, HomeViewModelFactory())[HomeViewModel::class.java]
        if (homeViewModel.isAdmin()) {
            val menu = binding.drawerLayout.findViewById<NavigationView>(R.id.nav_host_fragment_content_main).menu
            menu.findItem(R.id.drawer_admin).isVisible = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}