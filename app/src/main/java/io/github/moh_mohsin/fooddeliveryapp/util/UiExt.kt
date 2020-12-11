package io.github.moh_mohsin.fooddeliveryapp.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

inline fun <reified T : Activity> Context.startActivity(block: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    block(intent)
    startActivity(intent)
}

fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}

fun Context.toast(res: Int, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, res, length).show()
}

fun Fragment.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    context?.toast(message, length)
}


fun Fragment.toast(res: Int, length: Int = Toast.LENGTH_SHORT) {
    requireContext().toast(res, length)
}

fun View.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    context.toast(message, length)
}

fun View.hide() {
    visibility = View.GONE
}
fun View.invisible() {
    visibility = View.INVISIBLE
}


fun View.show() {
    visibility = View.VISIBLE
}

fun View.showOrHide(show: Boolean) {
    if (show) show() else hide()
}


fun Context.getColorCompact(@ColorRes id: Int) = ContextCompat.getColor(this, id)
fun Context.getDrawableCompact(@DrawableRes id: Int) = ContextCompat.getDrawable(this, id)
