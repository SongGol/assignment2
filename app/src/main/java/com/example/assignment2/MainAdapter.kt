package com.example.assignment2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.databinding.MainRecyclerviewItemBinding

class ViewItem(var title: String = "", var content: String = "", var modified: String)

class MainAdapter(private val dataSet: ArrayList<ViewItem>):
        RecyclerView.Adapter<MainAdapter.NoteHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val binding = MainRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val viewItem: ViewItem = dataSet[position]
        holder.bind(viewItem)
    }

    override fun getItemCount(): Int = dataSet.size

    class NoteHolder(private val binding: MainRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ViewItem) {
            binding.recyclerItemTitle.text = data.title
            binding.recyclerItemContent.text = data.content
            binding.recyclerItemModified.text = data.modified
        }
    }
}