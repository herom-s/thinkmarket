package com.example.thinkmarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.fragment.findNavController
import com.example.thinkmarket.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.apply {
            toggle= ActionBarDrawerToggle(activity, drawerLayout,R.string.open,R.string.close)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()
            activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
            navView.setNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.nav_ordem->{
                        findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
                    }
                    R.id.nav_empresa->{
                        findNavController().navigate(R.id.action_homeFragment_to_actEmpresaPerfil)}
                    R.id.nav_servico->{
                        findNavController().navigate(R.id.action_homeFragment_to_actServicoInfo)}
                    R.id.nav_login->{
                        findNavController().navigate(R.id.action_homeFragment_to_loginFragment)}
                    R.id.nav_conta->{
                        findNavController().navigate(R.id.action_homeFragment_to_actDadosUsuario)}
                    R.id.nav_logout->{}
                    R.id.nav_admuser->{
                        findNavController().navigate(R.id.action_homeFragment_to_actUsuarioInfo)}
                    R.id.nav_admempresa->{
                        findNavController().navigate(R.id.action_homeFragment_to_actEmpresaInfo)}
                    R.id.nav_admservico->{
                        findNavController().navigate(R.id.action_homeFragment_to_actServicoInfo)}
                    R.id.nav_cadempresa->{
                        findNavController().navigate(R.id.action_homeFragment_to_cadastroEmpresaFragment)}
                }
                true
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)
    }
}