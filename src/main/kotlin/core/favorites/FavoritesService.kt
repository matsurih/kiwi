package core.favorites

import retrofit2.http.GET
import retrofit2.http.POST

interface FavoritesService{
    @GET("")
    fun list()

    @POST("")
    fun create()

    @POST("")
    fun destroy()
}
