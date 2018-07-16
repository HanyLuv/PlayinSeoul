package com.work.hany.playinseoul.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/**
 *
 * 영어를 지원할것인가?
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
                var url = request.url().newBuilder()
                        .addQueryParameter(PARAM_SERVICE_KEY, VALUE_API_KEY)
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
        private const val BASE_URL = "http://api.visitkorea.or.kr"
//http://unikys.tistory.com/195 인코딩, 디코딩 
        private const val VALUE_API_KEY = "Ejx4tOEJrUzj0J460Snt4dNSCkA0H%2FINuX8Bvec4EMrJJieFwDCHJdL%2BVU%2B6HpuR2nrHrqG8ziZj%2FZ5gwGo0yg%3D%3D"
        private const val VALUE_MOBILE_NAME = "PlayInSeoul"
        private const val VALUE_MOBILE_OS = "AND"
        private const val VALUE_RESPONSE_TYPE = "json"

        private const val PARAM_SERVICE_KEY = "serviceKey"
        private const val PARAM_MOBILE_APP = "MobileApp"
        private const val PARAM_MOBILE_OS = "MobileOS"
        private const val PARAM_RESPONSE_TYPE = "_type"

        val instance: Retrofit by lazy {
            Holder.INSTANCE
        }

    }
}