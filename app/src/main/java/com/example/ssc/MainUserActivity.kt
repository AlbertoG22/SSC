package com.example.ssc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.ssc.fragments.HomeFragment
import com.example.ssc.fragments.OfferFragment
import com.example.ssc.fragments.PacksFragment
import com.example.ssc.fragments.PlaceFragment
import kotlinx.android.synthetic.main.activity_main_user.*

class MainUserActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment
    private val offerFragment = OfferFragment
    private val packsFragment = PacksFragment
    private val placeFragment = PlaceFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_user)
        replaceFragment(homeFragment)

        bottom_navigation.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.ic_home -> replaceFragment(homeFragment)
                R.id.ic_packs -> replaceFragment(packsFragment)
                R.id.ic_offers -> replaceFragment(offerFragment)
                R.id.ic_places -> replaceFragment(placeFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        if(fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}