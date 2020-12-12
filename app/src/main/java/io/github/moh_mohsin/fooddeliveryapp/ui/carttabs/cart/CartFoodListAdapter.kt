package io.github.moh_mohsin.fooddeliveryapp.ui.carttabs.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import io.github.moh_mohsin.fooddeliveryapp.R
import io.github.moh_mohsin.fooddeliveryapp.data.model.Food
import io.github.moh_mohsin.fooddeliveryapp.databinding.CartFoodItemBinding

class CartFoodListAdapter(
    val onDelete: (food: Food) -> Unit
) :
    ListAdapter<Pair<Food, Int>, CartFoodListAdapter.ViewHolder>(ResponseDiffCallback()) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = getItem(position)

        holder.bind(onDelete, todo!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor(val binding: CartFoodItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            onClick: (food: Food) -> Unit,
            item: Pair<Food, Int>
        ) {
            val food = item.first
            binding.name.text = food.name

            binding.price.text =
                binding.root.context.getString(R.string.price, food.price, "USD")

            binding.image.load(food.image) {
                crossfade(true)
            }

            binding.delete.setOnClickListener {
                onClick.invoke(food)
            }

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CartFoodItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}


class ResponseDiffCallback : DiffUtil.ItemCallback<Pair<Food, Int>>() {
    override fun areItemsTheSame(oldItem: Pair<Food, Int>, newItem: Pair<Food, Int>): Boolean {
        return oldItem.first.id == newItem.first.id
    }

    override fun areContentsTheSame(oldItem: Pair<Food, Int>, newItem: Pair<Food, Int>): Boolean {
        return oldItem.first == newItem.first && oldItem.second == newItem.second
    }
}




