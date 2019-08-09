package com.example.catapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class CatViewModel : ViewModel() {

    private val CAT_AMOUNT = "10"
    private var catLiveData: MutableLiveData<List<CatDO>>? = null
    private var catRepository: CatRepository? = null
    private lateinit var apikey : String
    private lateinit var userId : String
    lateinit var cats: java.util.ArrayList<CatDO>

    fun init(apikey: String, userId : String) {
        if (catLiveData != null) {
            return
        }
        this.apikey = apikey
        this.userId = userId
        catRepository = CatRepository.instance
        catLiveData = catRepository!!.getCats(CAT_AMOUNT, apikey)
    }

    fun getAllCats(): LiveData<List<CatDO>>? {
        return catLiveData
    }

    fun markCatAsFavorite(id: String) {
        catRepository!!.markCatAsFavorite(id, apikey)
    }

    fun getFavoriteCats() : LiveData<List<CatDO>>{
        return catRepository!!.getFavoriteCats(CAT_AMOUNT, apikey)
    }
}