package core.account

import http.KiwiRetrofit
import logging.Logger
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import util.Config

class AccountHandler{
    private val className = this::class.java.simpleName

    fun getAccountSettings(token: String){
        val config = Config.get()
        config ?: return

        val service = KiwiRetrofit.createService(AccountService::class.java)
        service.getAccountSettings(token)
                .subscribe({ ret ->
                    KiwiRetrofit.outputResult(ret, className)
                    val user  = ret.body()
                    Logger.d("${user?.screen_name}", className)
                }, { error ->
                    error.printStackTrace()
                })
    }

    fun verifyCredentials(token: String){
        val config = Config.get()
        config ?: return

        val service = KiwiRetrofit.createService(AccountService::class.java)
        service.verifyCredentials(token)
                .subscribe({ ret ->
                    KiwiRetrofit.outputResult(ret, className)
                    val user  = ret.body()
                    Logger.d("${user?.screen_name}", className)
                }, { error ->
                    error.printStackTrace()
                })
    }
}
