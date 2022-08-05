package com.vytivskyi.testtaskvideo.view.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vytivskyi.testtaskvideo.R
import com.vytivskyi.testtaskvideo.data.Video
import com.vytivskyi.testtaskvideo.databinding.RecyclerViewItemBinding

class SimpleAdapter() :
    RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {
    var mainL: List<Video> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var mItemClickListener: (position: Int) -> Unit = {}


    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = RecyclerViewItemBinding.bind(item)
        fun bind(video: Video?) = with(binding) {
            tvTitle.text = video?.title
            tvDescription.text = video?.description
            tvCreator.text = video?.subtitle
        }

        init {
            item.setOnClickListener {
                mItemClickListener(absoluteAdapterPosition)
            }
            setHasStableIds(true)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mainL[position])
    }

    override fun getItemCount(): Int {
        return mainL.size
    }

    override fun getItemId(position: Int): Long {
        return mainL[position].hashCode().toLong()
    }

}