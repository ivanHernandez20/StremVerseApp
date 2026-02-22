package com.example.streamverse.Api

import com.example.streamverse.Model.LoginRequest
import com.example.streamverse.Model.LoginResponse
import com.example.streamverse.Model.Movie
import com.example.streamverse.Model.ResetPasswordRequest
import com.example.streamverse.Model.ResetPasswordResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    // LOGIN
    @POST("api/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    // RECUPERACIÓN DE CONTRASEÑA
    @POST("api/reset-password")
    suspend fun resetPassword(@Body request: ResetPasswordRequest): Response<ResetPasswordResponse>

    // VER LAS PELICULAS
    @GET("api/movies")
    suspend fun getMovies(@Header("Authorization") token : String) : Response<List<Movie>>
}