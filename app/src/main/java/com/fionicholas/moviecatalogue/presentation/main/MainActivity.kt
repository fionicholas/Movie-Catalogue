package com.fionicholas.moviecatalogue.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.fionicholas.moviecatalogue.R
import com.fionicholas.moviecatalogue.R.layout
import com.fionicholas.moviecatalogue.core.utils.AppConstants
import com.fionicholas.moviecatalogue.presentation.movie.MovieFragment
import com.fionicholas.moviecatalogue.presentation.movie.TvShowFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigation)

        initialFragment()
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun initialFragment() {
        val fragment = MovieFragment()
        loadFragment(fragment)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val fragment: Fragment
        when (item.itemId) {
            R.id.menu_movie -> {
                fragment = MovieFragment()
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_tv_show -> {
                fragment = TvShowFragment()
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainFrame, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> {
                openSearchActivity(this)
                return true
            }
            R.id.favorite -> {
                openFavoriteActivity(this)
                return true
            }
            android.R.id.home -> {
                supportFragmentManager.popBackStack()
                onBackPressed()
            }
        }
        return false
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.popBackStack()
        finish()
    }

    private fun openFavoriteActivity(context: Context) {
        try {
            context.startActivity(
                Intent(
                    context,
                    Class.forName("com.fionicholas.moviecatalogue.favorite.presentation.FavoriteActivity")
                )
            )
        } catch (e: Exception) {
            Log.e(AppConstants.MOVIE_TAG, "Activity Not Found")
        }
    }

    private fun openSearchActivity(context: Context) {
        try {
            context.startActivity(
                Intent(
                    context,
                    Class.forName("com.fionicholas.moviecatalogue.search.presentation.SearchActivity")
                )
            )
        } catch (e: Exception) {
            Log.e(AppConstants.MOVIE_TAG, "Activity Not Found")
        }
    }

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            context.startActivity(intent)
        }
    }
}