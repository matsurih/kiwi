package core.followers

import retrofit2.http.GET

interface FollowersService{
    @GET("")
    fun ids()

    @GET("")
    fun list()
}