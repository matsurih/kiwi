package core.account

import io.reactivex.Observable
import mold.Settings
import mold.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

const val ROUTE = "/1.1/account"

interface AccountService{

    /**
     * Get account settings by using [token].
     * @return Settings
     */
    @GET("$ROUTE/settings.json")
    fun getAccountSettings(@Header("token") token: String): Observable<Response<Settings>>

    /**
     * Verify Credentials by using [token].
     * @return User data
     */
    @GET("$ROUTE/verify_credentials.json")
    fun verifyCredentials(@Header("token") token: String): Observable<Response<User>>

    /**
     * Remove user's profile banner
     *
     * Banner [image] must be a valid GIF, JPG, or PNG image of less than 800KB in size.
     * Images with larger than 2048 pixels will bi forcibily scale down.
     * The image must be provided as raw multipart data, not a URL
     * You can use [mediaId] instead of base64 image string.
     */
    // UNDONE
    @POST("$ROUTE/profile_banner.json")
    fun removeProfileBanner(image: String?,
                            mediaId: Long?,
                            tile: Boolean = false,
                            includeEntities: Boolean = false,
                            skipStatus: Boolean = true
                            )

    /**
     * Update user's settings.
     */
    @POST("")
    fun updateSettings()

    /**
     * Update user's profile.
     */
    @POST("")
    fun updateProfile()

    /**
     * Update user's profile background image.
     */
    @POST("$ROUTE/update_profile_background_image.json")
    fun updateProfileBackgroundImage()

    /**
     * Update user's profile banner.
     */
    @POST("")
    fun updateProfileBanner()

    /**
     * Update user's profile image.
     */
    @POST("")
    fun updateProfileImage()

}