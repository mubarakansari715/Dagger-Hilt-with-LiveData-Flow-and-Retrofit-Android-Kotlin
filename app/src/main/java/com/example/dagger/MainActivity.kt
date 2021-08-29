package com.example.dagger

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dagger.Adapter.PostAdapter
import com.example.dagger.Model.Post
import com.example.dagger.ViewModel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.Flow


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter
    val postViewModel: PostViewModel  by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUi()
        postViewModel.getPost()
        postViewModel.postLiveData.observe(this, Observer { response ->
            postAdapter.setData(response as ArrayList<Post>)

        })
    }

    private fun setUi() {
        recyclerView = findViewById(R.id.recyclerView)
        postAdapter = PostAdapter(this, ArrayList())
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }
    }
}