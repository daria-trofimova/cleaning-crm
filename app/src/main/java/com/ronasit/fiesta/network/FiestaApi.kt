package com.ronasit.fiesta.network

import com.ronasit.fiesta.network.requests.AddAppointmentRequest
import com.ronasit.fiesta.network.requests.AuthorizeRequest
import com.ronasit.fiesta.network.requests.GetCodeRequest
import com.ronasit.fiesta.network.requests.ProfileRequest
import com.ronasit.fiesta.network.responses.AddAppointmentResponse
import com.ronasit.fiesta.network.responses.AuthorizeResponse
import com.ronasit.fiesta.network.responses.GetClientsResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface FiestaApi {

    @POST("/auth/send-code")
    fun sendCode(@Body request: GetCodeRequest): Completable

    @POST("/auth/authorize")
    fun authorize(@Body request: AuthorizeRequest): Single<Response<AuthorizeResponse>>

    @PUT("/profile")
    fun createProfile(@Body request: ProfileRequest): Single<Response<Void>>

    @POST("/auth/refresh")
    fun refreshToken(): Call<Void>

    @GET("/clients?all=1")
    fun getClients(): Single<Response<GetClientsResponse>>

    @POST("/clients")
    fun createClient(@Body request: ClientModel): Single<Response<ClientModel>>

    @PUT("/clients/{id}")
    fun updateClient(@Path("id") id: Int, @Body request: ClientModel): Single<Response<Void>>

    @DELETE("/clients/{id}")
    fun deleteClient(@Path("id") id: Int): Single<Response<Void>>
    
    @POST("/appointments")
    fun createAppointment(@Body request: AddAppointmentRequest): Single<Response<AddAppointmentResponse>>
}