package com.example.assignment2

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.databinding.MainRecyclerviewItemBinding

class MainAdapter(private val dataSet: ArrayList<RecyclerItem>):
        RecyclerView.Adapter<MainAdapter.NoteHolder>(), ItemActionListener {

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

    override fun onItemMoved(from: Int, to: Int) {
        if (from == to) {
            return
        }

        val fromItem = dataSet.removeAt(from)
        dataSet.add(to, fromItem)
        notifyItemMoved(from, to)
    }

    override fun onItemSwipped(position: Int) {
        dataSet.removeAt(position)
        notifyItemRemoved(position)
    }
}