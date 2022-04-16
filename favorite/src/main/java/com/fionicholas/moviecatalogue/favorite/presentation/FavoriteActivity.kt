package com.fionicholas.moviecatalogue.favorite.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.fionicholas.moviecatalogue.core.base.BaseActivity
import com.fionicholas.moviecatalogue.favorite.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class FavoriteActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigation)

        setupToolbar(title = getString(R.string.label_favorite), isChild = true)

        initialFragment()

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun initialFragment() {
        val fragment = FavoriteMoviesFragment()
        loadFragment(fragment)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val fragment: Fragment
        when (item.itemId) {
            R.id.menu_movie -> {
                fragment = FavoriteMoviesFragment()
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_tv_show -> {
                fragment = FavoriteTvShowsFragment()
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.favoriteFrame, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.popBackStack()
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                supportFragmentManager.popBackStack()
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}