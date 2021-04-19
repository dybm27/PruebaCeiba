package co.com.ceiba.mobile.pruebadeingreso.rest

import co.com.ceiba.mobile.pruebadeingreso.model.entities.Post
import co.com.ceiba.mobile.pruebadeingreso.model.entities.User
import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET(Endpoints.GET_USERS)
    suspend fun getUsers(): Response<List<User>>

    @GET(Endpoints.GET_POST_USER)
    suspend fun getPost(@Query("userId") userId: String): Response<List<Post>>
}