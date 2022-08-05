package com.vytivskyi.testtaskvideo.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.vytivskyi.testtaskvideo.databinding.ActivityMainBinding
import com.vytivskyi.testtaskvideo.view.adaptors.SimpleAdapter
import com.vytivskyi.testtaskvideo.viewmodel.VideosVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val mViewModel: VideosVM by viewModels()
    private val simpleAdapter = SimpleAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        simpleAdapter.mItemClickListener = ::onClick
        binding.recyclerView.adapter = simpleAdapter


        initObserver()
    }

    private fun initObserver() = with(binding) {
        mViewModel.videos.observe(this@MainActivity) {
            simpleAdapter.mainL = it
        }
    }

    private fun onClick(position: Int?) {
        val i = Intent(this@MainActivity, VideoActivity::class.java)
        i.putExtra("position", position)
        startActivity(i)
    }
}