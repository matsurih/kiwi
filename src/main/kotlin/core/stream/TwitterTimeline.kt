package core.stream

import com.squareup.moshi.Moshi
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import util.Config

class TwitterTimeline {
    private val baseUrl = "https://api.twitter.com"

    fun getTimeLine(token: String, screenName: String) {
        val config = Config.get()
        config ?: return
        val bearer = "Bearer " + token

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create().asLenient())
                .baseUrl(baseUrl)
                .build()

        val service = retrofit.create(TwitterTimelineService::class.java)
        service.getTimeLine(bearer, screenName)
                .subscribe({ ret ->
                    println(ret.message())
                    println(ret.code())
                    println(ret.headers())
                    println(ret.errorBody()?.string())
                    ret.body()?.forEach {status ->
                        println("[" + status.created_at + "] @" + status.user.name +" (" + status.user.screen_name + ") " + status.text)
                    }
                }, { error ->
                    error.printStackTrace()
                })
    }
}