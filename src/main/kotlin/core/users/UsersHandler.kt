package core.users

import http.KiwiRetrofit
import logging.Logger
import util.Config
import util.HttpMethods
import util.Signature

class UsersHandler{
    private val className = this::class.java.simpleName
    fun showUser(userId: Long){
        val config = Config.get()
        config ?: return

        val params: MutableMap<String, String?>? = mutableMapOf(
                "user_id" to userId.toString(),
                "include_entities" to "false"
        )

        val signature = Signature.createAuthorizationHeader(HttpMethods.GET, "https://api.twitter.com//1.1/users/show.json", params)

        val service = KiwiRetrofit.createService(UsersService::class.java)
        service.show(signature, userId)
                .subscribe({ ret ->
                    KiwiRetrofit.outputResult(ret, className)
                    val user = ret.body()
                     Logger.d("[${user?.created_at}] ${user?.name} (@${user?.screen_name}) ${user?.statuses_count}", className)
                }, { error ->
                    error.printStackTrace()
                })
    }

    fun showUser(screenName: String){
        val config = Config.get()
        config ?: return

        val params: MutableMap<String, String?>? = mutableMapOf(
                "screen_name" to screenName,
                "include_entities" to "false"
                )

        val signature = Signature.createAuthorizationHeader(HttpMethods.GET, "https://api.twitter.com/1.1/users/show.json", params)

        val service = KiwiRetrofit.createService(UsersService::class.java)
        service.show(signature, screenName)
                .subscribe({ ret ->
                    KiwiRetrofit.outputResult(ret, className)
                    val user = ret.body()
                    Logger.d("[${user?.created_at}] ${user?.name} (@${user?.screen_name}) ${user?.statuses_count}", className)
                }, { error ->
                    error.printStackTrace()
                })
    }

}