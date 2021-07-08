package com.example.assignment2

import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.databinding.MainRecyclerviewItemBinding

class ViewItem(var id: String="", var title: String = "", var content: String = "", var modified: String)

class MainAdapter(private val dataSet: ArrayList<RecyclerItem>):
        RecyclerView.Adapter<MainAdapter.NoteHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val binding = MainRecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val item: RecyclerItem = dataSet[position]
        holder.bind(item)

        //리스너 등록
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, NoteActivity::class.java)
            //intent에 Parcelable객체 값 넣어 전달
            val data = RecyclerItem(item.id, item.title, item.content, item.modified)
            intent.putExtra("data", data)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }

    override fun getItemCount(): Int = dataSet.size

    class NoteHolder(private val binding: MainRecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: RecyclerItem) {
            binding.recyclerItemTitle.text = data.title
            binding.recyclerItemContent.text = data.content
            binding.recyclerItemModified.text = data.modified
        }
    }
}