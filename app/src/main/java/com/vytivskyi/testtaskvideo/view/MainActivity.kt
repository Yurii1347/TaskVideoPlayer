package com.vytivskyi.testtaskvideo.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.vytivskyi.testtaskvideo.databinding.ActivityMainBinding
import com.vytivskyi.testtaskvideo.view.adaptors.SimpleAdapter
import com.vytivskyi.testtaskvideo.viewmodel.VideosVM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), SimpleAdapter.ItemClickListener {

    private val mViewModel: VideosVM = VideosVM()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch(Dispatchers.IO) {
            mViewModel.getVideos()
        }

        initObserver()
    }

    private fun initObserver() = with(binding) {
        mViewModel.apply {
            videos.observe(this@MainActivity) {
                recyclerView.adapter =
                    SimpleAdapter(
                        it, this@MainActivity
                    )
                Log.d("lol", it?.size.toString())
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }

    override fun onClick(position: Int?) {
        val i = Intent(this@MainActivity, VideoActivity::class.java)
        i.putExtra("position", position)
        startActivity(i)
    }
}