package sample

import core.authn.TwitterOAuth
import core.stream.TwitterTimeline

val screenNamePublic = "himematsuri"
val screenNamePrivate = "mmvnvn"

fun main(args: Array<String>){
    val oauth = TwitterOAuth()
    oauth.executeOAuth()
    val token = oauth.token
    token?: return
    val timeline = TwitterTimeline()

    // user_timeline
    timeline.getUserTimeline(token, screenNamePublic)

    println("------------------------")

    // search
    timeline.searchTweets(token, "東京")

    println("------------------------")

    
}