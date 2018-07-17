package com.work.hany.playinseoul.network

import okhttp3.OkHttpClient
import okhttp3.internal.Util
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URLDecoder

/**
 *
 * 영어를 지원할것인가?
 *
 * */

class PlayInSeoulRetrofit private constructor(){

    private object Holder {
        val INSTANCE: Retrofit
            get() { return Retrofit.Builder()
                    .baseUrl(StringBuffer().append(BASE_URL).append("/").toString())
                    .client(createOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create()).build() }

        private fun createOkHttpClient(): OkHttpClient { //데이터 로그찍는것.
            val builder = OkHttpClient.Builder()
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
            builder.addInterceptor { chain ->

                var request = chain.request()
                var encodingApiKey = URLDecoder.decode(VALUE_API_KEY, Util.UTF_8.name())
                var url = request.url().newBuilder()
                        .addQueryParameter(PARAM_SERVICE_KEY, encodingApiKey)
                        .addQueryParameter(PARAM_MOBILE_APP,VALUE_MOBILE_NAME)
                        .addQueryParameter(PARAM_MOBILE_OS,VALUE_MOBILE_OS)
                        .addQueryParameter(PARAM_RESPONSE_TYPE,VALUE_RESPONSE_TYPE)
                        .build()
                request = request.newBuilder().url(url).build()
               chain.proceed(request)
            }

            return builder.build()
        }

    }

    companion object {
        private const val BASE_URL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService"
        private const val VALUE_API_KEY = "Ejx4tOEJrUzj0J460Snt4dNSCkA0H%2FINuX8Bvec4EMrJJieFwDCHJdL%2BVU%2B6HpuR2nrHrqG8ziZj%2FZ5gwGo0yg%3D%3D"
        private const val VALUE_MOBILE_NAME = "PlayInSeoul"
        private const val VALUE_MOBILE_OS = "AND"
        private const val VALUE_RESPONSE_TYPE = "json"

        private const val PARAM_SERVICE_KEY = "serviceKey"
        private const val PARAM_MOBILE_APP = "MobileApp"
        private const val PARAM_MOBILE_OS = "MobileOS"
        private const val PARAM_RESPONSE_TYPE = "_type"


        private const val CONTENT_TYPE_TOUR = 12 //관광지
        private const val CONTENT_TYPE_CULTUR = 14 //문화시설
        private const val CONTENT_TYPE_PESTIVAL = 15 //행사/공연/축제
        private const val CONTENT_TYPE_TRAVEL_COURSE = 25 //여행코스
        private const val CONTENT_TYPE_REPORTS = 28 //레포츠
        private const val CONTENT_TYPE_STAY = 32//숙박
        private const val CONTENT_TYPE_SHOPPING = 38//쇼핑
        private const val CONTENT_TYPE_FOOD = 39//음식점




        val instance: Retrofit by lazy {
            Holder.INSTANCE
        }

    }
}