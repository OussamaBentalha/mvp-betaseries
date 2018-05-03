package com.android.sam.kotlin_mvp_project.data.betaseries.services

import com.android.sam.kotlin_mvp_project.data.betaseries.models.LoginResponse
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by oussamabentalha on 25/04/2018.
 */
interface MembersService {

    @POST("members/auth")
    fun login(@Query("login") login: String, @Query("password") password: String): Single<LoginResponse>
}