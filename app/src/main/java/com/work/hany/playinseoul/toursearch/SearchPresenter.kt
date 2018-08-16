package com.work.hany.playinseoul.toursearch

import com.work.hany.playinseoul.model.DataHandler
import javax.inject.Inject

class SearchPresenter @Inject constructor(private var dataHandler: DataHandler): SearchContact.Presenter {
    lateinit var searchView: SearchContact.View

    override fun takeView(view: SearchContact.View) {
        this.searchView = view
    }

    override fun dropView() {

    }

    override fun loadAreaCode() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}