package core.media

import retrofit2.http.GET
import retrofit2.http.POST

interface MediaService{
    @GET("")
    fun uploadStatus()

    @POST("")
    fun createMetadata()

    @POST("")
    fun upload()

    @POST("")
    fun appendUpload()

    @POST("")
    fun finalizeUpload()

    @POST("")
    fun initUpload()
}
