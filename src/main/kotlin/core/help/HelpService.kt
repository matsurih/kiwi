package core.help

import retrofit2.http.GET

interface HelpService{

    @GET("")
    fun configuration()

    @GET("")
    fun languages()

    @GET("")
    fun privacy()

    @GET("")
    fun tos()

}