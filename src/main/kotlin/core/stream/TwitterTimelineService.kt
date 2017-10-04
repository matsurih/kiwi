package core.stream

import io.reactivex.Observable
import mold.AuthenticationMold
import mold.StatusMold
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface TwitterTimelineService {
    @GET("/1.1/statuses/user_timeline.json")
    @Headers("Accept-Encoding: gzip", "User-Agent: Kiwi")
    fun getTimeLine(@Header("Authorization") encodedKey: String, @Query("screen_name") screenName: String) : Observable<Response<List<StatusMold>>>
}