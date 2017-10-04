package util

import com.squareup.moshi.Moshi
import java.io.File
import java.nio.charset.Charset

data class Config(
        val consumerKey: String,
        val consumerSecret: String
){
    companion object {
        var config: Config? = parseConfig("twitter_key.json")

        fun get(): Config?{
            return config
        }
        private fun parseConfig(fileName: String): Config?{
            val path = ClassLoader.getSystemResource(fileName).toURI()
            val source = File(path).readText(Charset.forName("UTF-8"))
            val adapter = Moshi.Builder().build().adapter(Config::class.java)
            return adapter.fromJson(source)
        }
    }
}
