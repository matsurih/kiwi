package core.statuses

import logging.Logger
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import util.Config

class TwitterTimeline {
    private val className = this::class.java.simpleName
    private val baseUrl = "https://api.twitter.com"

    fun getUserTimeline(token: String, screenName: String) {
        val config = Config.get()
        config ?: return
        val bearer = "Bearer " + token

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create().asLenient())
                .baseUrl(baseUrl)
                .build()

        val service = retrofit.create(TwitterTimelineService::class.java)
        service.getUserTimeline(bearer, screenName)
                .subscribe({ ret ->
                    Logger.d(ret.message(), className)
                    Logger.d(ret.code().toString(), className)
                    Logger.d(ret.headers().toString(), className)
                    Logger.d(ret.errorBody()?.string()?:"", className)
                    ret.body()?.forEach {status ->
                        Logger.d("[${status.created_at}] ${status.user.name} (@${status.user.screen_name}) ${status.text}", className)
                    }
                }, { error ->
                    error.printStackTrace()
                })
    }

    fun searchTweets(token: String, query: String){
        val config = Config.get()
        config ?: return
        val bearer = "Bearer " + token

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create().asLenient())
                .baseUrl(baseUrl)
                .build()

        val service = retrofit.create(TwitterTimelineService::class.java)
        service.searchTweets(bearer, query)
                .subscribe({ ret ->
                    Logger.d(ret.message(), className)
                    Logger.d(ret.code().toString(), className)
                    Logger.d(ret.headers().toString(), className)
                    Logger.d(ret.errorBody()?.string()?: "", className)
                    ret.body()?.statuses?.forEach {status ->
                        Logger.d("[${status.created_at}] ${status.user.name} (@${status.user.screen_name}) ${status.text}", className)
                    }
                }, { error ->
                    error.printStackTrace()
                })
    }
}