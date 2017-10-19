package authn

import http.KiwiRetrofit
import logging.Logger
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import util.Config
import util.ContentTypes
import java.net.URLEncoder
import java.nio.charset.Charset
import java.util.*

class TwitterOAuth {
    private val className = this::class.java.simpleName
    var token : String? = ""

    /**
     * Execute OAuth Request
     */
    fun executeOAuth() {
        val config = Config.get()
        config ?: return
        val key = "Basic " + createEncodedKey(config.consumerKey, config.consumerSecret)
        val value = RequestBody.create(MediaType.parse(ContentTypes.TEXT_PLAIN), "grant_type=client_credentials")
        val service = KiwiRetrofit.createService(TwitterOAuthService::class.java)
        service.authenticate(key, value)
                .subscribe({ ret ->
                    KiwiRetrofit.outputResult(ret, className)
                    Logger.d("TYPE: ${ret.body()?.token_type}", className)
                    Logger.d("TOKEN: ${ret.body()?.access_token}", className)
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