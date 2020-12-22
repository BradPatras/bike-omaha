package io.github.bradpatras.bikeomaha.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.bradpatras.bikeomaha.R
import io.github.bradpatras.bikeomaha.data.Trail
import io.github.bradpatras.bikeomaha.databinding.ListItemTrailBinding
import kotlinx.coroutines.flow.emptyFlow

class TrailAdapter : ListAdapter<Trail, TrailAdapter.TrailViewHolder>(TrailDiffCallback()) {

    val itemCheckedLiveData = MutableLiveData<Long>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailViewHolder {
        val viewHolder = TrailViewHolder(ListItemTrailBinding.inflate(LayoutInflater.from(parent.context), parent, false))

        viewHolder.setOnClick {
            itemCheckedLiveData.postValue(getItemId(it))
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: TrailViewHolder, position: Int) {
        val trailItem = getItem(position)
        holder.bind(trailItem)
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).identifier
    }

    class TrailViewHolder(private val binding: ListItemTrailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(trailItem: Trail) {
            binding.titleTv.text = trailItem.title
            binding.colorView.setColorFilter(trailItem.pathColor)
            if (trailItem.selected) {
                binding.titleTv.setCheckMarkDrawable(R.drawable.ic_check)
            } else {
                binding.titleTv.checkMarkDrawable = null
            }
        }

        fun setOnClick(handler: (Int) -> Unit) {
            binding.titleTv.setOnClickListener { handler(layoutPosition) }
        }
    }
}

private class TrailDiffCallback : DiffUtil.ItemCallback<Trail>() {
    override fun areItemsTheSame(oldItem: Trail, newItem: Trail): Boolean {
        return oldItem.identifier == newItem.identifier
    }

    override fun areContentsTheSame(oldItem: Trail, newItem: Trail): Boolean {
        val sameTitle = oldItem.title == newItem.title
        val sameColor = oldItem.pathColor == newItem.pathColor
        val selected = oldItem.selected == newItem.selected
        return sameTitle && sameColor && selected
    }
}