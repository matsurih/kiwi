package sample

import authn.TwitterOAuth
import core.statuses.StatusesHandler
import core.users.UsersHandler
import logging.Logger

val screenNamePublic = "himematsuri"
val className = "KiwiMain"

fun main(args: Array<String>){
   getUser()
}

fun postTweet(){
    val status = StatusesHandler()
    val text = "ツイートテストだよ"
    status.updateTweet(text, null)
}

fun getUser(){
    val handler = UsersHandler()
    handler.showUser(screenNamePublic)
}

fun search(){
    val oauth = TwitterOAuth()
    oauth.executeOAuth()
    val token = oauth.token
    token?: return
    val timeline = StatusesHandler()

    // user_timeline
    timeline.getUserTimeline(token, screenNamePublic)

    Logger.d("------------------------", className)

    // search
    timeline.searchTweets(token, "東京")

    Logger.d("------------------------", className)

}