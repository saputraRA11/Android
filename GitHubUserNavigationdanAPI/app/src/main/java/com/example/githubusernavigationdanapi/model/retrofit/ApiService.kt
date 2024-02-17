package com.example.githubusernavigationdanapi.model.retrofit
import com.example.githubusernavigationdanapi.model.response.DetailUserResponse
import com.example.githubusernavigationdanapi.model.response.ListFollowerResponseItem
import com.example.githubusernavigationdanapi.model.response.ListFollowingResponseItem
import com.example.githubusernavigationdanapi.model.response.SearchUserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @Headers(
        "Authorization: token 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",
        "User-Agent: request"
    )
    @GET("search/users")
    fun searchUser(
        @Query("q") username: String
    ): Call<SearchUserResponse>

    @Headers(
        "Authorization: token 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",
        "User-Agent: request"
    )
    @GET("users/{username}")
    fun detailUser(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @Headers(
        "Authorization: token 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",
        "User-Agent: request"
    )
    @GET("users/{username}/followers")
    fun followersUser(
        @Path("username") username: String
    ): Call<List<ListFollowerResponseItem>>

    @Headers(
        "Authorization: token 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",
        "User-Agent: request"
    )
    @GET("users/{username}/following")
    fun followingUser(
        @Path("username") username: String
    ): Call<List<ListFollowingResponseItem>>
}