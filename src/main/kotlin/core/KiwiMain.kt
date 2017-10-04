package core

import core.authn.TwitterOAuth

fun main(args: Array<String>){
    val oauth = TwitterOAuth()
    oauth.executeOAuth()
    val token = oauth.token

}

class KiwiMain{

    fun main(args: Array<String>){
        val oauth = TwitterOAuth()
        oauth.executeOAuth()
    }

}