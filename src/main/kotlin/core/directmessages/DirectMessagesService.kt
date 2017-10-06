package core.directmessages

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DirectMessagesService{
    @GET("")
    fun get()

    @GET("")
    fun listEvents()

    @GET("")
    fun showEvents()

    @GET("")
    fun listWelcomeMessages()

    @GET("")
    fun listRulesOfWelcomeMessages()

    @GET("")
    fun showRulesOfWelcomeMessages()

    @GET("")
    fun showWelcomeMessages()

    @POST("")
    fun destroy()

    @POST("")
    fun newEvents()

    @POST("")
    fun new()

    @POST("")
    fun newWelcomeMessages()

    @POST("")
    fun newRulesOfWelcomeMessages()

    @DELETE("/1.1/direct_messages/welcome_messages/destroy.json")
    fun destroyWelcomeMessages(@Query("id") id: String)

    @DELETE("/1.1/direct_messages/welcome_messages/rules/destroy.json")
    fun destroyRulesOfWelcomeMessages(@Query("id") id: String)
}