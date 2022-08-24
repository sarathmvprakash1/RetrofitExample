package com.example.retrofitexample.ui.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitexample.R
import com.example.retrofitexample.databinding.BookItemBinding
import kotlinx.android.synthetic.main.book_item.view.*

class MarsRealEstateAdapter(val onClickListener: OnClickListener) :
    ListAdapter<BookModel, MarsRealEstateAdapter.MarsRealEstateViewHolder>(FlowerDiffCallback) {

    class MarsRealEstateViewHolder(binding: BookItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        private val textView = binding.text
        fun bind(book: BookModel) {
            textView.text = book.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsRealEstateViewHolder {
        return MarsRealEstateViewHolder(BookItemBinding.inflate(LayoutInflater.from(parent.context),
                parent, false))
    }

    override fun onBindViewHolder(holder: MarsRealEstateViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.bind(marsProperty)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(marsProperty)
        }
    }

    class OnClickListener(val clickListener: (marsProperty: BookModel) -> Unit) {
        fun onClick(marsProperty: BookModel) = clickListener(marsProperty)
    }
}

object FlowerDiffCallback : DiffUtil.ItemCallback<BookModel>() {
    override fun areItemsTheSame(oldItem: BookModel, newItem: BookModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: BookModel, newItem: BookModel): Boolean {
        return oldItem.id == newItem.id
    }
}