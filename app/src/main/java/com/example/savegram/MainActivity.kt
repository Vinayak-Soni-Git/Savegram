package com.example.savegram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.savegram.Fragments.BrowserFragment
import com.example.savegram.Fragments.DownloadFragment
import com.example.savegram.Fragments.ExploreFragment
import com.example.savegram.Fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.mainDrawerLayout)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val toolbar:Toolbar = findViewById(R.id.navigationDrawerToolbar)
//        setSupportActionBar(toolbar)
        val actionBarDrawerToggle:ActionBarDrawerToggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.openNavigationDrawer, R.string.closeNavigationDrawer
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        
        bottomNavigationView.setOnNavigationItemSelectedListener { 
            when(it.itemId){
                R.id.menu_home -> {
                    loadFragment(HomeFragment(), false)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_explore -> {
                    loadFragment(ExploreFragment(), false)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_browser -> {
                    loadFragment(BrowserFragment(), false)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_download -> {
                    loadFragment(DownloadFragment(), false)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    loadFragment(HomeFragment(), false)
                    return@setOnNavigationItemSelectedListener true
                }
            }
        }
        bottomNavigationView.selectedItemId = R.id.menu_home
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()

        }
    }
    
    private fun loadFragment(fragment:Fragment, flag:Boolean){
        val fragmentManager:FragmentManager = supportFragmentManager
        val fragmentTransition:FragmentTransaction = fragmentManager.beginTransaction()
        if (flag){
            fragmentTransition.add(R.id.fragmentContainer, fragment)
        } else{
            fragmentTransition.replace(R.id.fragmentContainer, fragment)
        }
        fragmentTransition.commit()
    }
}