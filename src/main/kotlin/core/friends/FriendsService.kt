package core.friends

import retrofit2.http.GET

interface FriendsService{
    @GET("")
    fun ids()

    @GET("")
    fun list()
}