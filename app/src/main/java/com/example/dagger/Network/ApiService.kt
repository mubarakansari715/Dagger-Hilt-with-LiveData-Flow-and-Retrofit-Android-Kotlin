package com.example.dagger.Network
import com.example.dagger.Model.Post
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
   suspend fun getPosts() : List<Post>
}