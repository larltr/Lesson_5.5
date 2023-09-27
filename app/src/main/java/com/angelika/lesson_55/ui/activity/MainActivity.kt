package com.angelika.lesson_55.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.angelika.lesson_55.App
import com.angelika.lesson_55.databinding.ActivityMainBinding
import com.angelika.lesson_55.models.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val postApiService = App.retrofitClient.jsonPlaceApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchPosts()
        createNewPost()
        updatePost()
        editPost()
        deletePost()
    }

    private fun fetchPosts() {
        postApiService.fetchPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                response.body()?.let {
                    Log.e("success", it.toString())
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.e("error", t.localizedMessage ?: "Error not showing")
            }
        })
    }

    private fun createNewPost() = with(binding) {
        btnPost.setOnClickListener {
            val id = 100
            val userId = 11
            val tittle = etTittle.text.toString()
            val body = etBody.text.toString()
            val post = Post(id = id, userId = userId, title = tittle, body = body)

            postApiService.createNewPost(post)
                .enqueue(object : Callback<Post> {
                    override fun onResponse(call: Call<Post>, response: Response<Post>) {
                        response.body()?.let {
                            Log.e("post_request_data", it.toString())
                        }
                    }

                    override fun onFailure(call: Call<Post>, t: Throwable) {
                        Log.e("post_request_data", t.localizedMessage ?: "Error Occurred!")
                    }
                })
        }
    }

    private fun updatePost() = with(binding) {
        btnPut.setOnClickListener {
            val id = 21
            val userId = 7
            val tittle = etTittle.text.toString()
            val body = etBody.text.toString()
            val post = Post(id = id, userId = userId, title = tittle, body = body)

            postApiService.updatePost(id, post = post).enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    response.body()?.let {
                        Log.e("put_request_data", it.toString())
                    }
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    Log.e("put_request_data", t.localizedMessage ?: "Error Occurred!")
                }
            })
        }
    }

    private fun editPost() = with(binding) {
        btnPatch.setOnClickListener {
            val id = 5
            val tittle = etTittle.text.toString()

            postApiService.editPost(id = id, title = tittle).enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    response.body()?.let {
                        Log.e("patch_request_data", it.toString())
                    }
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    Log.e("patch_request_data", t.localizedMessage ?: "Error Occurred!")
                }
            })
        }
    }

    private fun deletePost() = with(binding) {
        btnDelete.setOnClickListener {
            val id = 65

            postApiService.deletePost(id).enqueue(object : Callback<Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    response.body()?.let {
                        Log.e("delete_request_data", it.toString())
                    }
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    Log.e("delete_request_data", t.localizedMessage ?: "Error Occurred!")
                }
            })
        }
    }
}