package com.angelika.lesson_55.data.remout.apiservices

import com.angelika.lesson_55.models.Post
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface JsonPlaceApiService {

    @GET("posts")
    fun fetchPosts(): Call<List<Post>>

    @POST("posts")
    fun createNewPost(
        @Body() post: Post
    ): Call<Post>

    @PUT("posts/{postId}")
    fun updatePost(
        @Path("postId") id: Int,
        @Body post: Post
    ): Call<Post>

    @FormUrlEncoded
    @PATCH("posts/{postId}")
    fun editPost(
        @Path("postId") id: Int,
        @Field("title") title: String,
    ): Call<Post>

    @DELETE("posts/{postId}")
    fun deletePost(
        @Path("postId") id: Int,
    ): Call<Post>
}