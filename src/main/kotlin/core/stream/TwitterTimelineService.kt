package core.stream

import io.reactivex.Observable
import mold.Tweet
import retrofit2.Response
import retrofit2.http.*

interface TwitterTimelineService {
    @GET("/1.1/statuses/user_timeline.json")
    fun getTimeLine(@Header("Authorization") encodedKey: String, @Query("screen_name") screenName: String) : Observable<Response<List<Tweet>>>
}