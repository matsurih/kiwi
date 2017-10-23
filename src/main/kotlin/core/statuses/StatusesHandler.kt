package core.statuses

import http.KiwiRetrofit
import logging.Logger
import okhttp3.MediaType
import okhttp3.RequestBody
import util.Config
import util.ContentTypes
import util.HttpMethods
import util.Signature

class StatusesHandler {
    private val className = this::class.java.simpleName


    /**
     * Get [screenName]'s timeline by using [token].
     */
    fun getUserTimeline(token: String, screenName: String) {
        val config = Config.get()
        config ?: return
        val bearer = "Bearer " + token

        val service = KiwiRetrofit.createService(StatusesService::class.java)
        service.getUserTimeline(bearer, screenName)
                .subscribe({ ret ->
                    KiwiRetrofit.outputResult(ret, className)
                    ret.body()?.forEach { status ->
                        Logger.d("[${status.created_at}] ${status.user.name} (@${status.user.screen_name}) ${status.text}", className)
                    }
                }, { error ->
                    error.printStackTrace()
                })
    }

    /**
     * Search tweets with [query] by using [token].
     */
    fun searchTweets(token: String, query: String) {
        val config = Config.get()
        config ?: return
        val bearer = "Bearer " + token

        val service = KiwiRetrofit.createService(StatusesService::class.java)
        service.searchTweets(bearer, query)
                .subscribe({ ret ->
                    KiwiRetrofit.outputResult(ret, className)
                    ret.body()?.statuses?.forEach { status ->
                        Logger.d("[${status.created_at}] ${status.user.name} (@${status.user.screen_name}) ${status.text}", className)
                    }
                }, { error ->
                    error.printStackTrace()
                })
    }

    /**
     * Post tweet. [status] is tweet text and [inReplyToStatusId] is necessary when replying.
     */
    fun updateTweet(status: String, inReplyToStatusId: Long?) {
        val config = Config.get()
        config ?: return

        val params = mutableMapOf(
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

        val signature = Signature.createAuthorizationHeader(HttpMethods.POST, "https://api.twitter.com/1.1/statuses/update.json", params)

        val service = KiwiRetrofit.createService(StatusesService::class.java)

        val reqBody = RequestBody.create(MediaType.parse(ContentTypes.TEXT_PLAIN), "status=$status")
        service.update(signature, reqBody)
                .subscribe({ ret ->
                    KiwiRetrofit.outputResult(ret, className)
                    val resStatus = ret.body()
                    Logger.d("[${resStatus?.created_at}] ${resStatus?.user?.name} (@${resStatus?.user?.screen_name}) ${resStatus?.text}", className)

                }, { error ->
                    error.printStackTrace()
                })
    }

}