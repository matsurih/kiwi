package http

import logging.Logger
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class KiwiRetrofit{
    companion object {
        private val baseUrl = "https://api.twitter.com"
        fun <T> createService(service: Class<T>): T {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .baseUrl(baseUrl)
                    .build()
            return retrofit.create(service)
        }

        fun <T> outputResult(ret: Response<T>, className: String) {
            Logger.d(ret.message(), className)
            Logger.d(ret.code().toString(), className)
            Logger.d(ret.headers().toString(), className)
            Logger.d(ret.errorBody()?.string() ?: "", className)
        }

    }
}