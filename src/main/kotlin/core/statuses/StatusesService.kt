package core.statuses

import io.reactivex.Observable
import mold.SearchResult
import mold.Tweet
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

const val ROOT = "/1.1/statuses"

interface StatusesService {
    @GET("${ROOT}/user_timeline.json")
    fun getUserTimeline(@Header("Authorization") encodedKey: String, @Query("screen_name") screenName: String) : Observable<Response<List<Tweet>>>

    @GET("$ROOT/tweets.json")
    fun searchTweets(@Header("Authorization") encodedKey: String, @Query("q") query: String) : Observable<Response<SearchResult>>

    @POST("$ROOT/update.json")
    @Headers("Content-Type: application/x-www-form-urlencoded;charset=UTF-8", "User-Agent: Kiwi")
    fun update(@Header("Authorization") authorization: String, @Body() body: RequestBody): Observable<Response<Tweet>>

    @Deprecated("This API was deprecated by Twitter.")
    @POST("$ROOT/")
    fun updateWithMedia()

    @POST()
    fun destroy()

    @POST()
    fun retweet()

    @POST()
    fun unretweet()

}