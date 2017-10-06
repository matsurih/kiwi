package core.blocks

import retrofit2.http.GET
import retrofit2.http.POST

interface BlocksService{
    @GET("")
    fun get()

    @GET("")
    fun list()

    @POST("")
    fun create()

    @POST("")
    fun destroy()
}
