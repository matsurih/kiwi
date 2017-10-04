package core

import core.authn.TwitterOAuth
import core.stream.TwitterTimeline

fun main(args: Array<String>){
    val oauth = TwitterOAuth()
    oauth.executeOAuth()
    val token = oauth.token
    token?: return

    val timeline = TwitterTimeline()
    timeline.getTimeLine(token, "himematsuri")
}

class KiwiMain{

    fun main(args: Array<String>){
        val oauth = TwitterOAuth()
        oauth.executeOAuth()
    }

}