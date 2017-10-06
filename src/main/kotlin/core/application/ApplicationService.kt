package core.application

import retrofit2.http.GET

interface ApplicationService{
    @GET("")
    fun getRateLimitStatus()
}
