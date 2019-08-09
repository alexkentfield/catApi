package com.example.catapi

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface CatApi {
    @GET("v1/images/search")
    fun getCats(@Query("limit") limit: String, @Header("x-api-key") key: String): Call<List<CatDO>>

    @POST("v1/favourites")
    fun favoriteCat(@Body favoriteCat: FavoriteCat, @Header("x-api-key") key: String) : Call<ResponseBody?>

    @GET("v1/favourites")
    fun getFavoriteCats(@Query("limit") limit: String, @Header("x-api-key") key: String): Call<List<CatDO>>
}