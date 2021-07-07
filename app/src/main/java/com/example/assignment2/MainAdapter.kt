package com.example.assignment2

import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.databinding.MainRecyclerviewItemBinding

class ViewItem(var id: Int=-1, var title: String = "", var content: String = "", var modified: String)

class MainAdapter(private val dataSet: ArrayList<ViewItem>):
        RecyclerView.Adapter<MainAdapter.NoteHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val binding = MainRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val viewItem: ViewItem = dataSet[position]
        holder.bind(viewItem)

        //리스너 등록
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, NoteActivity::class.java)
            //intent에 값 넣어 전달
            intent.putExtra("id", viewItem.id)
            intent.putExtra("title", viewItem.title)
            intent.putExtra("content", viewItem.content)
            intent.putExtra("modified", viewItem.modified)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
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