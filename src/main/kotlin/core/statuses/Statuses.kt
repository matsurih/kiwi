package core.statuses

import logging.Logger
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import util.Config
import util.Signature

class Statuses {
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

        val service = retrofit.create(StatusesService::class.java)
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

        val service = retrofit.create(StatusesService::class.java)
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

    fun updateTweet(status: String, inReplyToStatusId: Long?){
        val config = Config.get()
        config ?: return

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create().asLenient())
                .baseUrl(baseUrl)
                .build()

        val map = mutableMapOf(
                "status" to status,
                "in_reply_to_status_id" to inReplyToStatusId?.toString(),
                "possibly_sensitive" to null,
                "lat" to null,
                "long" to null,
                "place_id" to null,
                "display_coordinates" to null,
                "trim_user" to null,
                "media_ids" to null,
                "enable_dm_commands" to null,
                "fail_dm_commands" to null
        )

        Logger.d(map.toString(), className)

        var signature = Signature.createAuthorizationHeader("POST", "https://api.twitter.com/1.1/statuses/update.json", map)
        Logger.d(signature, className)

        val service = retrofit.create(StatusesService::class.java)
        val reqBody = RequestBody.create(MediaType.parse("text/plain"), "status=$status")
        service.update(signature, reqBody)
                .subscribe({ ret ->
                    Logger.d(ret.message(), className)
                    Logger.d(ret.code().toString(), className)
                    Logger.d(ret.headers().toString(), className)
                    Logger.d(ret.errorBody()?.string()?: "", className)
                    val status = ret.body()
                    Logger.d("[${status?.created_at}] ${status?.user?.name} (@${status?.user?.screen_name}) ${status?.text}", className)

                }, { error ->
                    error.printStackTrace()
                })
    }

}