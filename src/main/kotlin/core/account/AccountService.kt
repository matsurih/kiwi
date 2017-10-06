package core.account

import io.reactivex.Observable
import mold.Settings
import mold.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AccountService{
    @GET("/1.1/account/settings.json")
    fun getAccountSettings(@Header("token") token: String): Observable<Response<Settings>>

    @GET("/1.1/account/verify_credentials.json")
    fun verifyCredentials(@Header("token") token: String): Observable<Response<User>>

    @POST("")
    fun removeProfileBanner()

    @POST("")
    fun updateSettings()

    @POST("")
    fun updateProfile()

    @POST("")
    fun updateProfileBackgroundImage()

    @POST("")
    fun updateProfileBanner()

    @POST("")
    fun updateProfileImage()

}