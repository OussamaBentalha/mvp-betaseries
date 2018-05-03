package com.android.sam.kotlin_mvp_project.mvp

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

/**
 * Created by oussamabentalha on 02/05/2018.
 */
abstract class BaseMvpFragment<in V : BaseMvpView, T : BaseMvpPresenter<V>> : Fragment(), BaseMvpView {

    override fun getContext(): Context = activity

    protected abstract val mPresenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.attachView(this as V)
    }

    override fun showError(error: String?) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

}