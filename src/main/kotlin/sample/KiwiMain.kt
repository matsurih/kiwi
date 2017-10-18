package sample

import authn.TwitterOAuth
import core.statuses.Statuses
import logging.Logger

val screenNamePublic = "himematsuri"
val className = "KiwiMain"

fun main2(args: Array<String>){
    val oauth = TwitterOAuth()
    oauth.executeOAuth()
    val token = oauth.token
    token?: return
    val timeline = Statuses()

    // user_timeline
    timeline.getUserTimeline(token, screenNamePublic)

    Logger.d("------------------------", className)

    // search
    timeline.searchTweets(token, "東京")

    Logger.d("------------------------", className)

}

fun main(args: Array<String>){
    val status = Statuses()
    val text = "ツイートテストだよ"
    status.updateTweet(text, null)
}