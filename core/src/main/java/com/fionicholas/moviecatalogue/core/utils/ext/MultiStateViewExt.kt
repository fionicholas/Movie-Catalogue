package com.fionicholas.moviecatalogue.core.utils.ext

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.fionicholas.moviecatalogue.core.R
import com.kennyc.view.MultiStateView

fun MultiStateView.showLoadingState() {
    this.viewState = MultiStateView.VIEW_STATE_LOADING
}

fun MultiStateView.showDefaultState() {
    this.viewState = MultiStateView.VIEW_STATE_CONTENT
}


fun MultiStateView.showErrorState(errorMessage: String? = null, title: String? = null, drawable: Drawable? = null, errorButton: Pair<String, (() -> Unit)>? = null) {
    this.viewState = MultiStateView.VIEW_STATE_ERROR

    errorMessage?.let {
        val tvError = this.getView(MultiStateView.VIEW_STATE_ERROR)?.findViewById<TextView>(R.id.tv_error)
        tvError?.text = errorMessage
    }

    title?.let {
        val tvTitle = this.getView(MultiStateView.VIEW_STATE_ERROR)?.findViewById<TextView>(R.id.tv_title)
        tvTitle?.text = title
    }

    drawable?.let {
        val imgError = this.getView(MultiStateView.VIEW_STATE_ERROR)?.findViewById<ImageView>(R.id.img_error)
        imgError?.setImageDrawable(drawable)
    }

    errorButton?.let { pair ->
        val btnError = this.getView(MultiStateView.VIEW_STATE_ERROR)?.findViewById<Button>(R.id.btn_error)
        btnError?.text = pair.first
        btnError?.visibility = View.VISIBLE

        btnError?.setOnClickListener { pair.second.invoke() }
    }
}

fun MultiStateView.showEmptyState(emptyMessage: String? = null, drawable: Drawable? = null, title: String? = null) {
    this.viewState = MultiStateView.VIEW_STATE_EMPTY

    emptyMessage?.let {
        val tvError = this.getView(MultiStateView.VIEW_STATE_EMPTY)?.findViewById<TextView>(R.id.tv_error)
        tvError?.text = emptyMessage
    }

    title?.let {
        val tvTitle = this.getView(MultiStateView.VIEW_STATE_EMPTY)?.findViewById<TextView>(R.id.tv_title)
        tvTitle?.text = title
    }

    drawable?.let {
        val imgError = this.getView(MultiStateView.VIEW_STATE_EMPTY)?.findViewById<ImageView>(R.id.img_error)
        imgError?.setImageDrawable(drawable)
    }
}