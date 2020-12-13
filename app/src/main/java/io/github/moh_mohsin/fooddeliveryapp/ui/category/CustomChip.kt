package io.github.moh_mohsin.fooddeliveryapp.ui.category

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import com.google.android.material.chip.Chip


/**
 * A workaround for an unwanted behaviour with nested scrolling.
 * This view will handle every touch even as a click and then allow the parent to
 * consume the touch event
 */
class CustomChip : Chip {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onTouchEvent(event: MotionEvent): Boolean {
        performClick()
        return false
    }

}