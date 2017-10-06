package core.account

import logging.Logger
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import util.Config

class AccountHandler{
    private val className = this::class.java.simpleName
    private val baseUrl = "https://api.twitter.com"

    fun getAccountSettings(token: String){
        val config = Config.get()
        config ?: return

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create().asLenient())
                .baseUrl(baseUrl)
                .build()

        val service = retrofit.create(AccountService::class.java)
        service.getAccountSettings(token)
                .subscribe({ ret ->
                    Logger.d(ret.message(), className)
                    Logger.d(ret.code().toString(), className)
                    Logger.d(ret.headers().toString(), className)
                    Logger.d(ret.errorBody()?.string()?:"", className)
                    val user  = ret.body()
                    Logger.d("${user?.screen_name}", className)
                }, { error ->
                    error.printStackTrace()
                })
    }

    fun verifyCredentials(token: String){
        val config = Config.get()
        config ?: return

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create().asLenient())
                .baseUrl(baseUrl)
                .build()

        val service = retrofit.create(AccountService::class.java)
        service.verifyCredentials(token)
                .subscribe({ ret ->
                    Logger.d(ret.message(), className)
                    Logger.d(ret.code().toString(), className)
                    Logger.d(ret.headers().toString(), className)
                    Logger.d(ret.errorBody()?.string()?:"", className)
                    val user  = ret.body()
                    Logger.d("${user?.screen_name}", className)
                }, { error ->
                    error.printStackTrace()
                })
    }
}
