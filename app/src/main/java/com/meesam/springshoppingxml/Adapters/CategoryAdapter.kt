package com.meesam.springshoppingxml.Adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meesam.springshoppingxml.R
import com.meesam.springshoppingxml.databinding.CategoryItemBinding
import com.meesam.springshoppingxml.models.CategoryResponse

class CategoryAdapter(
    val categories: List<CategoryResponse>,
    private val onItemClickListener: (id: Long) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryItemViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: Long)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryItemViewHolder {
        val itemBinding =
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(
        holder: CategoryItemViewHolder,
        position: Int
    ) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class CategoryItemViewHolder(val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: CategoryResponse) {
            binding.tvCategoryTitle.text = category.title
            binding.tvCategoryTitle.setBackgroundResource(R.drawable.btn_background)   // R.drawable.btn_background.toString()
            binding.root.setOnClickListener {
                onItemClickListener(category.id)
            }
        }
    }
}