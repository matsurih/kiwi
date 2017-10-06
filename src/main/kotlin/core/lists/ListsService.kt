package core.lists

import retrofit2.http.GET
import retrofit2.http.POST

interface ListsService{
    @GET("")
    fun list()

    @GET("")
    fun members()

    @GET("")
    fun showMemberships()

    @GET("")
    fun ownweships()

    @GET("")
    fun show()

    @GET("")
    fun statuses()

    @GET("")
    fun subscribers()

    @GET("")
    fun showSubscribers()

    @GET("")
    fun subscriptions()

    @POST("")
    fun create()

    @POST("")
    fun destroy()

    @POST("")
    fun createMembers()

    @POST("")
    fun createAllMembers()

    @POST("")
    fun destroyMembers()

    @POST("")
    fun destroyAllMembers()

    @POST("")
    fun createSubscribers()

    @POST("")
    fun destroySubscribers()

    @POST("")
    fun update()


}