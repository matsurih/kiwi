package core.users

import io.reactivex.Observable
import mold.User
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by matsurihime on 2017/10/19.
 */
const val ROUTE = "/1.1/users"

interface UsersService{

    @GET("$ROUTE/lookup.json")
    fun lookup()

    @GET("$ROUTE/search.json")
    fun search()

    /**
     * Show user who's UserID is [userId], optional [includeEntities]
     * @return Observable of Response of Users
     */
    @GET("$ROUTE/show.json")
    @Headers("Content-Type: application/x-www-form-urlencoded;charset=UTF-8", "User-Agent: Kiwi")
    fun show(@Header("Authorization") signature: String, @Query("user_id") userId : Long, @Query("include_entities") includeEntities: Boolean = false) : Observable<Response<User>>

    /**
     * Show user who's UserID is [screenName], optional [includeEntities]
     * @return Observable of Response of Users
     */
    @GET("$ROUTE/show.json")
    @Headers("Content-Type: application/x-www-form-urlencoded;charset=UTF-8", "User-Agent: Kiwi")
    fun show(@Header("Authorization") signature: String, @Query("screen_name") screenName : String, @Query("include_entities") includeEntities: Boolean = false) : Observable<Response<User>>

    @GET("$ROUTE/suggestions.json")
    fun suggestions()

    @GET("$ROUTE/suggestions/:slug")
    fun suggestions(@Url slug: Int)

    @GET("$ROUTE/suggestions/:slug/members.json")
    fun suggestionMembers(slug: Int)
}