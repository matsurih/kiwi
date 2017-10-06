package core.statuses

import io.reactivex.Observable
import mold.SearchResult
import mold.Tweet
import retrofit2.Response
import retrofit2.http.*

interface TwitterTimelineService {
    @GET("/1.1/statuses/user_timeline.json")
    fun getUserTimeline(@Header("Authorization") encodedKey: String, @Query("screen_name") screenName: String) : Observable<Response<List<Tweet>>>

    @GET("/1.1/search/tweets.json")
    fun searchTweets(@Header("Authorization") encodedKey: String, @Query("q") query: String) : Observable<Response<SearchResult>>

}