package com.example.thinkmarket


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.thinkmarket.ui.login.LoginFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(LoginFragment())
    }

    private fun replaceFragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.layout_container, fragment).commit()
    }
}