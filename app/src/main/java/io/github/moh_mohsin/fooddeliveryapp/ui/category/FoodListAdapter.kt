package io.github.moh_mohsin.fooddeliveryapp.ui.category

import android.os.Handler
import android.os.Looper
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import io.github.moh_mohsin.fooddeliveryapp.R
import io.github.moh_mohsin.fooddeliveryapp.data.model.Food
import io.github.moh_mohsin.fooddeliveryapp.databinding.FoodItemBinding
import io.github.moh_mohsin.fooddeliveryapp.util.getColorCompact

class FoodListAdapter(
    val onClick: (food: Food) -> Unit
) :
    ListAdapter<Food, FoodListAdapter.ViewHolder>(ResponseDiffCallback()) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = getItem(position)

        holder.bind(onClick, todo!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    class ViewHolder private constructor(val binding: FoodItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var addToCartDisabled = false

        fun bind(
            onClick: (food: Food) -> Unit,
            food: Food
        ) {
            binding.name.text = food.name
            binding.ingredients.text = food.ingredients
            binding.size.text = food.size

            binding.addToCart.text =
                binding.root.context.getString(R.string.price, food.price, "USD")

            binding.image.load(food.image){
                crossfade(true)
            }

            binding.addToCart.setOnClickListener {
                if (addToCartDisabled) return@setOnClickListener
                addToCartDisabled = true

                TransitionManager.beginDelayedTransition(binding.root)
                binding.addToCart.setBackgroundColor(binding.root.context.getColorCompact(R.color.green))
                binding.addToCart.setText(R.string.added_to_cart)

                Handler(Looper.getMainLooper()).postDelayed({
                    TransitionManager.beginDelayedTransition(binding.root)
                    binding.addToCart.setBackgroundColor(binding.root.context.getColorCompact(R.color.black))
                    binding.addToCart.text =
                        binding.root.context.getString(R.string.price, food.price, "USD")

                    addToCartDisabled = false
                }, 1000)
                onClick.invoke(food)
            }

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FoodItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}


class ResponseDiffCallback : DiffUtil.ItemCallback<Food>() {
    override fun areItemsTheSame(oldFood: Food, newFood: Food): Boolean {
        return oldFood.id == newFood.id
    }

    override fun areContentsTheSame(oldFood: Food, newFood: Food): Boolean {
        return oldFood == newFood
    }
}




