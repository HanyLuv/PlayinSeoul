package com.work.hany.playinseoul.model.room

import android.os.Handler
import android.os.HandlerThread

class DataBaseWorkerThread(threadName: String): HandlerThread(threadName) {

    private lateinit var workerHander: Handler

    override fun onLooperPrepared() {
        super.onLooperPrepared()
        workerHander = Handler(looper)
    }


    fun postTask(task: Runnable){
        workerHander.post(task)
    }
}