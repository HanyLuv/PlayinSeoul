package com.work.hany.playinseoul.tourlist

import android.util.Log
import com.work.hany.playinseoul.di.ActivityScoped
import com.work.hany.playinseoul.model.DataHandler
import com.work.hany.playinseoul.network.AreaTour
import com.work.hany.playinseoul.network.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject

@ActivityScoped
class TourListPresenter @Inject constructor(private var dataHandler: DataHandler) : TourListContact.Presenter {

    lateinit var tourListView: TourListContact.View

    override fun loadTourList(contentTypeId: Int) {
        dataHandler.getTourList(100, contentTypeId).enqueue(object : Callback<Result<ArrayList<AreaTour>>> {

            override fun onResponse(call: Call<Result<ArrayList<AreaTour>>>, response: Response<Result<ArrayList<AreaTour>>>) {
                if (!call.isCanceled) {
                    val areaTourInformationList = response.body()!!.response.body.items.data
                    tourListView.initTourListUi(areaTourInformationList)
                }

            }

            override fun onFailure(call: Call<Result<ArrayList<AreaTour>>>?, t: Throwable?) {
                Log.d("HANY_TAG","onFailure");
            }

        })
    }

    override fun takeView(view: TourListContact.View) {
        tourListView = view
    }

    //TODO kotlin은 해당 항목 필요없는듯..
    override fun dropView() {

    }

    override fun openTourDetails(areaTour: AreaTour) {
        tourListView.showTourDetailsUi(areaTour)
    }

}