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

class TwitterOAuth {
    var token : String? = ""
    private val baseUrl = "https://api.twitter.com"

    /**
     * Execute OAuth Request
     */
    fun executeOAuth() {
        val config = Config.get()
        config ?: return
        val key = "Basic " + createEncodedKey(config.consumerKey, config.consumerSecret)
        val value = RequestBody.create(MediaType.parse("text/plain"), "grant_type=client_credentials")

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(baseUrl)
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

    /**
     * Create Encoded Key
     * @param key consumer_key
     * @param secret consumer_secret
     */
    private fun createEncodedKey(key: String, secret: String): String{
        val encodedKey = key.toRFC1738()
        val encodedSecret = secret.toRFC1738()
        return (encodedKey + ":" + encodedSecret).toBase64()
    }

    /**
     * Encode to Base64
     */
    private fun String.toBase64(): String{
        return Base64.getEncoder().encode(this.toByteArray()).toString(Charset.defaultCharset())
    }

    /**
     * Encode to RFC1738 format
     */
    private fun String.toRFC1738(): String{
        return URLEncoder.encode(this, "UTF-8")
    }
}