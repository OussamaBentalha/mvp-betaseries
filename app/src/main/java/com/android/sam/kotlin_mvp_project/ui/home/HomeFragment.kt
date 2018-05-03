package com.android.sam.kotlin_mvp_project.ui.home

import android.animation.Animator
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.sam.kotlin_mvp_project.R
import com.android.sam.kotlin_mvp_project.mvp.BaseMvpFragment
import com.android.sam.kotlin_mvp_project.ui.Navigator
import com.android.sam.kotlin_mvp_project.ui.models.EpisodeViewModel
import com.android.sam.kotlin_mvp_project.ui.uiKodeinModule
import com.github.salomonbrys.kodein.*
import com.github.salomonbrys.kodein.android.FragmentInjector
import com.github.salomonbrys.kodein.android.appKodein
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * Created by oussamabentalha on 02/05/2018.
 */
class HomeFragment : BaseMvpFragment<HomeContract.View, HomePresenter>(), HomeContract.View, FragmentInjector, EpisodeHolder.Listener {

    override val injector: KodeinInjector = KodeinInjector()

    override val mPresenter: HomePresenter by instance()

    val defaultLayout: Int = R.layout.fragment_home

    lateinit var adapter : EpisodesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        initializeInjector()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(defaultLayout, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login.setOnClickListener { mPresenter.login() }
    }

    override fun provideOverridingModule() = Kodein.Module {
        bind<HomeContract.View>() with instance(this@HomeFragment)
    }

    override fun setData(episodes: List<EpisodeViewModel>) {
        login.visibility = View.GONE
        list.visibility = View.VISIBLE

        adapter = EpisodesAdapter(episodes.toMutableList(), this)
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = adapter
    }

    override fun updateItemAtPosition(item: EpisodeViewModel, position: Int) {
        adapter.updateItemForPosition(position, item)
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onClickItems(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClickFavorite(position: Int) {
        mPresenter.favoriteClicked(position)
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyInjector()
    }

}
