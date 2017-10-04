package core.authn

import io.reactivex.Observable
import mold.AuthenticationMold
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface TwitterOAuthService{
    @POST("/oauth2/token")
    @Headers("Content-Type: application/x-www-form-urlencoded;charset=UTF-8", "User-Agent: Kiwi")
    fun authenticate(@Header("Authorization") encodedKey: String, @Body body: RequestBody) : Observable<Response<AuthenticationMold>>
}