package sample

import core.authn.TwitterOAuth
import core.stream.TwitterTimeline
import logging.Logger

val screenNamePublic = "himematsuri"
val screenNamePrivate = "mmvnvn"
val className = "KiwiMain"

fun main(args: Array<String>){
    val oauth = TwitterOAuth()
    oauth.executeOAuth()
    val token = oauth.token
    token?: return
    val timeline = TwitterTimeline()

    // user_timeline
    timeline.getUserTimeline(token, screenNamePublic)

    Logger.d("------------------------", className)

    // search
    timeline.searchTweets(token, "東京")

    Logger.d("------------------------", className)

}