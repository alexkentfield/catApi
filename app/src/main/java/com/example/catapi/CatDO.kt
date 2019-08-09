package com.example.catapi

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CatDO {
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("width")
    @Expose
    var width: Int = 0
    @SerializedName("height")
    @Expose
    var height: Int = 0
}