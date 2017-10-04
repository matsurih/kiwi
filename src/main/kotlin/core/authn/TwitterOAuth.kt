package core.authn

import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import util.Config
import java.net.URLEncoder
import java.nio.charset.Charset
import java.util.*

/**
 * Created by matsurihime on 2017/10/04.
 */
class TwitterOAuth {
    var token : String? = ""
    private val BASE_URL = "https://api.twitter.com"

    fun executeOAuth() {
        val config = Config.get()
        if(config == null) return
        val key = "Basic " + createEncodedKey(config.consumerKey, config.consumerSecret)
        val value = RequestBody.create(MediaType.parse("text/plain"), "grant_type=client_credentials")

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
        val service = retrofit.create(TwitterOAuthService::class.java)
        service.authenticate(key, value)
                .subscribe({ ret ->
                    println(ret.message())
                    println(ret.code())
                    println(ret.errorBody()?.string())
                    println("TYPE: " + ret.body()?.token_type)
                    println("TOKEN: " + ret.body()?.access_token)
                    token = ret.body()?.access_token
                }, { error ->
                    error.printStackTrace()
                })
    }

    private fun createEncodedKey(key: String, secret: String): String{
        val encodedKey = key.toRFC1738()
        val encodedSecret = secret.toRFC1738()
        return (encodedKey + ":" + encodedSecret).toBase64()
    }

    private fun String.toBase64(): String{
        return Base64.getEncoder().encode(this.toByteArray()).toString(Charset.defaultCharset())
    }

    private fun String.toRFC1738(): String{
        return URLEncoder.encode(this, "UTF-8")
    }
}