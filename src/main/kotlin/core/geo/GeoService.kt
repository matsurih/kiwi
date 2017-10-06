package core.geo

import retrofit2.http.GET
import retrofit2.http.Path

interface GeoService{
    @GET("/1.1/geo/id/{place_id}.json")
    fun get(@Path("place_id") id: String)

    @GET("")
    fun reverseGeocode()

    @GET("")
    fun search()
}
