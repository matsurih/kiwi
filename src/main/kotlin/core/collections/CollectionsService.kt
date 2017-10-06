package core.collections

import retrofit2.http.GET
import retrofit2.http.POST

interface CollectionsService{

    @GET("")
    fun getEntries()

    @GET("")
    fun list()

    @GET("")
    fun show()

    @POST("")
    fun create()

    @POST("")
    fun destroy()

    @POST("")
    fun addEntries()

    @POST("")
    fun curateEntries()

    @POST("")
    fun moveEntries()

    @POST("")
    fun removeEntries()

    @POST("")
    fun update()
}