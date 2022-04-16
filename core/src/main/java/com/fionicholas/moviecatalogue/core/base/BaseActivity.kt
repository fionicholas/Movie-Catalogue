package com.fionicholas.moviecatalogue.core.base

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    fun setupToolbar(title : String, isChild: Boolean) {

        if (supportActionBar != null) {
            supportActionBar?.title = title
            supportActionBar?.setDisplayHomeAsUpEnabled(isChild)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}