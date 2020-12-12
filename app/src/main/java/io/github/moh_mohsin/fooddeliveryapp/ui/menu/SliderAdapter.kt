package io.github.moh_mohsin.fooddeliveryapp.ui.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import coil.load
import com.smarteist.autoimageslider.SliderViewAdapter
import io.github.moh_mohsin.fooddeliveryapp.R
import io.github.moh_mohsin.fooddeliveryapp.ui.menu.SliderItem.SliderItemRes
import io.github.moh_mohsin.fooddeliveryapp.ui.menu.SliderItem.SliderItemUrl

sealed class SliderItem {
    data class SliderItemRes(@DrawableRes val res: Int) : SliderItem()
    data class SliderItemUrl(val url: String) : SliderItem()
}

class SliderAdapter : SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {
    private var sliderItems: List<SliderItem> = ArrayList()

    fun renewItems(sliderItems: List<SliderItem>) {
        this.sliderItems = sliderItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.image_slider_layout_item, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        when (val sliderItem = sliderItems[position]) {
            is SliderItemRes -> {
                viewHolder.sliderImage.load(sliderItem.res)
            }
            is SliderItemUrl -> {
                viewHolder.sliderImage.load(sliderItem.url)
            }
        }
        viewHolder.sliderImage

    }

    override fun getCount() = sliderItems.size

    class SliderAdapterVH(itemView: View) : ViewHolder(itemView) {
        var sliderImage: ImageView = itemView.findViewById(R.id.slider_image)
    }
}