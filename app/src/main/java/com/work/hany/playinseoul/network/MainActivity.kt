package com.work.hany.playinseoul.network

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.work.hany.playinseoul.R
import com.work.hany.playinseoul.network.network.PlayInSeoulRetrofit
import com.work.hany.playinseoul.network.network.PlayInSeoulService
import com.work.hany.playinseoul.network.network.Result
import retrofit2.Call
import retrofit2.Callback

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var service = PlayInSeoulRetrofit.instance.create(PlayInSeoulService::class.java)
        service.getAreaBasedList().enqueue(object : Callback<Result>{

            override fun onResponse(call: Call<Result>, response: retrofit2.Response<Result>?) {
                Log.d("HANY_TAG", "onResponse call.isCanceled ? " + call.isCanceled)
            }

            override fun onFailure(call: Call<Result>?, t: Throwable?) {
                Log.d("HANY_TAG", "onFailure ");
            }
        })
    }

}