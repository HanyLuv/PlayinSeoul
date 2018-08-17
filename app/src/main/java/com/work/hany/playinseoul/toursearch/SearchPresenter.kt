package com.work.hany.playinseoul.toursearch

import android.util.Log
import com.work.hany.playinseoul.model.DataHandler
import com.work.hany.playinseoul.network.Area
import com.work.hany.playinseoul.network.Result
import com.work.hany.playinseoul.toursearch.vo.SearchItem.SearchItemType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
import javax.inject.Inject

class SearchPresenter @Inject constructor(private var dataHandler: DataHandler): SearchContact.Presenter {
    private lateinit var searchView: SearchContact.View

    override fun takeView(view: SearchContact.View) {
        this.searchView = view
    }

    override fun dropView() {

    }

    override fun loadAreaCode(areaCode: String,type: SearchItemType) {

        if (type == SearchItemType.AREA) {
            dataHandler.getAreaCode(areaCode).enqueue(object : Callback<Result<ArrayList<Area>>> {

                override fun onResponse(call: Call<Result<ArrayList<Area>>>, response: Response<Result<ArrayList<Area>>>) {
                    if (!call.isCanceled) {
                        val areaCodeList = response.body()!!.response.body.items.data
                        searchView.initAreaCodeListUi(SearchItemType.AREA, areaCodeList)
                    }

                }

                override fun onFailure(call: Call<Result<ArrayList<Area>>>?, t: Throwable?) {
                    Log.d("HANY_TAG", "onFailure")
                }

            })

        } else if (type == SearchItemType.TOUR) {

            dataHandler.getCategoryCode(areaCode).enqueue(object : Callback<Result<ArrayList<Area>>> {

                override fun onResponse(call: Call<Result<ArrayList<Area>>>, response: Response<Result<ArrayList<Area>>>) {
                    if (!call.isCanceled) {
                        val categoryCodeList = response.body()!!.response.body.items.data
                        searchView.initAreaCodeListUi(SearchItemType.TOUR, categoryCodeList)
                    }

                }

                override fun onFailure(call: Call<Result<ArrayList<Area>>>?, t: Throwable?) {
                    Log.d("HANY_TAG","onFailure")
                }

            })
        }

    }
    override fun loadAreaCode() {
       dataHandler.areaCode.enqueue(object : Callback<Result<ArrayList<Area>>> {

           override fun onResponse(call: Call<Result<ArrayList<Area>>>, response: Response<Result<ArrayList<Area>>>) {
               if (!call.isCanceled) {
                   val areaCodeList = response.body()!!.response.body.items.data
                   searchView.initAreaCodeListUi(SearchItemType.AREA, areaCodeList)
               }

           }

           override fun onFailure(call: Call<Result<ArrayList<Area>>>?, t: Throwable?) {
               Log.d("HANY_TAG","onFailure");
           }

       })


        dataHandler.categoryCode.enqueue(object : Callback<Result<ArrayList<Area>>> {

            override fun onResponse(call: Call<Result<ArrayList<Area>>>, response: Response<Result<ArrayList<Area>>>) {
                if (!call.isCanceled) {
                    val categoryCodeList = response.body()!!.response.body.items.data
                    searchView.initAreaCodeListUi(SearchItemType.TOUR, categoryCodeList)
                }

            }

            override fun onFailure(call: Call<Result<ArrayList<Area>>>?, t: Throwable?) {
                Log.d("HANY_TAG","onFailure");
            }

        })

    }


}