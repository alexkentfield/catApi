package com.example.catapi

import androidx.lifecycle.MutableLiveData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class CatRepository {

    private val catApi: CatApi = CatService.createService(CatApi::class.java)

    fun getCats(limit: String, apiKey: String): MutableLiveData<List<CatDO>> {
        val catData = MutableLiveData<List<CatDO>>()

        catApi.getCats(limit, apiKey).enqueue(object : Callback<List<CatDO>> {
            override fun onResponse(
                call: Call<List<CatDO>>,
                response: Response<List<CatDO>>
            ) {
                if (response.isSuccessful) {
                    catData.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<CatDO>>, t: Throwable) {
                catData.value = null
            }
        })
        return catData
    }

    fun markCatAsFavorite(id: String, apiKey: String) {
        catApi.favoriteCat(FavoriteCat(id, "User-123"), apiKey).enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(
                call: Call<ResponseBody?>,
                response: Response<ResponseBody?>
            ) {
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {}
        })
    }

    fun getFavoriteCats(limit: String, apiKey: String): MutableLiveData<List<CatDO>> {
        val catData = MutableLiveData<List<CatDO>>()

        catApi.getFavoriteCats(limit, apiKey).enqueue(object : Callback<List<CatDO>> {
            override fun onResponse(
                call: Call<List<CatDO>>,
                response: Response<List<CatDO>>
            ) {
                if (response.isSuccessful) {
                    catData.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<CatDO>>, t: Throwable) {
                catData.value = null
            }
        })
        return catData
    }

    companion object {
        private var catRepository: CatRepository? = null
        val instance: CatRepository
            get() {
                if (catRepository == null) {
                    catRepository = CatRepository()
                }
                return catRepository as CatRepository
            }
    }
}