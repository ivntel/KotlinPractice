package com.example.ivan.kotlinpractice.Network

import com.example.ivan.kotlinpractice.objects.Model
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi{
    @GET("api.php")
    fun hitCountCheck(@Query("action") action: String,
                  @Query("format") format: String,
                  @Query("list") list: String,
                  @Query("srsearch") srsearch: String):
        Observable<Model.Result>
}