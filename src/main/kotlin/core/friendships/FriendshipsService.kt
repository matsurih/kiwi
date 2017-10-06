package core.friendships

import retrofit2.http.GET
import retrofit2.http.POST

interface FriendshipsService{
    @GET("")
    fun incoming()

    @GET("")
    fun lookup()

    @GET("")
    fun idsNoRetweets()

    @GET("")
    fun outgoing()

    @GET("")
    fun show()

    @POST("")
    fun create()

    @POST("")
    fun destroy()

    @POST("")
    fun update()
}