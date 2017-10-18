package util

import logging.Logger
import java.net.URLEncoder
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class Signature {
    companion object {
        private val className = this::class.java.simpleName

        private var oauthConsumerKey = Config.config?.consumerKey
        private var oauthConsumerSecret = Config.config?.consumerSecret

        private var oauthToken = "USER'S OAUTH TOKEN"
        private var oauthTokenSecret = "USER'S OAUTH TOKEN SECRET"
        

        private val oauthSignatureMethod = "HMAC-SHA1"
        private val oauthVersion = "1.0"

        fun createAuthorizationHeader(method: String, url: String, params: MutableMap<String, String?>?): String {
            params?.put("oauth_consumer_key", this.oauthConsumerKey)
            params?.put("oauth_nonce", this.createNonce())
            params?.put("oauth_signature_method", this.oauthSignatureMethod)
            params?.put("oauth_timestamp", this.epochSeconds())
            params?.put("oauth_token", this.oauthToken)
            params?.put("oauth_version", this.oauthVersion)
            val signature = sign(method, url, params)
            params?.put("oauth_signature", signature)
            return "OAuth " + createAuthorizationParams(params)
        }

        private fun sign(method: String, url: String, params: MutableMap<String, String?>?): String {
            val methodEncoded = method.toUpperCase()
            val urlEncoded = percentEncode(url)
            val paramsEncoded = percentEncode(params)
            val signingValue = "$methodEncoded&$urlEncoded&$paramsEncoded"
            Logger.d(signingValue, className)

            val signingKey = percentEncode(oauthConsumerSecret ?: "") +
                    "&" + percentEncode(oauthTokenSecret)
            Logger.d(signingKey, className)

            val sks = SecretKeySpec(signingKey.toByteArray(), "HmacSHA1")
            val mac = Mac.getInstance("HmacSHA1")
            mac.init(sks)
            val result = mac.doFinal(signingValue.toByteArray())
            return String(Base64.getEncoder().encode(result))
        }

        private fun createAuthorizationParams(params: MutableMap<String, String?>?): String {
            return "oauth_consumer_key=\"$oauthConsumerKey\", " +
                    "oauth_nonce=\"" + params?.get("oauth_nonce")+ "\", " +
                    "oauth_signature=\""+ percentEncode(params?.get("oauth_signature")) +"\", " +
                    "oauth_signature_method=\"$oauthSignatureMethod\", " +
                    "oauth_timestamp=\"" + params?.get("oauth_timestamp") + "\", " +
                    "oauth_token=\"$oauthToken\", " +
                    "oauth_version=\"$oauthVersion\""
        }

        private fun createNonce(): String {
            val random = ByteArray(32)
            Random().nextBytes(random)
           // return String(Base64.getEncoder().encode(random))
            return System.currentTimeMillis().toString()
        }

        private fun epochSeconds(): String {
            val epochSeconds: Long = System.currentTimeMillis() / 1000
            return epochSeconds.toString(10)
        }

        private fun percentEncode(enc: String?): String {
            return URLEncoder.encode(enc, "utf-8") ?: ""
        }

        private fun percentEncode(enc: Map<String, String?>?): String {
            enc ?: return ""
            val sb = StringBuilder()
            var isFirst = true
            val sorted = enc.toSortedMap()
            for (key in sorted.keys) {
                val value = enc[key]
                if (value != null) {
                    if (isFirst) {
                        isFirst = false
                    } else {
                        sb.append("%26")
                    }
                    sb.append(percentEncode(key) + "%3D" + percentEncode(value))
                }
            }
            return sb.toString()
        }

    }
}