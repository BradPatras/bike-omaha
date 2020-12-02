package io.github.bradpatras.bikeomaha.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.bradpatras.bikeomaha.data.Trail
import io.github.bradpatras.bikeomaha.databinding.ListItemTrailBinding

class TrailAdapter : ListAdapter<Trail, TrailAdapter.TrailViewHolder>(TrailDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailViewHolder {
        return TrailViewHolder(ListItemTrailBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TrailViewHolder, position: Int) {
        val trailItem = getItem(position)
        holder.bind(trailItem)
    }

    class TrailViewHolder(private val binding: ListItemTrailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(trailItem: Trail) {
            binding.titleTv.text = trailItem.title
            binding.colorView.setBackgroundColor(Color.CYAN)
        }
    }
}

private class TrailDiffCallback : DiffUtil.ItemCallback<Trail>() {
    override fun areItemsTheSame(oldItem: Trail, newItem: Trail): Boolean {
        return oldItem.identifier == newItem.identifier
    }

    override fun areContentsTheSame(oldItem: Trail, newItem: Trail): Boolean {
        return oldItem.title == newItem.title
    }
}