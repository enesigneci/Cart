package com.enesigneci.basket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.enesigneci.basket.ui.listing.ListingFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ListingFragment.newInstance())
                    .commitNow()
        }
    }
}